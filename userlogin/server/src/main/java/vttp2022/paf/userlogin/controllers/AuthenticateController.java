package vttp2022.paf.userlogin.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp2022.paf.userlogin.models.User;
import vttp2022.paf.userlogin.services.UserService;

@Controller
@RequestMapping
public class AuthenticateController {

    @Autowired
    private UserService uSvc;

    @GetMapping
    public ModelAndView getUserLogin() {
        ModelAndView mav = new ModelAndView();
        User user = new User();
        mav.addObject("user", user);
        mav.setViewName("index");
        return mav;
    }

    @PostMapping(path="/authenticate")
    public ModelAndView postUserLogin(@ModelAttribute User user, 
        HttpSession sess) {

        String username = user.getUsername();

        System.out.printf("Input Values >>> username: %s, password: %s\n", 
            username, user.getPassword());

        ModelAndView mav = new ModelAndView();

        if (!uSvc.authenticate(user)) {
            mav.setStatus(HttpStatus.UNAUTHORIZED);
            mav.setViewName("error");
            return mav;
        } else {
            sess.setAttribute("user", user);
            sess.setAttribute("username", username);
            mav = new ModelAndView("redirect:/protected/welcome");
            return mav;
        }
    }

    @GetMapping(path="/logout")
    public ModelAndView getLogout(HttpSession sess) {
        sess.invalidate();
        ModelAndView mav = new ModelAndView("redirect:/");
        return mav;
    }
    
}
