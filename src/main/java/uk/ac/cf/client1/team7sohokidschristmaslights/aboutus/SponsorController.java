package uk.ac.cf.client1.team7sohokidschristmaslights.aboutus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
public class SponsorController {

    private final SponsorService sponsorService;

    public SponsorController(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }

//    @GetMapping("/signup")
//    public String showSignUpForm(Model model) {
//        model.addAttribute("sponsor", new Sponsor());
//        return "sponsor-signup";
//    }
//
//    @PostMapping("/signup")
//    public String processSignUpForm(@ModelAttribute Sponsor sponsor, BindingResult bindingResult, Model model) {
//        // Validation logic (if needed)
//        // Save to the database
//        sponsorRepository.saveSponsor(sponsor);
//
//        // Display success message
//        model.addAttribute("message", "Thank you for signing up as a sponsor. A member of our team will be in contact shortly.");
//
//        // Redirect to the home page or another appropriate page
//        return "redirect:/";
//    }

    @GetMapping("home/aboutus")
    public ModelAndView hostAboutUs() {
        ModelAndView modelAndView = new ModelAndView("about-us-page/aboutus2");

        return modelAndView;
    }

    @GetMapping("home/aboutus/sponsor-details")
    public ModelAndView hostSponsorDetails() {
        ModelAndView modelAndView = new ModelAndView("about-us-page/sponsor-details");

        modelAndView.addObject("sponsorList", sponsorService.getAllSponsors());

        return modelAndView;
    }
}