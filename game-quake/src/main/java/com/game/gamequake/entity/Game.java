package com.game.gamequake.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonRootName;


@JsonRootName(value = "Game")
public class Game implements Serializable{
	private Integer totalKills;
	private String[] players;
	private Map<String,Integer> kills;
	
	public Game(String string, String phraseList) {
		// TODO Auto-generated constructor stub
	}
	public Game() {
		// TODO Auto-generated constructor stub
	}
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
	public String getPhraseList() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setPhraseList(ArrayList arrayList) {
		// TODO Auto-generated method stub
		
	}
	public void setAction(String string) {
		// TODO Auto-generated method stub
		
	}
	public void setWho(String string) {
		// TODO Auto-generated method stub
		
	}
	public void setPlayer(String trim) {
		// TODO Auto-generated method stub
		
	}
	public void setCause(String substring) {
		// TODO Auto-generated method stub
		
	}
}
