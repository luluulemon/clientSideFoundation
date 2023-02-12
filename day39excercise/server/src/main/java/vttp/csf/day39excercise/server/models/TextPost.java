package vttp.csf.day39excercise.server.models;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class TextPost {
    
    private String id;
    private String title;
    private String text;

    public String getId() {        return id;    }
    public void setId(String id) {        this.id = id;    }

    public String getTitle() {        return title;    }
    public void setTitle(String title) {        this.title = title;    }

    public String getText() {        return text;    }
    public void setText(String text) {        this.text = text;    }

    public static JsonObject docToJson(Document doc){
        
        JsonObjectBuilder post = Json.createObjectBuilder();
        if(!doc.getString("id").equals("log")){
                                post.add("id", doc.getString("id"))
                                    .add("title", doc.getString("title"))
                                    .add("text", doc.getString("text"))
                                    .add("likes", doc.getInteger("likes", 0))
                                    .add("dislikes", doc.getInteger("dislikes", 0));
                                }
        return post.build();
    }
}
