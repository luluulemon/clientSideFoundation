package vttp.csf.workshop37.server.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import vttp.csf.workshop37.server.services.TestPostService;

@Controller
public class PostController {
    
    @Autowired
    private TestPostService testSvc;

    @PostMapping(path="/api/post", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
    //@ResponseBody
    public String savePost 
        (@RequestPart MultipartFile image, @RequestPart String notes, Model model) throws IOException{
        
        InputStream is = image.getInputStream();
        testSvc.savePost(notes, is);
        
        model.addAttribute("Notes", notes);
        model.addAttribute("Image", image.getOriginalFilename() );
        
        return "testPage";
    }
}
