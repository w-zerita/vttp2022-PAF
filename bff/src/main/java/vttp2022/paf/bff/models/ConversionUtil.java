package vttp2022.paf.bff.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ConversionUtil {

    public static Bff convert(SqlRowSet rs) {
        Bff bff = new Bff(rs.getString("email"), rs.getString("name"), 
            rs.getString("phone"), rs.getDate("dob"), 
            rs.getString("status"), rs.getString("email"));
        return bff;
    }
    
}
