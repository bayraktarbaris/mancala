package com.baris.mancala;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MancalaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testMancalaController() throws Exception {
        //Test get page when table isn't ready
        mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().isOk()).andExpect(model().attributeDoesNotExist("board"));
        //Test initialize new game
        mockMvc.perform(MockMvcRequestBuilders.post("/new")).andExpect(status().isOk());
        //Test get page when game table is ready
        mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().isOk()).andExpect(model().attributeExists("board"));
        //Test reinitializing the game and having an error
        mockMvc.perform(MockMvcRequestBuilders.post("/new")).andExpect(status().isBadRequest());
        //Test move
        mockMvc.perform(MockMvcRequestBuilders.post("/play/0")).andExpect(status().isOk());
        //Test illegal move
        mockMvc.perform(MockMvcRequestBuilders.post("/play/0")).andExpect(status().isBadRequest());

        //////////////////////////////////////
        //  Playing rest of an example game //
        //////////////////////////////////////

        mockMvc.perform(MockMvcRequestBuilders.post("/play/1")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/7")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/0")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/12")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/5")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/12")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/11")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/4")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/11")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/5")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/3")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/12")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/11")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/5")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/4")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/10")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/1")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/8")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/5")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/3")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/5")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/4")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/11")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/10")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/5")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/2")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/12")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/0")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/10")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/5")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/4")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/5")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/2")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/7")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/3")).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/play/5")).andExpect(status().isOk());

        //At this point, game is almost over but winner should still be missing.
        mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().isOk()).andExpect(model().attributeDoesNotExist("winner"));

        //Making the finishing move
        mockMvc.perform(MockMvcRequestBuilders.post("/play/4")).andExpect(status().isOk());

        //And finally, game is over and we have a winner.
        mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().isOk()).andExpect(model().attributeExists("winner"));
    }
}
