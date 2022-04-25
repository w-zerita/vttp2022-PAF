package vttp2022.paf.fileupload.repositories;

public interface Queries {

    public static final String SQL_INSERT_POST = 
        "insert into post(photo, comment, uploader, mediatype) values (?, ?, ?, ?)";

    public static final String SQL_SELECT_POST_BY_POST_ID = 
        "select post_id, photo, comment, uploader, mediatype from post where post_id = ?";
    
}
