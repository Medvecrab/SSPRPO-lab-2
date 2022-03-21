package tselebrovskii.controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import tselebrovskii.form.TourForm;
import tselebrovskii.service.TourService;
import tselebrovskii.service.GuideService;
import tselebrovskii.service.PlaceService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TourController {

    @Autowired
    TourService tourService;

    @Autowired
    GuideService guideService;

    @Autowired
    PlaceService placeService;

    @GetMapping(value = {"/", "/index-admin"})
    public String indexPage(Model model, HttpServletRequest httpRequest) {
        model.addAttribute("tours", tourService.findAll());
        model.addAttribute("converter", new Base64());
        if (httpRequest.getRequestURI().equals("/"))
            return "index";
        else
            return "index-admin";
    }

    @GetMapping("/tour/create")
    public String savePage(Model model) {
        model.addAttribute("guides", guideService.findAll());
        model.addAttribute("places", placeService.findAll());
        model.addAttribute("tourForm", new TourForm());
        return "create-tour";
    }

    @GetMapping("/tour/edit/{id}")
    public String editPage(Model model, @PathVariable Long id) {
        model.addAttribute("tour", tourService.findById(id));
        model.addAttribute("guides", guideService.findAll());
        model.addAttribute("places", placeService.findAll());
        model.addAttribute("tourForm", new TourForm());
        return "edit-tour";
    }

    @GetMapping("/tour/{id}")
    public String tourPage(Model model, @PathVariable Long id) {
        model.addAttribute("tour", tourService.findById(id));
        model.addAttribute("converter", new Base64());
        return "tour";
    }

    @PostMapping("tour/create/")
    public RedirectView saveTour(@ModelAttribute TourForm form) {
        tourService.save(form);
        return new RedirectView("/index-admin");
    }

    @PostMapping("tour/edit/{id}")
    public RedirectView updateTour(@PathVariable Long id, @ModelAttribute TourForm form) {
        tourService.update(id, form);
        return new RedirectView("/index-admin");
    }

    @GetMapping("tour/delete/{id}")
    public RedirectView deleteTour(@PathVariable Long id) {
        tourService.deleteById(id);
        return new RedirectView("/index-admin");
    }

}
