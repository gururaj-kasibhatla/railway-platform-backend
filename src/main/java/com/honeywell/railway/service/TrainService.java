package com.honeywell.railway.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honeywell.railway.model.Train;
import com.honeywell.railway.repository.TrainRepository;

@Service
public class TrainService {

	@Autowired
	TrainRepository trainRepository;

	public List<Train> getTrains() {
		return trainRepository.findAll();
	}

	public Train saveOrUpdateTrain(Train train) {
		return trainRepository.save(train);
	}
	
	public Train getTrainById(Long id) {
        return trainRepository.findById(id).orElse(null);
    }

}
