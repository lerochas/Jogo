package com.game.gamequake.controller;

import com.game.gamequake.entity.Game;
import com.game.gamequake.service.ReturnGames;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@ContextConfiguration(classes = {GameController.class})
public class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReturnGames returnGames;

    private static Map<String, Game> jogos;

    @Before
    public void initMocks() {
        when(this.returnGames.retornarJogos()).thenReturn(jogos);
    }

    @BeforeClass
    public static void setUp() {
        jogos = new HashMap<String, Game>() {{
            put("jogo_1", new Game(null, null, null));
            put("jogo_2", new Game(null, null, null));
        }};
    }

    @Test
    public void deveRetornarJogos() throws Exception {

        mockMvc.perform(get("/games"))
                .andExpect(status().isOk());
    }

    @Test
    public void deveRetornarUmJogo() throws Exception {

        ResultActions resultActions = mockMvc.perform(get("/games/1"))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    mvcResult.getResponse().getContentAsString().equals("{\"totalKills\":null,\"players\":null,\"kills\":null,\"phraseList\":null}");
                });
    }
}