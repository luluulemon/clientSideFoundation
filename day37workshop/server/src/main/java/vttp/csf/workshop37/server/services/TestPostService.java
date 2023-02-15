package vttp.csf.workshop37.server.services;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.csf.workshop37.server.repositories.TestPostRepository;

@Service
public class TestPostService {
    
    @Autowired
    private TestPostRepository testRepo;

    public void savePost(String comments, InputStream image){
        
        String postId = UUID.randomUUID().toString().substring(0, 8);
        testRepo.savePost(postId, comments, image);
    }

}
