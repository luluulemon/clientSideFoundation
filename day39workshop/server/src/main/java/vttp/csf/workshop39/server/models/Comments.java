package vttp.csf.workshop39.server.models;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Comments {
    
    private int id;
    private String comments;
    private long timeStamp;

    public int getId() {        return id;    }
    public void setId(int id) {        this.id = id;    }
    
    public String getComments() {        return comments;    }
    public void setComments(String comments) {        this.comments = comments;    }

    public long getTimeStamp() {        return timeStamp;    }
    public void setTimeStamp(long timeStamp) {        this.timeStamp = timeStamp;    }

    public static JsonObject docToJson(Document commentDoc){
        return Json.createObjectBuilder()
                .add("id", commentDoc.getInteger("id", 0))
                .add("comments", commentDoc.getString("comments"))
                .build();                        
    }

}
