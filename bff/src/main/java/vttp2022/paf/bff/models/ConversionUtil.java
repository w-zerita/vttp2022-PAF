package vttp2022.paf.bff.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ConversionUtil {

    public static Contact convert(SqlRowSet rs) {
        Contact c = new Contact(rs.getString("email"), rs.getString("name"), 
            rs.getString("phone"), rs.getDate("dob"), 
            rs.getString("status"), rs.getString("email"));
        return c;
    }
    
}
