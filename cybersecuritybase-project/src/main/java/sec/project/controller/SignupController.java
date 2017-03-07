package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

@Controller
public class SignupController {
    
    @Autowired
    private SignupRepository signupRepository;

    @RequestMapping("/")
    public String defaultMapping() {
        return "redirect:/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(Model model, @RequestParam String name, @RequestParam String address, @RequestParam String dateOfBirth, @RequestParam String password) {
        //Flaw #2: personal details and password are all sent through POST as plaintext
        //OWASP A6: Sensitive data exposure
        signupRepository.save(new Signup(name, address, dateOfBirth, password));
        model.addAttribute("userName", name);
        model.addAttribute("userAddress", address);
        model.addAttribute("userDateOfBirth", dateOfBirth);
        model.addAttribute("userPassword", password);
        //Flaw #2: personal details and password are all sent through POST as plaintext
        //OWASP A6: Sensitive data exposure
        String loginurl = "redirect:/loginpage?name=" + name;
        return loginurl;
    }

}
