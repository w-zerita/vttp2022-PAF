package vttp2022.paf.gnc.repositories;

public interface Queries {

    // BEWARE OF SQL INJECTION!!! 
    // NEVER use concatenation to perform a database action, use ? instead!!!
    public static final String SQL_SELECT_GAME_BY_GID = "select * from game where gid = ?";

    // public static final String SQL_SELECT_COMMENT_BY_CID = "select * from comment where cid = ?";
    public static final String SQL_SELECT_COMMENT_BY_GID = "select * from comment where gid = ? limit ? offset ?";

    public static final String SQL_SELECT_COMMENT = "select * from comment limit ? offset ?";

}
