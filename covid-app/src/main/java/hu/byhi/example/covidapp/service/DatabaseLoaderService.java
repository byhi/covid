package hu.byhi.example.covidapp.service;

import hu.byhi.example.covidapp.model.StatusEntity;
import hu.byhi.example.covidapp.repository.StatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Service
public class DatabaseLoaderService {

    Logger logger = LoggerFactory.getLogger(DatabaseLoaderService.class);

    @Autowired
    StatusRepository statusRepository;

    @Value( "${apify-covid-endpoint}" )
    private String BASE_URL;

    WebClient client;


    public void initDatabase(){
        saveDataIntoDatabase(
                getDataFromApi()
        );
    }

    private void saveDataIntoDatabase(StatusEntity[] dataFromApi) {
        statusRepository.saveAll(
              Arrays.asList(dataFromApi)
        );
        logger.info("Data migartion is finished");
    }

    private StatusEntity[] getDataFromApi(){
        logger.info("Download data from api");
        this.client = WebClient.create();
        Mono<StatusEntity[]> charactersDTOMono = client.get()
                .uri(BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(StatusEntity[].class);
        StatusEntity[] readers = charactersDTOMono.block();

        if(readers == null || readers.length == 0) {
            throw new IllegalStateException("No data from api!");
        }
        return  readers;
    }
}
