package com.game.gamequake.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.gamequake.entity.Game;

@Service
public class ReturnGames {
	
	@Autowired
	private ReaderFileService readerFileService;
	@Autowired
	private ParserService parserService;
	
	public Map<String, Game> retornarJogos(){
		List<String> linhas= readerFileService.ler();		
		return parserService.parseGame(linhas);
		
		
	}
	
}
