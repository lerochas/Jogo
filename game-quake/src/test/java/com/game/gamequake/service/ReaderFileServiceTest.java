package com.game.gamequake.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= {ReaderFileService.class})
public class ReaderFileServiceTest {
	
	@Autowired
	private ReaderFileService readerFileService;
	
	@Test
	public void lerArquivo() {
		ReflectionTestUtils.setField(readerFileService, "nomeArquivo", "games.log");
		ReflectionTestUtils.setField(readerFileService, "caminhoArquivo", "C:\\Users\\Leandro Rocha\\Jogo\\game-quake\\");
		
		List<String> linhas= readerFileService.ler();
		
		assertNotNull(linhas);
	}

}
