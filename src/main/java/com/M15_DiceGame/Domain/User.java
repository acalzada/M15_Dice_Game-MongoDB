package com.M15_DiceGame.Domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mongodb.lang.NonNull;

@Document(collection="users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

	@Id
	@Field(name="user_id")
	protected Long id;
	
	@Field()
	protected String name;
	
	@Field()
	protected LocalDateTime registration_date;
	
	@Field(name="meanScore")
	@NonNull
	protected float meanScore;
	
	protected List <Game> games;
	
	// Constructors
	
	public User() {
	}
	
	
	public User(String name) {
		this.name = name;
	}

	public User(Long id, String name, float meanScore) {
		this.id = id;
		this.name = name;
		this.meanScore = meanScore;
	}
	
	
	// Getters & Setters
	

	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Get the User's unique Id
	 * @return the User's id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Get USer's name
	 * @return the User's name
	 */
	public String getName() {
		return name;
	}


	/**
	 * Set the User's name
	 * @param name the name to assign to the user.
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * Get the date the User registered into the system
	 * @return the registration_date
	 */
	public LocalDateTime getRegistration_date() {
		return registration_date;
	}


	/**
	 * Get all games a User has played and are registered in the system (not deleted previously).
	 * @return all the User's games
	 */
	public List<Game> getGames() {
		return games;
	}


	public void setGames(List<Game> games) {
		this.games = games;
	}


	/**
	 * @return the meanScore
	 */
	public float getMeanScore() {
		return meanScore;
	}


	/**
	 * @param meanScore the meanScore to set
	 */
	public void setMeanScore(float meanScore) {
		this.meanScore = meanScore;
	}
	
}
