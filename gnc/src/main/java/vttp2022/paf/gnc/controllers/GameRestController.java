package vttp2022.paf.gnc.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import vttp2022.paf.gnc.models.Comment;
import vttp2022.paf.gnc.models.Game;
import vttp2022.paf.gnc.services.GameService;

@RestController
@RequestMapping(path="/api/game", produces = MediaType.APPLICATION_JSON_VALUE)
public class GameRestController {

    @Autowired
    private GameService gameSvc;
    
    @GetMapping(path="/{gid}")
    public ResponseEntity<String> getGameAndCommentsById(
        @PathVariable Integer gid) {
            
        Optional<Game> opt = gameSvc.getComments(gid);
        JsonObjectBuilder objBuilder = Json.createObjectBuilder();
        if (opt.isEmpty())
            return ResponseEntity.status(404)
                .body(objBuilder.add("error", "Not Found: %s".formatted(gid))
                    .build().toString());
        Game g = opt.get();

        JsonArrayBuilder cArrBuilder = Json.createArrayBuilder();
        for (Comment c: g.getComments())
            cArrBuilder.add("/comment/%s".formatted(c.getCid()));
        JsonArray comments = cArrBuilder.build();

        JsonObject o = objBuilder
            .add("gid", g.getGid())
            .add("name", g.getName())
            .add("comments", comments)
            .build();
        return ResponseEntity.ok(o.toString());
    }
}
