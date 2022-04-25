package vttp2022.paf.fileupload.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp2022.paf.fileupload.models.Post;
import vttp2022.paf.fileupload.repositories.PostRepository;

@RestController
@RequestMapping(path = "/upload")
public class PostRestController {
    
    @Autowired
    private PostRepository pRepo;

    @GetMapping(path = "{postId}/image")
    public ResponseEntity<byte[]> getUploadedImage(@PathVariable Integer postId) {

        Optional<Post> opt = pRepo.getPostById(postId);
        if (opt.isEmpty())
            return ResponseEntity.notFound().build();

        final Post p = opt.get();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", p.getImageType());
        headers.set("Cache-Control", "max-age=604800");

        return ResponseEntity.ok()
            .headers(headers)
            .body(p.getImage());
    }
}
