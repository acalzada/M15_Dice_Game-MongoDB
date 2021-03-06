package com.M15_DiceGame.Service;

import java.util.List;

import com.M15_DiceGame.DTO.UserDTO;
import com.M15_DiceGame.Domain.Game;
import com.M15_DiceGame.Domain.User;

public interface DiceGameService {
	
	
	public UserDTO addNewUser(UserDTO user);
	
	public Long getMaxUserId();
	
	
	public User findById(Long id);
	
	public UserDTO updateUser(UserDTO user);
	
	
	public Game saveNewGame(Game game);
	
	
	public List<UserDTO> getAllUsers();
	
	
	public float getAverageWinningScore();

	
	public List<UserDTO> getLastRanking();

	
	public List<UserDTO> getFirstRanking();
	
}
