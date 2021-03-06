package com.game.gamequake.service;

import com.game.gamequake.entity.Game;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ParserServiceTest {

    @InjectMocks
    private ParserService parserService;

    private static List<String> linhas;


    @BeforeClass
    public static void setUp() {
        linhas = Arrays.asList(
                "  0:00 ------------------------------------------------------------",
                "  0:00 InitGame: \\sv_floodProtect\\1\\sv_maxPing\\0\\sv_minPing\\0\\sv_maxRate\\10000\\sv_minRate\\0\\sv_hostname\\Code Miner Server\\g_gametype\\0\\sv_privateClients\\2\\sv_maxclients\\16\\sv_allowDownload\\0\\dmflags\\0\\fraglimit\\20\\timelimit\\15\\g_maxGameClients\\0\\capturelimit\\8\\version\\ioq3 1.36 linux-x86_64 Apr 12 2009\\protocol\\68\\mapname\\q3dm17\\gamename\\baseq3\\g_needpass\\0",
                " 15:00 Exit: Timelimit hit.",
                " 20:34 ClientConnect: 2",
                " 20:34 ClientUserinfoChanged: 2 n\\Isgalamido\\t\\0\\model\\xian/default\\hmodel\\xian/default\\g_redteam\\\\g_blueteam\\\\c1\\4\\c2\\5\\hc\\100\\w\\0\\l\\0\\tt\\0\\tl\\0",
                " 20:37 ClientUserinfoChanged: 2 n\\Isgalamido\\t\\0\\model\\uriel/zael\\hmodel\\uriel/zael\\g_redteam\\\\g_blueteam\\\\c1\\5\\c2\\5\\hc\\100\\w\\0\\l\\0\\tt\\0\\tl\\0",
                " 20:37 ClientBegin: 2",
                " 20:37 ShutdownGame:",
                "  20:37 ------------------------------------------------------------" ,
                " 20:37 InitGame: \\sv_floodProtect\\1\\sv_maxPing\\0\\sv_minPing\\0\\sv_maxRate\\10000\\sv_minRate\\0\\sv_hostname\\Code Miner Server\\g_gametype\\0\\sv_privateClients\\2\\sv_maxclients\\16\\sv_allowDownload\\0\\bot_minplayers\\0\\dmflags\\0\\fraglimit\\20\\timelimit\\15\\g_maxGameClients\\0\\capturelimit\\8\\version\\ioq3 1.36 linux-x86_64 Apr 12 2009\\protocol\\68\\mapname\\q3dm17\\gamename\\baseq3\\g_needpass\\0" ,
                " 20:38 ClientConnect: 2" ,
                " 20:38 ClientUserinfoChanged: 2 n\\Isgalamido\\t\\0\\model\\uriel/zael\\hmodel\\uriel/zael\\g_redteam\\\\g_blueteam\\\\c1\\5\\c2\\5\\hc\\100\\w\\0\\l\\0\\tt\\0\\tl\\0" ,
                " 20:38 ClientBegin: 2",
                " 20:40 Item: 2 weapon_rocketlauncher",
                " 20:40 Item: 2 ammo_rockets",
                " 20:42 Item: 2 item_armor_body",
                " 20:54 Kill: 1022 2 22: <world> killed Isgalamido by MOD_TRIGGER_HURT",
                "  20:37 ------------------------------------------------------------");
    }

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void retornaUmJogoComSucesso() {

        // Prepare
        Map<String, Game> stringGameMap = parserService.parseGame(linhas);

        assertNotNull(stringGameMap);
        assertTrue(stringGameMap.containsKey("jogo_1"));
        assertEquals(Integer.valueOf(1), stringGameMap.get("jogo_2").getTotalKills());
    }

    @Test
    public void retornaVazio() {
        List<String> logErrado = Arrays.asList("essa não é", "uma lista", "de log");
        Map<String, Game> stringGameMap = parserService.parseGame(logErrado);

        assertEquals(0, stringGameMap.size());
    }

}