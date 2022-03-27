package hu.byhi.example.covidapp.service;

import hu.byhi.example.covidapp.model.StatusEntity;
import hu.byhi.example.covidapp.model.dto.StatusDto;
import hu.byhi.example.covidapp.repository.StatusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        statusRepository.findAll();
        return (ArrayList<StatusDto>) StreamSupport.stream(statusRepository.findAll().spliterator(),false)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private StatusDto convertToDto(StatusEntity entity) {
        return modelMapper.map(entity, StatusDto.class);
    }

    public StatusDto getStatusById(Long id) {
        return modelMapper.map(statusRepository.findById(id), StatusDto.class);
    }
}
