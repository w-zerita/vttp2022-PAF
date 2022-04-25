package vttp2022.paf.fileupload.repositories;

import java.sql.ResultSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2022.paf.fileupload.models.Post;

@Repository
public class PostRepository implements Queries {
    
    @Autowired
    private JdbcTemplate template;

    public Integer insertPost(Post post) {
        // returns the number of rows affected
        Integer uploadCount = template.update(
            SQL_INSERT_POST, post.getImage(), post.getComment(), 
            post.getUploader(), post.getImageType());
        return uploadCount;
    }

    public Optional<Post> getPostById(Integer postId) {
        return template.query(
            SQL_SELECT_POST_BY_POST_ID, 
            (ResultSet rs) -> {
                if(!rs.next())
                    return Optional.empty();
                final Post post = Post.populate(rs);
                return Optional.of(post);
            }, postId);
    }
}
