package fr.grp404.projetjee.persistence.domain;

public class UserGame {
	private Integer userId;
	private Integer gameId;
	private String startDateHour;
	
	public UserGame(Integer userId, Integer gameId, String startDateHour) {
		this.userId = userId;
		this.gameId = gameId;
		this.startDateHour = startDateHour;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public int getGameId() {
		return gameId;
	}
	
	public int getUserName() {
		//TODO : recuperer le nom de l'user en fct de l'id 
		return userId;
	}
	
	public int getGameName() {
		//TODO : recuperer le nom du jeu en fct de l'id
		return gameId;
	}
	
	public String getStartDateHour() {
		return startDateHour;
	}
}
