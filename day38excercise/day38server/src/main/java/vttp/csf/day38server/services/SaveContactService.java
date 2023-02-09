package vttp.csf.day38server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.JsonArray;
import vttp.csf.day38server.repositories.ContactRepository;

@Service
public class SaveContactService {
    
    @Autowired
    private ContactRepository contactRepo;

    public void saveContacts(JsonArray contacts){
        contactRepo.saveContacts(contacts);
    }

    public JsonArray fetchContacts(){
        return contactRepo.fetchContacts();
    }
}
