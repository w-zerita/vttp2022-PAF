package vttp2022.paf.bff.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp2022.paf.bff.models.Contact;
import vttp2022.paf.bff.services.BffException;
import vttp2022.paf.bff.services.BffService;

@Controller
@RequestMapping(path="/")
public class BffController {

    @Autowired
    private BffService bSvc;

    @GetMapping(path="/")
    public ModelAndView getIndex() {
        ModelAndView mav = new ModelAndView();
        List<Contact> contacts = bSvc.getAllContacts();
        Contact c = new Contact();
        mav.addObject("contacts", contacts);
        mav.addObject("c", c);
        mav.setViewName("index");
        return mav;
    }

    @PostMapping(path="/")
    public ModelAndView postBff(@ModelAttribute Contact c) {

        System.out.printf(">>> BFF: %s\n", c.toString());

        ModelAndView mav = new ModelAndView();

        try {
            bSvc.addNewContact(c);
            mav.addObject("message", 
                "%s has been added to your BFF list".formatted(c.getName()));
            mav.addObject("c", c);
            mav.addObject("contacts", bSvc.getAllContacts());
        } catch (BffException e) {
            mav.addObject("c", c);
            mav.addObject("contacts", bSvc.getAllContacts());
            mav.addObject("message", 
                "Error: %s".formatted(e.getReason()));
            mav.setStatus(HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }

        mav.setViewName("index");
        return mav;
    }
}
