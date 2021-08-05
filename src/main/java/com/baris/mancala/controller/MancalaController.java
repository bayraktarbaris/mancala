package com.baris.mancala.controller;

import com.baris.mancala.exception.MancalaException;
import com.baris.mancala.exception.UnsupportedOperationException;
import com.baris.mancala.service.IGameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MancalaController {
    private final IGameService IGameService;

    public MancalaController(IGameService IGameService) {
        this.IGameService = IGameService;
    }

    @GetMapping("/")
    public @ResponseBody
    ModelAndView getBoard() {
        Map<String, Object> model = new HashMap<>();
        model.put("state", IGameService.getCurrentState().name());
        try {
            model.put("board", IGameService.getBoard());
        } catch (UnsupportedOperationException e) {
            model.put("board", null);
        }
        try {
            model.put("winner", IGameService.declareWinner());
        } catch (UnsupportedOperationException e) {
            model.put("winner", null);
        }

        return new ModelAndView("home", model);
    }

    @PostMapping("/new")
    public @ResponseBody
    ResponseEntity newGame() {
        try {
            IGameService.startNewGame();
            return ResponseEntity.ok().build();
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/play/{pit}")
    public @ResponseBody
    ResponseEntity play(@PathVariable("pit") int pit) {
        try {
            IGameService.play(pit);
            return ResponseEntity.ok().build();
        } catch (MancalaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
