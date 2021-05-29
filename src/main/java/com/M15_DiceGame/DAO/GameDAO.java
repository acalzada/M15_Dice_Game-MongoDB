package com.M15_DiceGame.DAO;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.M15_DiceGame.Domain.Game;

public interface GameDAO extends MongoRepository<Game, Long>{

}