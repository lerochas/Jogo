package com.game.gamequake.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.game.gamequake.entity.Game;


@Service
public class ParserService {
	
	private static final String KILL = "Kill";
    private static final String KILLED = "killed";
    private static final String LINE_EMPTY     = "------------------------------------------------------------";

    public List<Game> getGame(final List<String> lines) {
        List<Game> games = new ArrayList<>();
        Game game = null;
        for (String line: lines) {
            if(LINE_EMPTY.equals(parserLineToGame(line))) {
                if(game != null) {
                    if(!game.getPhraseList().isEmpty()) {
                        games.add(new Game("game_"+(games.size()+1), game.getPhraseList()));
                    }
                }
                game = new Game() ;
                game.setPhraseList(new ArrayList<>());
            }
            try {
                Game gamep = parserLineToGameP(line);
                games.add(gamep);
            } catch (Exception e) {
                System.out.print(e.getMessage());            
                }
        }
        return games;
    }

    private Game parserLineToGameP(String line) {
		// TODO Auto-generated method stub
		return null;
	}

	private String parserLineToGame(String line) {
        String[] splitLine = line.split(":");

        if(splitLine.length >= 2 && splitLine[1].contains(LINE_EMPTY)) {
            return LINE_EMPTY;
        }

        return null;
    }

    private Game parserLineToPhrase(String line){
        String[] splitLine = line.split(":");
        final Game phrase = new Game();

        if(Pattern.matches("(?s).*killed.*", line)){
        	findPhrase(splitLine[3], phrase);
            return phrase;
        } else {
            throw new UnprocessableLineException();
        }
    }

    private void findPlayer(final String line, final Game phrase) {
        if(line.contains("<world>")) {
            phrase.setAction("killed");
            phrase.setWho("<world>");
            phrase.setPlayer(line.substring(getBeginPlayerIndex(line, KILLED.length()), line.indexOf("by")).trim());
        } else {
            phrase.setAction("kill");
            phrase.setWho(line.substring(getBeginPlayerIndex(line, KILLED.length()), line.indexOf("by")).trim());
            phrase.setPlayer(line.substring(0, getBeginPlayerIndex(line, 0)).trim());
        }
    }

    private void findPhrase(String line, Game phrase) {
        findPlayer(line, phrase);
        findMOD(line, phrase);
    }

    private void findMOD(final String line, final Game phrase) {
        phrase.setCause(line.substring(line.indexOf("MOD")));
    }

    private int getBeginPlayerIndex(final String line, final int plus) {
        return line.indexOf(KILLED)+plus;
    }

	
}
