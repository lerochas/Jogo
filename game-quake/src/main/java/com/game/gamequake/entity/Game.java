package com.game.gamequake.entity;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonRootName;


@JsonRootName(value = "Game")
public class Game implements Serializable{
	private Integer totalKills;
	private String[] players;
	private Map<String,Integer> kills;
	
	public Integer getTotalKills() {
		return totalKills;
	}
	public void setTotalKills(Integer totalKills) {
		this.totalKills = totalKills;
	}
	public String[] getPlayers() {
		return players;
	}
	public void setPlayers(String[] players) {
		this.players = players;
	}
	public Map<String, Integer> getKills() {
		return kills;
	}
	public void setKills(Map<String, Integer> kills) {
		this.kills = kills;
	}
}
