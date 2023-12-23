package com.honeywell.railway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.honeywell.railway.model.Train;
import com.honeywell.railway.service.TrainService;

@RestController
public class TrainController {

    @Autowired
    TrainService trainService;

    @GetMapping("/trains")
    private List<Train> getAllTrains() {
        return trainService.getTrains();
    }

    @PostMapping("/trains")
    private ResponseEntity<Train> saveTrain(@RequestBody Train train) {
        try {
            Train savedTrain = trainService.saveOrUpdateTrain(train);
            return new ResponseEntity<>(savedTrain, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/trains/{id}")
    public ResponseEntity<Train> updateTrain(@PathVariable Long id, @RequestBody Train updatedTrain) {
        try {
            Train existingTrain = trainService.getTrainById(id);

            if (existingTrain == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Update the existing train with the new information
            existingTrain.setName(updatedTrain.getName());
            existingTrain.setPlatform(updatedTrain.getPlatform());
            existingTrain.setTime(updatedTrain.getTime());

            // Save the updated train
            Train savedTrain = trainService.saveOrUpdateTrain(existingTrain);

            return new ResponseEntity<>(savedTrain, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
