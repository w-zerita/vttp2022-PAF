package vttp2022.paf.fileupload.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import vttp2022.paf.fileupload.models.Post;
import vttp2022.paf.fileupload.repositories.PostRepository;

@Controller
@RequestMapping(path= "/upload")
public class PostController {

    @Autowired
    private PostRepository pRepo;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView postFileUpload(@RequestParam("image") MultipartFile image, 
        @RequestPart String uploader, @RequestPart String comment) {
        
        // String imageName = image.getOriginalFilename();
        // Long imageSize = image.getSize();
        String imageType = image.getContentType();
        byte[] buff = new byte[0];

        try {
            buff = image.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Post p = new Post();
        p.setComment(comment);
        p.setUploader(uploader);
        p.setImageType(imageType);
        p.setImage(buff);
        Integer uploadCount = pRepo.insertPost(p);

        ModelAndView mav = new ModelAndView();
        mav.addObject("uploadCount", uploadCount);
        mav.setViewName("result");
        return mav;
    }

    @GetMapping(path= "/{postId}")
    public ModelAndView getPostById(@PathVariable Integer postId) {

        Optional<Post> opt = pRepo.getPostById(postId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("post", opt.get());
        mav.setViewName("post");
        return mav;
    }
}
