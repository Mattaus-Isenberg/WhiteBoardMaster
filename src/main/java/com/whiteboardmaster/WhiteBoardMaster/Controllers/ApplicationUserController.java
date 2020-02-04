package com.whiteboardmaster.WhiteBoardMaster.Controllers;

import com.whiteboardmaster.WhiteBoardMaster.Models.ApplicationUser;
import com.whiteboardmaster.WhiteBoardMaster.Models.ApplicationUserRepository;
import com.whiteboardmaster.WhiteBoardMaster.Models.DiagramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class ApplicationUserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ApplicationUserRepository userRepository;

    @Autowired
    DiagramRepository diagramRepository;

    /*
                        POST ROUTES
     */
    @PostMapping("/user/register")
    public RedirectView register(HttpServletRequest request, String userName, String password, String firstName, String lastName) {

        // create user and add to database
        ApplicationUser newUser = new ApplicationUser(userName, passwordEncoder.encode(password), firstName, lastName);
        userRepository.save(newUser);

        // auto-login feature after creating account
        try {
            request.login(userName, password);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return new RedirectView("/");
    }

    @GetMapping("/profile")
    public String displayProfileDetails(Principal p, Model m) {

        if (p != null) {
            ApplicationUser user = userRepository.findByUserName(p.getName());
            m.addAttribute(user);

        }
        return "profile";
    }
}
