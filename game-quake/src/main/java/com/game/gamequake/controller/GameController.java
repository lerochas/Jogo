package com.game.gamequake.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.gamequake.service.ReturnGames;

@Slf4j
@RestController
public class GameController {

	@Autowired
	private ReturnGames returnGames;

	@GetMapping(path= "/api", produces= MediaType.APPLICATION_JSON_VALUE)
    public String list() { 
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(returnGames.retornarJogos());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
            log.error("Erro GameController: ", e);
            return "Erro: ";
		}
    }
}