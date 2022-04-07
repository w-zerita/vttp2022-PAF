package vttp2022.paf.gnc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.paf.gnc.models.Game;
import vttp2022.paf.gnc.repositories.GameRepository;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepo;

    public Optional<Game> getComments(Integer gid) {
        Optional<Game> opt = gameRepo.getGameById(gid);
        if (opt.isEmpty())
            return Optional.empty();
        Game g = opt.get();
        g.setComments(gameRepo.getCommentsByGid(gid));
        return Optional.of(g);
    }
    
}
