package vttp2022.paf.bff.repositories;

import static vttp2022.paf.bff.models.ConversionUtil.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.paf.bff.models.Bff;

@Repository
public class BffRepository implements Queries {

    @Autowired
    private JdbcTemplate template;

    public Optional<Bff> findBffByEmail(String email) {
        final SqlRowSet rs = template.queryForRowSet(
            SQL_SELECT_BFF_BY_EMAIL, email);
        if (!rs.next())
            return Optional.empty();
        return Optional.of(convert(rs));
    }

    public boolean insertBff(Bff bff) {
        int count = template.update(
            SQL_INSERT_NEW_BFF, 
            bff.getEmail(), bff.getName(), bff.getPhone(), bff.getDob(), bff.getStatus(), bff.getPassphrase()
        );
        System.out.printf(">>> Inserting %s to DB\n", bff.toString());
        return count == 1;
    }

    public List<Bff> selectAllBff() {
        List<Bff> bffs = new ArrayList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_ALL_BFF);
        while (rs.next()) {
            Bff bff = convert(rs);
            bffs.add(bff);
        }
        return bffs;
    }

    public boolean deletBffByEmail(String email) {
        int count = template.update(
            SQL_DELETE_BFF_BY_EMAIL, email);
        return count == 1;
    }
    
}
