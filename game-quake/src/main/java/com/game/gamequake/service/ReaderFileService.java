package com.game.gamequake.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReaderFileService {

	@Value(value="${file.name}")
	private String nomeArquivo;
	@Value(value="${file.path}")
	private String caminhoArquivo;
	
	public List<String> ler(){
		List<String> listar = new ArrayList<String>();
		try {
			FileInputStream arquivo = new FileInputStream(caminhoArquivo+nomeArquivo);
			InputStreamReader input = new InputStreamReader(arquivo);
			BufferedReader br = new BufferedReader(input);
			String linha;
			do {
				linha = br.readLine();
				if(linha != null) {
					listar.add(linha);
				}
			} while(linha != null);
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("Erro ReaderFileService: ", e);
			System.out.println("Erro ao ler o arquivo");
		}
		return listar;
	}
}