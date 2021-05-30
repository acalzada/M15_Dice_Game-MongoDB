package com.M15_DiceGame.Domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mongodb.lang.NonNull;

@Document(collection="games")
public class Game {
	
	@Id
	@Field(name="game_id")
	private Long gameId;
	
	@Field(name="user_id")
	private Long userId;
	
	@Field()
	@NonNull
	private int dice1_Value;
	
	@Field()
	@NonNull
	private int dice2_Value;
	
	@Field()
	@NonNull
	private boolean game_won;
	
	@Transient
	int winningGameValue = 7;  // Value to get adding the two dices in order to win the game.
	
	/*
	@DBRef
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") //retorna només id
    @JsonIdentityReference(alwaysAsId = true) //retorna només id
	private User user;
	 */
	
	// Constructors
	
	protected Game() {
		
	}
	
	public Game(Long id, int dice1_val, int dice2_val) {
		this.userId = id;
		this.dice1_Value = dice1_val;
		this.dice2_Value = dice2_val;
		
		// Game Winning Rule
		this.game_won = (this.dice1_Value + this.dice2_Value) == this.winningGameValue;
	}

	// GETTERS & SETTERS
	
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return gameId;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.gameId = id;
	}

	/**
	 * @return the dice1_Value
	 */
	public int getDice1_Value() {
		return dice1_Value;
	}

	/**
	 * @param dice1_Value the dice1_Value to set
	 */
	public void setDice1_Value(int dice1_Value) {
		this.dice1_Value = dice1_Value;
	}

	/**
	 * @return the dice2_Value
	 */
	public int getDice2_Value() {
		return dice2_Value;
	}

	/**
	 * @param dice2_Value the dice2_Value to set
	 */
	public void setDice2_Value(int dice2_Value) {
		this.dice2_Value = dice2_Value;
	}

	/**
	 * @return the game_won
	 */
	public boolean isGame_won() {
		return game_won;
	}

	/**
	 * @param game_won the game_won to set
	 */
	public void setGame_won(boolean game_won) {
		this.game_won = game_won;
	}

	/**
	 * @return the winningGameValue
	 */
	public int getWinningGameValue() {
		return winningGameValue;
	}

	/**
	 * @param winningGameValue the winningGameValue to set
	 */
	public void setWinningGameValue(int winningGameValue) {
		this.winningGameValue = winningGameValue;
	}
		
	public String toString() {
		return "Game ['Id= " + this.getId() + ", UId= " + this.userId +", D1= " + this.dice1_Value + ", D2= " + this.dice2_Value + ", Result= " + this.game_won + "']";
	}
}
