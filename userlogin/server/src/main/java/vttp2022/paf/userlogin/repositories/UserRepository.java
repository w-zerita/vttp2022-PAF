package vttp2022.paf.userlogin.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.paf.userlogin.models.User;

@Repository
public class UserRepository implements Queries {

    @Autowired 
    private JdbcTemplate template;

    public int countUsersMatchingNameAndPassword(final User user) {

        SqlRowSet rs = template.queryForRowSet(
            SQL_CHECK_VALID_USERNAME_AND_PASSWORD, user.getUsername(), user.getPassword()
        );
        while (!rs.next())
            return 0;
        return rs.getInt("user_count");
    }
    
}
