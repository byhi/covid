package hu.byhi.example.covidapp.controller;

import hu.byhi.example.covidapp.model.dto.StatusDto;
import hu.byhi.example.covidapp.model.filter.DateFilter;
import hu.byhi.example.covidapp.service.CovidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/covid/status")
public class CovidSatusController {

    @Autowired
    CovidService covidService;

    @GetMapping
    public ResponseEntity<ArrayList<StatusDto>> getAllStatus() {
        return ResponseEntity.ok(
                covidService.getAllStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusDto> getStatusById(@PathVariable Long id) {
        return ResponseEntity.ok(covidService.getStatusById(id));
    }

    @GetMapping("/filtered")
    public ResponseEntity<ArrayList<StatusDto>> getStatusById(@RequestBody DateFilter dateFilter) {
        return ResponseEntity.ok(covidService.getStatusByDateFilter(dateFilter));
    }

    @PostMapping
    public ResponseEntity<StatusDto> createStatus(@RequestBody StatusDto statusDto) {
        return ResponseEntity.ok(covidService.createStatus(statusDto));
    }

    @PutMapping
    public ResponseEntity<StatusDto> updateStatus(@RequestBody StatusDto statusDto) {
        return ResponseEntity.ok(covidService.updateStatus(statusDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStatus(@PathVariable Long id) {
        return ResponseEntity.ok("Deleted");
    }
}
