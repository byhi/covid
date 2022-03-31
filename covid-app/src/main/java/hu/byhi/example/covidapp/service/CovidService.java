package hu.byhi.example.covidapp.service;

import hu.byhi.example.covidapp.model.StatusEntity;
import hu.byhi.example.covidapp.model.dto.StatusDto;
import hu.byhi.example.covidapp.model.filter.DateFilter;
import hu.byhi.example.covidapp.repository.StatusRepository;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CovidService {

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ArrayList<StatusDto> getAllStatus() {
        return (ArrayList<StatusDto>) StreamSupport.stream(statusRepository.findAll().spliterator(),false)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public StatusDto getStatusById(Long id) {
        return modelMapper.map(statusRepository.findById(id).orElseThrow(IllegalStateException::new), StatusDto.class);
    }

    public ArrayList<StatusDto> getStatusByDateFilter(DateFilter dateFilter) {
        return (ArrayList<StatusDto>) statusRepository.findAllByDateInterval(dateFilter.getIntervalStart(), dateFilter.getIntervalEnd()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public StatusDto createStatus(StatusDto statusDto) {
        return convertToDto(statusRepository.save(convertToEntity(statusDto)));
    }

    public StatusDto updateStatus(StatusDto statusDto) {
        return convertToDto(statusRepository.save(convertToEntity(statusDto)));
    }

    public void deleteStatus(Long id) {
        statusRepository.deleteById(id);
    }

    private StatusDto convertToDto(StatusEntity entity) {
        Converter<LocalDateTime, String> toDateString = new AbstractConverter<LocalDateTime, String>() {
            @Override
            protected String convert(LocalDateTime source) {
                return source.toString();
            }
        };
        modelMapper.addConverter(toDateString);

        return  modelMapper.map(entity, StatusDto.class);
    }
    private StatusEntity convertToEntity(StatusDto statusDto) {
        Converter<String, LocalDateTime> toStringDate = new AbstractConverter<String, LocalDateTime>() {
            @Override
            protected LocalDateTime convert(String source) {
                return LocalDateTime.parse(source);
            }
        };
        modelMapper.addConverter(toStringDate);
        return modelMapper.map(statusDto, StatusEntity.class);
    }
}
