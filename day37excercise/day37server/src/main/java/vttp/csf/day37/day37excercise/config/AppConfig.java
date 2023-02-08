package vttp.csf.day37.day37excercise.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class AppConfig {
    
    @Value("${ACCESS_KEY}")
    String accessKey;

    @Value("${SECRET_KEY}")
    String secretKey;
    
    
    // @Bean
    // public AmazonS3 getS3Client() {
    //     // create credential
    //     System.out.println(secretKey);

    //     BasicAWSCredentials cred = new BasicAWSCredentials(System.getenv("ACCESS_KEY"), System.getenv("SECRET_KEY"));

    //     EndpointConfiguration epConfig = new EndpointConfiguration
    //                                     ("sgp1.digitaloceanspaces.com", "sgp1");
                                        
    //     return AmazonS3ClientBuilder.standard()
    //         .withEndpointConfiguration(epConfig)
    //         .withCredentials(new AWSStaticCredentialsProvider(cred))
    //         .build();
    // }


    @Bean
    public AmazonS3 createS3Client() {
        // Create a credential
        BasicAWSCredentials cred = new BasicAWSCredentials(accessKey, secretKey);

        EndpointConfiguration ep = new EndpointConfiguration(
            "sgp1.digitaloceanspaces.com", "sgp1");

        return AmazonS3ClientBuilder.standard()
            .withEndpointConfiguration(ep)
            .withCredentials(new AWSStaticCredentialsProvider(cred))
            .build();
    }

}
