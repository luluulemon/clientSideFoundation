package vttp.csf.workshop39.server.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Hero {
    
    private String name;
    private String description;
    private String thumbnail;
    private int id;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    

    // convert Json (extracted details) to obj
    public static Hero create(JsonObject heroJson){
        Hero hero = new Hero();
        hero.setDescription(heroJson.getString("description"));
        hero.setId(heroJson.getInt("id"));
        hero.setName(heroJson.getString("name"));
        hero.setThumbnail(heroJson.getString("image"));
        return hero;
    }


    // convert cache obj to Json
    public JsonObject toJson(){
        return
        Json.createObjectBuilder().add("id", id)
                                .add("image", thumbnail)
                                .add("description", description)
                                .add("name", name)
                                .build();
    }


    // accepts the JsonObject in the results array
    public static JsonObject getDetails(JsonObject Character){
        String imageUrl = Character.getJsonObject("thumbnail").getString("path") + "."
                            + Character.getJsonObject("thumbnail").getString("extension");

        return
        Json.createObjectBuilder().add("name", Character.getString("name"))
                                .add("description", Character.getString("description"))
                                .add("id", Character.getInt("id"))
                                .add("image", imageUrl)
                                .build();
    }
}
//     "results": [
                // {
                // "id": 1010727,
                // "name": "Spider-dok",
                // "description": "",
                // "modified": "1969-12-31T19:00:00-0500",
                // "thumbnail": {
                        // "path": "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available",
                        // "extension": "jpg"

