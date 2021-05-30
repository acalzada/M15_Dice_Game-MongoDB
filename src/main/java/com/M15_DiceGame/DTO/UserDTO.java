package com.M15_DiceGame.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;


import com.M15_DiceGame.Domain.Game;
import com.M15_DiceGame.Domain.User;

public class UserDTO extends User {
	
	public UserDTO() {
	}
	
	public UserDTO(User user) {
		this.setId(user.getId());
		if (user.getName() == null) {
			this.setName("ANONIM");
		}else {
			this.setName(user.getName());
		}
		this.setMeanScore(user.getMeanScore());
		this.setRegistration_date(user.getRegistration_date());
		this.games = user.getGames();
		if (this.games == null)
			this.games = new ArrayList<Game>();
	}
	
	public UserDTO(Long userId, String name) {
		this.userId = userId;
		this.name = name;
	}
	
	public UserDTO(Long userId, String name, float meanScore) {
		this.userId = userId;
		this.name = name;
		this.meanScore = meanScore;
	}
	
	public Game play() {
		// Limit values for a dice roll output
		int min = 1;
		int max = 6;
		
		// Roll dices
		int dice1 = (int) Math.floor(Math.random()*(max-min+1)+min);
		int dice2 = (int) Math.floor(Math.random()*(max-min+1)+min);
		
		Game game = new Game(this.getId(), dice1, dice2);

		System.out.println("=========   Dins el PLAY DTO     ============");
		System.out.println("=========   GAME obtingut:");
		System.out.println(game.toString());
		
		// Add new game to the user profile
		System.out.println("ABANS AFEGIR GAME a GAMES");
		this.games.forEach((g)->System.out.println(g.toString()));
		this.games.add(game);
		System.out.println("DESPRES AFEGIR GAME a GAMES");
		this.games.forEach((g)->System.out.println(g.toString()));
		// Compute the new total Score based on the historic Score and the new Game result.
		float numberOfGames = this.games.size();
		float gameResult = game.isGame_won() ? 1.0f : 0.0f;  // Used to convert the Boolean value to float to be able to compute the next computations
		float newMeanScore = this.getMeanScore()*((numberOfGames-1)/numberOfGames) + (gameResult/numberOfGames*100);
		System.out.println("meanScore= " + newMeanScore);
		// Update User's new mean Score
		this.setMeanScore(newMeanScore);
		
		return game;
	}
	
	/**
	 * Delete ALL the games a user has registered in the system.
	 */
	public void deleteAllGames() {
		this.games.clear();
		this.meanScore = 0;
	}
	
	/**
	 * Set the registration time to the dTO. The database already sets timeStamp for new users.
	 * @param timeStamp
	 */
	protected void setRegistration_date(LocalDateTime timeStamp) {
		this.registration_date = timeStamp;
	}
	
	
	
}
