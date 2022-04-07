package vttp2022.paf.gnc.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Comment {

    private String cid;
    private String user;
    private Integer rating;
    private String ctext;
    private Integer gid;

    public String getCid() {
        return cid;
    }
    public void setCid(String cid) {
        this.cid = cid;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public String getCtext() {
        return ctext;
    }
    public void setCtext(String ctext) {
        this.ctext = ctext;
    }
    public Integer getGid() {
        return gid;
    }
    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public static Comment create(SqlRowSet rs) {
        Comment c = new Comment();
        c.setCid(rs.getString("c_id"));
        c.setUser(rs.getString("user"));
        c.setRating(rs.getInt("rating"));
        c.setCtext(rs.getString("c_text"));
        c.setGid(rs.getInt("gid"));
        return c;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("cid", getCid())
            .add("user", getUser())
            .add("rating", getRating())
            .add("ctext", getCtext())
            .add("gid", getGid())
            .build();
    }
}
