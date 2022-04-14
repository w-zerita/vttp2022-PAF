package vttp2022.paf.userlogin.repositories;

public interface Queries {

    public static final String SQL_CHECK_VALID_USERNAME_AND_PASSWORD = 
        "select count(*) as user_count from user where username = ? and password = sha1(?)";
    
}
