package vttp.csf.day38server.models;


import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Contact {
    
    private String contactName;
    private String contactEmail;

    public String getContactName() {        return contactName;    }
    public void setContactName(String contactName) {        this.contactName = contactName;    }

    public String getContactEmail() {        return contactEmail;    }
    public void setContactEmail(String contactEmail) {        this.contactEmail = contactEmail;    }

    public static Contact create(JsonObject c){
        Contact contact = new Contact();
        contact.setContactEmail(c.getString("email"));
        contact.setContactName(c.getString("name"));

        return contact;
    }

    public static JsonObject docToJson(Document doc){
        JsonObjectBuilder contactJson = Json.createObjectBuilder();
        contactJson.add("name", doc.getString("name"))
                    .add("email", doc.getString("email"));

        return contactJson.build();
    }


}
