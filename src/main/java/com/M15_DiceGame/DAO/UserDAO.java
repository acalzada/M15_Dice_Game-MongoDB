package com.M15_DiceGame.DAO;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.M15_DiceGame.Domain.User;

public interface UserDAO extends MongoRepository<User, Long>{
	
	public List<User> findAll();
	
	public User save(User user);
	
	//@Query(value="{}.sort({'user_id':-1}).limit(1)",fields="{'user_id':1,'_id':0}")
	public User findTopByOrderByUserIdDesc();
	
	public User findByUserId(Long id);
	
	/*
	public float getAverageMeanScore();
	
	public float getMinMeanScore();
	
	public float getMaxMeanScore();
	
	public List<User> findByMeanScore(float meanScore);
	
	
	 */
	
}