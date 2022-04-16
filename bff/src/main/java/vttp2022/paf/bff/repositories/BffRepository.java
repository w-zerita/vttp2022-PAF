package vttp2022.paf.bff.repositories;

import static vttp2022.paf.bff.models.ConversionUtil.convert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.paf.bff.models.Contact;

@Repository
public class BffRepository implements Queries {

    @Autowired
    private JdbcTemplate template;

    public Optional<Contact> findContactByEmail(String email) {
        final SqlRowSet rs = template.queryForRowSet(
            SQL_SELECT_BFF_BY_EMAIL, email);
        if (!rs.next())
            return Optional.empty();
        return Optional.of(convert(rs));
    }

    public boolean insertContact(Contact c) {
        int count = template.update(
            SQL_INSERT_NEW_BFF, 
            c.getEmail(), c.getName(), c.getPhone(), c.getDob(), c.getStatus(), c.getPassphrase()
        );
        System.out.printf(">>> Inserting %s to DB\n", c.toString());
        return count == 1;
    }

    public List<Contact> selectAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_SELECT_ALL_BFF);
        while (rs.next()) {
            Contact c = convert(rs);
            contacts.add(c);
        }
        return contacts;
    }

    public boolean deleteContactByEmail(String email) {
        int count = template.update(
            SQL_DELETE_BFF_BY_EMAIL, email);
        return count == 1;
    }
    
}
