package sec.project.controller;

import java.beans.Statement;
import java.sql.ResultSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

@Controller
public class LoginController {
    
    @Autowired
    private SignupRepository signupRepository;

    @RequestMapping(value = "/loginpage", params = {"name"}, method = RequestMethod.GET)
    public String loadForm(Model model, @RequestParam(required=true, value="name") String userName) {
        
        String query = "SELECT account_balance FROM user_data WHERE user_name = " + userName + "'";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jcg-JPA");
	EntityManager em = emf.createEntityManager();

	em.getTransaction().begin();

	List<Signup> users = em.createNativeQuery(query).getResultList();
	System.out.println(users);
        
        //List<Signup> users = signupRepository.findByName(userName);
        Signup user = users.get(0);
        
        model.addAttribute("userName", user.getName());
        model.addAttribute("userAddress", user.getAddress());
        model.addAttribute("userDateOfBirth", user.getDateOfBirth());
        model.addAttribute("userPassword", user.getPassword());
        return "loginpage";
    }

    @RequestMapping(value = "/loginpage", params = {"name"}, method = RequestMethod.POST)
    public String submitForm(Model model, @RequestParam(required=true, value="name") String userName, @RequestParam String address, @RequestParam String dateOfBirth, @RequestParam String password) {

        System.out.println("Name check: " + userName);
        List<Signup> users = signupRepository.findByName(userName);
        Signup user = users.get(0);
        model.addAttribute("userName", userName);
        model.addAttribute("userAddress", address);
        model.addAttribute("userDateOfBirth", dateOfBirth);
        model.addAttribute("userPassword", password);
        System.out.println("User check: " + user.getName());
        user.setAddress(address);
        user.setDateOfBirth(dateOfBirth);
        user.setPassword(password);
        
        signupRepository.save(user);
        
        String loginurl = "redirect:/loginpage?name=" + userName;
        return loginurl;
    }

}

