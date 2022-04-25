package vttp2022.paf.fileupload;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;

@Configuration
public class AppConfig {

    @Value("${spaces.endpoint}")
    private String endpoint;

    @Value("${spaces.region}")
    private String region;
    
    @Bean
    public AmazonS3 createS3Client() {
        // final String accessKey = System.getenv("S3_ACCESS_KEY");
        // final String secretKey = System.getenv("S3_SECRET_KEY");

        Dotenv dotenv = Dotenv.configure().load();
        // Iterate over each environment entry
        // Note: entries in the host environment override entries in .env
        for (DotenvEntry e: dotenv.entries())
            System.out.println(e);

        final String accessKey = dotenv.get("S3_ACCESS_KEY");
        final String secretKey = dotenv.get("S3_SECRET_KEY");

        System.out.printf(">>> accessKey: %s\n", accessKey);
        System.out.printf(">>> secretKey: %s\n", secretKey);

        // S3 credentials
        final BasicAWSCredentials basiccred = 
            new BasicAWSCredentials(accessKey, secretKey);
        final AWSStaticCredentialsProvider credProv = 
            new AWSStaticCredentialsProvider(basiccred);
        final EndpointConfiguration endpointConfig = 
            new EndpointConfiguration(endpoint, region);

        return AmazonS3ClientBuilder
            .standard()
            .withEndpointConfiguration(endpointConfig)
            .withCredentials(credProv)
            .build();
    }
}
