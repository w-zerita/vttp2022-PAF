package vttp2022.paf.gnc;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vttp2022.paf.gnc.models.Comment;
import vttp2022.paf.gnc.models.Game;
import vttp2022.paf.gnc.repositories.GameRepository;

@SpringBootTest
class GncApplicationTests {

	@Autowired
	private GameRepository gameRepo;

	@Test
	void shouldReturnAGame() {
		Optional<Game> opt = gameRepo.getGameById(10);
		assertTrue(opt.isPresent(), "gid = 10");
	}

	@Test
	void shouldReturnEmpty() {
		Optional<Game> opt = gameRepo.getGameById(10000);
		assertFalse(opt.isPresent(), "gid = 10000");
	}

	@Test
	void shouldReturnComments() {
		List<Comment> comments = gameRepo.getCommentsByGid(1, 10, 5);
		assertFalse(comments.isEmpty(), "gid = 1 limit 10 offset 5");
	}

	@Test
	void shouldReturnEmptyList() {
		List<Comment> comments = gameRepo.getCommentsByGid(0, 10, 5);
		assertTrue(comments.isEmpty(), "gid = 0 limit 10 offset 5");
	}

	@Test
	void shouldReturn42Comments() {
		List<Comment> comments = gameRepo.getCommentsByGid(10, 50, 0);
		assertTrue(comments.size() == 42, "gid = 10 limit 50 offset 0");
	}
}
