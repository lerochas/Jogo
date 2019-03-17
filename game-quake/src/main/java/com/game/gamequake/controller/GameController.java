package com.game.gamequake.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.game.gamequake.entity.Game;
import com.game.gamequake.service.ReturnGames;

@RestController
public class GameController {
	
	@Autowired
	private ReturnGames returnGames;
	
	@GetMapping(path= "/api", produces= MediaType.APPLICATION_JSON_VALUE)
    public String list() { 
		ObjectMapper mapper = new ObjectMapper();
//		mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
		try {
			return mapper.writeValueAsString(returnGames.retornarJogos());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "erro";
		}
		
		
    }
}
