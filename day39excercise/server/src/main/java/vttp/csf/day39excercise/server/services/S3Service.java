package vttp.csf.day39excercise.server.services;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {
    
    @Autowired
    private AmazonS3 s3Client;

    public String upload(MultipartFile file, String id) throws IOException {

        // User data
        Map<String, String> userData = new HashMap<>();
        //userData.put("id", id);
        userData.put("uploadTime", (new Date()).toString());
        userData.put("originalFilename", file.getOriginalFilename());

        // Metadata of the file
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        metadata.setUserMetadata(userData);

        // Create a put request
        PutObjectRequest putReq = new PutObjectRequest(
            "lufirstbucket", // bucket name
            "myobjects/%s".formatted(id), //key
            file.getInputStream(), //inputstream
            metadata);

        // Allow public access
        putReq.withCannedAcl(CannedAccessControlList.PublicRead);

        s3Client.putObject(putReq);

        return id;
    }
}
