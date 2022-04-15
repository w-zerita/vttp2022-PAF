package vttp2022.paf.bff.repositories;

public interface Queries {

    public static final String SQL_SELECT_BFF_BY_EMAIL = 
        "select * from bff where email = ?";
    
    public static final String SQL_INSERT_NEW_BFF = 
        "insert into bff (email, name, phone, dob, status, passphrase) values(?, ?, ?, ?, ?, sha1(?))";
    
    public static final String SQL_SELECT_ALL_BFF = 
        "select * from bff";

    public static final String SQL_DELETE_BFF_BY_EMAIL = 
        "delete from bff where email = ?";
}
