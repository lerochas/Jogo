package com.game.gamequake.service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.game.gamequake.entity.Game;

@Slf4j
@Service
public class ParserService {
	
	private static final String REGEX_JOGADOR = "(?s).*ClientUserinfoChanged.*";
    private static final String REGEX_KILLED = "(?s).*killed.*";
    private static final String REGEX_TRACO = "(?s).*--------";
    private static final String REGEX_MORREU = "(?<=killed\\s)(.*?)(?=\\sby)";
    private static final String REGEX_MATOU = "(?<=[0-9]:\\s)(.*?)(?=\\skilled)";
    private static final String REGEX_EXTRAIR = "(?<=\\sn\\\\)(.*?)(?=\\\\t)";

    public Map<String, Game> parseGame(final List<String> lines) {
        Map<String, Game> games = new HashMap<>();

        int contador = 0;

        int totalKills = 0;
        HashSet<String> players= new HashSet<>();
        Map<String, Integer> kills = new HashMap<>();

        for (String linha: lines) {
            if(validaLinha(REGEX_TRACO, linha)) {
                if (!players.isEmpty()){
                    contador++;
                    log.info("jogo_{}: players {}, totalKills {}, kills {}", contador ,players, totalKills, kills);

                    games.put("jogo_" + contador, new Game(totalKills, players, kills));

                    totalKills = 0;
                    players= new HashSet<>();
                    kills = new HashMap<>();

                }
            } else if(validaLinha(REGEX_JOGADOR, linha)){
                String player = extrairDado(REGEX_EXTRAIR, linha);
                players.add(player);
                kills.put(player, 0);
//                log.info("jogo_{}: {}", contador, linha);
            } else if(validaLinha(REGEX_KILLED, linha)){
                totalKills++;
                String matou = extrairDado(REGEX_MATOU, linha);

                if ("<world>".equals(matou)){
                    String morreu = extrairDado(REGEX_MORREU, linha);
                    kills.put(morreu, kills.get(morreu) -1);
                }else{
                    kills.put(matou, kills.get(matou)+1);
                }

//                log.info("{} matou {}", extrairDado(REGEX_MATOU, linha), extrairDado(REGEX_MORREU, linha));

            }
        }
        return games;
    }

    private String extrairDado(String regexMorreu, String linha) {
        Pattern pattern = Pattern.compile(regexMorreu);
        Matcher matcher = pattern.matcher(linha);
        return matcher.find()?matcher.group(1):null;
    }

    private boolean validaLinha(String lineEmpty, String linha) {
        return Pattern.matches(lineEmpty, linha);
    }
	
}
