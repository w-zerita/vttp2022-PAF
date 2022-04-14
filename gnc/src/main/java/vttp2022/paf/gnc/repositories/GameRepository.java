package vttp2022.paf.gnc.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.paf.gnc.models.Comment;
import vttp2022.paf.gnc.models.Game;

import static vttp2022.paf.gnc.repositories.Queries.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
public class GameRepository {

    @Autowired
    private JdbcTemplate template;

    public List<Comment> getCommentsByGid(Integer gid) {
        return getCommentsByGid(gid, Integer.MAX_VALUE, 0);
    }

    public List<Comment> getCommentsByGid(Integer gid, Integer limit) {
        return getCommentsByGid(gid, limit, 0);
    }

    public List<Comment> getCommentsByGid(Integer gid, Integer limit, Integer offset) {

        final List<Comment> result = new LinkedList<>();

        final SqlRowSet rs = template.queryForRowSet(
            SQL_SELECT_COMMENT_BY_GID, gid, limit, offset
        );

        while(rs.next()) {
            Comment c = Comment.create(rs);
            result.add(c);
        }
        return result;
    }

    public Optional<Game> getGameById(Integer gid) {

        System.out.printf("++++ GameRepository.getGameByGid: %d\n", gid);

        final SqlRowSet result = template.queryForRowSet(
            // select * from game where gid = <gid>
            SQL_SELECT_GAME_BY_GID, gid
        );

        if (!result.next())
            return Optional.empty();

        return Optional.of(Game.create(result));
    }
    
}
