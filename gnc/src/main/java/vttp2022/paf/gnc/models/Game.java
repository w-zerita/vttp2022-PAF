package vttp2022.paf.gnc.models;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Game {

    private Integer gid;
    private String name;
    private Integer year;
    private Integer ranking;
    private Integer usersRated; // users_rated
    private String url;
    private String image;
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getUsersRated() {
        return usersRated;
    }

    public void setUsersRated(Integer usersRated) {
        this.usersRated = usersRated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Game() {
    }

    public static Game create(SqlRowSet rs) {
        Game g = new Game();
        g.setGid(rs.getInt("gid"));
        g.setName(rs.getString("name"));
        g.setYear(rs.getInt("year"));
        g.setRanking(rs.getInt("ranking"));
        g.setUsersRated(rs.getInt("users_rated"));
        g.setUrl(rs.getString("url"));
        g.setImage(rs.getString("image"));
        return g;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("gid", getGid())
            .add("name", getName())
            .add("year", getYear())
            .add("ranking", getRanking())
            .add("usersRated", getUsersRated())
            .add("url", getUrl())
            .add("image", getImage())
            .build();
    }
}
