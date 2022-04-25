package vttp2022.paf.fileupload.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Post {
    private String uploader;
    private String comment;
    private String imageType;
    private byte[] image;
    private Integer postId;

    public String getUploader() {
        return uploader;
    }
    public void setUploader(String uploader) {
        this.uploader = uploader;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getImageType() {
        return imageType;
    }
    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }
    public Integer getPostId() {
        return postId;
    }
    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public static Post populate(ResultSet rs) throws SQLException {
        final Post post = new Post();
        post.setPostId(rs.getInt("post_id"));
        post.setComment(rs.getString("comment"));
        post.setImageType(rs.getString("mediatype"));
        post.setImage(rs.getBytes("photo"));
        post.setUploader(rs.getString("uploader"));
        return post;
    }

}
