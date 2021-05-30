package com.M15_DiceGame.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.M15_DiceGame.DAO.GameDAO;
import com.M15_DiceGame.DAO.UserDAO;
import com.M15_DiceGame.DTO.UserDTO;
import com.M15_DiceGame.Domain.Game;
import com.M15_DiceGame.Domain.User;

@Service
public class DiceGameServiceImpl implements DiceGameService{

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	GameDAO gameDAO;
	
	
	
	@Override
	public UserDTO addNewUser(UserDTO userDTO) {
		User user = new User(userDTO.getId(), userDTO.getName());
		Long id = getMaxUserId();
		if (id == null)
			id = (long) 0;
		user.setId(id+1);
		user = userDAO.save(user);
		return new UserDTO(user);
	}

	public Long getMaxUserId() {
		User user = userDAO.findTopByOrderByUserIdDesc();
		if (user == null)
			return null;
		return user.getId();
	};
	
	@Override
	public Game saveNewGame(Game game) {
		Long gameId = getMaxGameId();
		game.setId(gameId+1);
		return gameDAO.save(game);
	}
	
	
	public Long getMaxGameId() {
		Game game = gameDAO.findTopByOrderByGameIdDesc();
		if (game == null)
			return 0L;
		return game.getId();
	};
	
	@Override
	public UserDTO updateUser(UserDTO userDTO) {
		User user = userDAO.findByUserId(userDTO.getId());
		user.setName(userDTO.getName());
		user.setMeanScore(userDTO.getMeanScore());
		user.setGames(userDTO.getGames());
		user = userDAO.save(user);
		return new UserDTO(user);
	}

	
	@Override
	public UserDTO findById(Long id) {
		User user = userDAO.findByUserId(id);
		return new UserDTO(user);
	}
	
	
	
	
	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = userDAO.findAll();
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		users.forEach((user) -> usersDTO.add(new UserDTO(user.getId(), user.getName(), user.getMeanScore())));
		
		return usersDTO;
	}
	
	
	public List<UserDTO> getLastRanking() {
		User userMinScore = userDAO.findTopByOrderByMeanScore();
		float min_mean_score = userMinScore.getMeanScore(); 
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		userDAO.findByMeanScore(min_mean_score).forEach((user)-> usersDTO.add(new UserDTO(user)));
		return usersDTO;
	}
	/*
	@Override
	public List<UserDTO> getFirstRanking() {
		float max_mean_score = userDAO.getMaxMeanScore();
		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		userDAO.findByMeanScore(max_mean_score).forEach((user)->usersDTO.add(new UserDTO(user)));
		return usersDTO;
	}
	 
	 
	public float getAverageWinningScore() {
		return userDAO.getAverageMeanScore();
	}
	*/
}
