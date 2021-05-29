package com.M15_DiceGame.DAO;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.M15_DiceGame.Domain.User;

public interface UserDAO extends MongoRepository<User, Long>{
	
	public List<User> findAll();
	
	public float getAverageMeanScore();
	
	public float getMinMeanScore();
	
	public float getMaxMeanScore();
	
	public List<User> findByMeanScore(float meanScore);
	
	public User save(User user);

}