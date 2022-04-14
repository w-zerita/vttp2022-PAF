package vttp2022.paf.gnc;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import vttp2022.paf.gnc.models.Comment;
import vttp2022.paf.gnc.models.Game;
import vttp2022.paf.gnc.repositories.GameRepository;
import vttp2022.paf.gnc.services.GameService;

@SpringBootTest
public class GameServiceTest {

    @Autowired
    private GameService gameSvc;

    // mock game repository
    @MockBean 
	private GameRepository gameRepo;

    @Test
    public void shouldReturn200() {
        int gid = 1;

        // mock the GameRepository to pass test
        Game g = new Game();
        List<Comment> comments = new ArrayList<>();
        Comment c = new Comment();

        c.setGid(gid);
        comments.add(c);
        g.setName("abc");

        Mockito.when(gameRepo.getGameById(gid))
            .thenReturn(Optional.of(g));
        Mockito.when(gameRepo.getCommentsByGid(gid))
            .thenReturn(comments);

        Optional<Game> opt = gameSvc.getComments(gid);
        assertTrue(opt.isPresent(), "should find gid: %d".formatted(gid));
    }

    @Test
    public void shouldReturnOptionalEmpty() {
        int gid = -500;

        // mock the GameRepository
        Mockito.when(gameRepo.getGameById(gid))
            .thenReturn(Optional.empty());

        // use the mock GameRepository
        Optional<Game> opt = gameSvc.getComments(gid);
        assertFalse(opt.isPresent(), "should not find gid: %d".formatted(gid));
    }
    
}
