package vttp.csf.day36server.demo.models;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Game {
    
    private int gid;
    private String name;
    private int year;
    private int ranking;
    private int users_rated;
    private String url;
    private String image_url;

    public int getGid() {        return gid;    }
    public void setGid(int gid) {        this.gid = gid;    }

    public String getName() {        return name;    }
    public void setName(String name) {        this.name = name;    }

    public int getYear() {        return year;    }
    public void setYear(int year) {        this.year = year;    }

    public int getRanking() {        return ranking;    }
    public void setRanking(int ranking) {        this.ranking = ranking;    }

    public int getUsers_rated() {        return users_rated;    }
    public void setUsers_rated(int users_rated) {        this.users_rated = users_rated;    }

    public String getUrl() {        return url;    }
    public void setUrl(String url) {        this.url = url;    }

    public String getImage_url() {        return image_url;    }
    public void setImage_url(String image_url) {        this.image_url = image_url;    }

    public static Game create(Document doc){
        Game game = new Game();
        game.setGid(doc.getInteger("gid", 0));
        game.setName(doc.getString("name"));
        game.setYear(doc.getInteger("year", 0));
        game.setRanking(doc.getInteger("ranking", 0));
        game.setUsers_rated(doc.getInteger("users_rated", 0));
        game.setUrl(doc.getString("url"));
        game.setImage_url(doc.getString("image"));
        return game;
    }

    // "name" : "Dragonmaster",
    // "year" : NumberInt(1981),
    // "ranking" : NumberInt(3095),
    // "users_rated" : NumberInt(523),
    // "url" : "https://www.boardgamegeek.com/boardgame/2/dragonmaster",
    // "image" : "https://cf.geekdo-images.com/micro/img/GjYccOggc9GURTZwKLB3EsLh19A=/fit-in/64x64/pic4001505.jpg"

    public JsonObject toJsonSummary(){
        return Json.createObjectBuilder().add("name", name)
                                .add("gid", gid)
                                .build();
    }

    public JsonObject toJson(){
        return Json.createObjectBuilder().add("name", name)
                                .add("gid", gid)
                                .add("year", year)
                                .add("ranking", ranking)
                                .add("users_rated", users_rated)
                                .add("url", url)
                                .add("image_url", image_url)
                                .build();
    }

}
