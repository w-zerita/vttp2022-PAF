package vttp2022.paf.fileupload.controllers;

import java.io.IOException;
import java.util.UUID;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path = "upload/s3")
public class PostS3RestController {

    @Autowired
    private AmazonS3 s3;

    @PostMapping
    public ResponseEntity<String> postS3(@RequestParam MultipartFile image, 
        @RequestPart String comment, @RequestPart String uploader) {
        
        JsonObject result;
        String imageType = image.getContentType();
        byte[] buff = new byte[0];

        try {
            buff = image.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String uuid = UUID.randomUUID().toString().substring(0, 8);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(imageType);
        metadata.setContentLength(buff.length);

        try {
            PutObjectRequest putReq = new PutObjectRequest(
                "zspace97", "%s/images/%s".formatted(uploader, uuid), 
                image.getInputStream(), metadata);
            putReq.setCannedAcl(CannedAccessControlList.PublicRead);
            s3.putObject(putReq);
            String url = s3.getUrl("zspace97", "%s/images/%s".formatted(uploader, uuid)).toString();
            result = Json.createObjectBuilder()
                .add("objId", uuid)
                .add("url", url)
                .build();
            return ResponseEntity.ok(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
            result = Json.createObjectBuilder()
                .add("error", e.getMessage())
                .build();
            return ResponseEntity.status(500).body(result.toString());
        }
    }
    
}
