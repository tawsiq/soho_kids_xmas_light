package uk.ac.cf.client1.team7sohokidschristmaslights.aboutus;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
//@RequestMapping("/sponsor")
public class SponsorController {

    private final SponsorRepository sponsorRepository;

    public SponsorController(SponsorRepository sponsorRepository) {
        this.sponsorRepository = sponsorRepository;
    }

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("sponsor", new Sponsor());
        return "sponsor-signup";
    }

    @PostMapping("/signup")
    public String processSignUpForm(@ModelAttribute Sponsor sponsor, BindingResult bindingResult, Model model) {
        // Validation logic (if needed)
        // Save to the database
        sponsorRepository.saveSponsor(sponsor);

        // Display success message
        model.addAttribute("message", "Thank you for signing up as a sponsor. A member of our team will be in contact shortly.");

        // Redirect to the home page or another appropriate page
        return "redirect:/";
    }

    @GetMapping("home/aboutus")
    public ModelAndView hostaboutus() {
        ModelAndView modelAndView = new ModelAndView("about-us-page/aboutus2");

        return modelAndView;
    }
}