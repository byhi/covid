package hu.byhi.example.covidapp.controller;

import hu.byhi.example.covidapp.model.dto.StatusDto;
import hu.byhi.example.covidapp.service.CovidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/covid/status")
public class CovidSatusController {

    @Autowired
    CovidService covidService;

    @GetMapping("/all")
    public ResponseEntity<ArrayList<StatusDto>> getAllStatus() {
        return ResponseEntity.ok(
                covidService.getAllStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusDto> getStatusById(@PathVariable Long id) {
        return ResponseEntity.ok(covidService.getStatusById(id));
    }
}
