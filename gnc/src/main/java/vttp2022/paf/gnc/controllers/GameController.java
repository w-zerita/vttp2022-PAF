package vttp2022.paf.gnc.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp2022.paf.gnc.models.Game;
import vttp2022.paf.gnc.services.GameService;

@Controller
@RequestMapping(path="/game", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
public class GameController {

    @Autowired
    private GameService gameSvc;

    @GetMapping(path="/{gid}")
    public String getGame(@PathVariable Integer gid, Model model) {
        Optional<Game> opt = gameSvc.getComments(gid);
        if (opt.isEmpty()) {
            model.addAttribute("errMsg", "Game not Found!!!");
            return "error";
        }
        Game game = opt.get();
        model.addAttribute("game", game);
        model.addAttribute("comment", game.getComments());
        return "game";
    }
    
}
