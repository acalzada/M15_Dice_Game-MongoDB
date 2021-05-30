package com.M15_DiceGame.Domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mongodb.lang.NonNull;

@Document(collection="users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
	
	@Id
	@Field(name="user_id")
	protected Long userId;
	
	@Field()
	protected String name;
	
	@Field()
	@CreatedDate
	protected LocalDateTime registration_date;
	
	@Field(name="meanScore")
	@NonNull
	protected float meanScore;
	
	@DBRef
	protected List <Game> games;
	
	// Constructors
	
	public User() {
	}
	
	public User(Long id, String name) {
		this.userId = id;
		this.name = name;
	}
	
	public User(String name) {
		this.name = name;
	}

	public User(Long user_id, String name, float meanScore) {
		this.userId = user_id;
		this.name = name;
		this.meanScore = meanScore;
	}
	
	
	// Getters & Setters
	

	public void setId(Long user_id) {
		this.userId = user_id;
	}
	
	/**
	 * Get the User's unique Id
	 * @return the User's id
	 */
	public Long getId() {
		return userId;
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
	
	public String toString() {
		String out = "User [ userId:" + this.getId() + ", name:'" + this.getName() + "', meanScore:" + this.getMeanScore() + "]";
		out = "---------------------------------\n" + out + "\n---------------------------------\n"; 
		return out;
	}
	
}
