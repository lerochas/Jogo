package com.game.gamequake.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonRootName;


@JsonRootName(value = "Game")
public class Game implements Serializable{
	private Integer totalKills;
	private HashSet<String> players;
	private Map<String,Integer> kills;

	public Game(){

	}

	public Game(Integer totalKills, HashSet<String> players, Map<String, Integer> kills) {
		this.totalKills = totalKills;
		this.players = players;
		this.kills = kills;
	}

	public Integer getTotalKills() {
		return totalKills;
	}
	public void setTotalKills(Integer totalKills) {
		this.totalKills = totalKills;
	}
	public HashSet<String> getPlayers() {
		return players;
	}
	public void setPlayers(HashSet<String> players) {
		this.players = players;
	}
	public Map<String, Integer> getKills() {
		return kills;
	}
	public void setKills(Map<String, Integer> kills) {
		this.kills = kills;
	}
	public String getPhraseList() {
		return null;
	}
	public void setAction(String string) {

	}
	public void setWho(String string) {
	}
	public void setPlayer(String trim) {
	}
	public void setCause(String substring) {
	}
}
