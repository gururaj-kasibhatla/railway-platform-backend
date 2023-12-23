package com.honeywell.railway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.honeywell.railway.model.Train;

public interface TrainRepository extends JpaRepository<Train, Long> {

}
