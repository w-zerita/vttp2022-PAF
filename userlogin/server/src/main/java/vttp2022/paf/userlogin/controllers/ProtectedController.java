package vttp2022.paf.userlogin.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp2022.paf.userlogin.models.User;

@Controller
@RequestMapping(path="/protected/{view}")
public class ProtectedController {
    
    @RequestMapping // POST + GET
    public ModelAndView postProtectedView(@PathVariable String view, 
        HttpSession sess) {

        System.out.println(">>>> view: " + view);

        User user = (User) sess.getAttribute("user");
        String username = (String) sess.getAttribute("username");

        ModelAndView mav = new ModelAndView();
        mav.setViewName(view);
        mav.addObject("user", user);
        mav.addObject("username", username);
        mav.setStatus(HttpStatus.OK);
        return mav;
    }
}
