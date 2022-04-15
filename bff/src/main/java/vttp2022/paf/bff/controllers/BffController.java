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

import vttp2022.paf.bff.models.Bff;
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
        List<Bff> bffs = bSvc.getAllBffs();
        Bff bff = new Bff();
        mav.addObject("bffs", bffs);
        mav.addObject("bff", bff);
        mav.setViewName("index");
        return mav;
    }

    @PostMapping(path="/add")
    public ModelAndView postBff(@ModelAttribute Bff bff) {

        System.out.printf(">>> BFF: %s\n", bff.toString());

        ModelAndView mav = new ModelAndView();

        try {
            bSvc.addNewBff(bff);
            mav.addObject("message", 
                "%s has been added to your BFF list".formatted(bff.getName()));
            mav.addObject("bff", bff);
            mav.addObject("bffs", bSvc.getAllBffs());
        } catch (BffException e) {
            mav.addObject("message", 
                "Error: %s".formatted(e.getReason()));
            mav.setStatus(HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }

        mav.setViewName("index");
        return mav;
    }
}
