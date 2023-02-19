package vttp.csf.oldAssess.carousellPage.models;

import java.util.Date;


import jakarta.json.Json;
import jakarta.json.JsonObject;

public class ItemPost {
    
    private String id;
    private String name;
    private Date date;
    private String email;
    private Integer phone;
    private String title;
    private String description;
    private String image;

    public String getId() {        return id;    }
    public void setId(String id) {        this.id = id;    }
    
    public String getName() {        return name;    }
    public void setName(String name) {        this.name = name;    }

    public Date getDate() {        return date;    }
    public void setDate(Date date) {        this.date = date;    }

    public String getEmail() {        return email;    }
    public void setEmail(String email) {        this.email = email;    }

    public Integer getPhone() {        return phone;    }
    public void setPhone(Integer phone) {        this.phone = phone;    }

    public String getTitle() {        return title;    }
    public void setTitle(String title) {        this.title = title;    }
    public String getDescription() {        return description;    }
    public void setDescription(String description) {        this.description = description;    }

    public String getImage() {        return image;    }
    public void setImage(String image) {        this.image = image;    }

    public static ItemPost create
    (String name, String email, String title, String description, String id, Integer phone){

        ItemPost itemPost = new ItemPost();
        itemPost.setId(id);
        itemPost.setName(name);
        itemPost.setDate(new Date());
        itemPost.setTitle(title);
        itemPost.setDescription(description);
        itemPost.setEmail(email);
        itemPost.setImage("https://lufirstbucket.sgp1.digitaloceanspaces.com/carousell" + id);
        if(phone == null){  itemPost.setPhone(0);}
        else{   itemPost.setPhone(phone);   }
        return itemPost;
    }

    public JsonObject toJson(ItemPost post){
        return  Json.createObjectBuilder().add(name, post.getName())
                                        .add("id", id)
                                        .add("Date", date.toString())
                                        .add("email", email)
                                        .add("phone", phone)
                                        .add("title", title)
                                        .add("description", description)
                                        .add("imageURL", image)
                                        .build();
    }

        
    
}
