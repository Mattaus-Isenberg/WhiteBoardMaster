package com.whiteboardmaster.WhiteBoardMaster.Controllers;

import com.whiteboardmaster.WhiteBoardMaster.Models.ApplicationUser;
import com.whiteboardmaster.WhiteBoardMaster.Models.ApplicationUserRepository;
import com.whiteboardmaster.WhiteBoardMaster.Models.Diagram;
import com.whiteboardmaster.WhiteBoardMaster.Models.DiagramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class DiagramController {

    @Autowired
    ApplicationUserRepository userRepository;

    @Autowired
    DiagramRepository diagramRepository;


    /*
                    POST ROUTES
    */
    // CREATES NEW DIAGRAM AND STORES TO DATABASE
    @PostMapping("boards/create")
    public RedirectView createDiagram(Principal p, String problemDomain, String algorithm, String pseudoCode, String bigONotation, String verification, String code, String edgeCases, String inputAndOutput, String visual) {

        if (p != null) {
            // get user and create new diagram.  Store in database
            ApplicationUser user = userRepository.findByUserName(p.getName());
            Diagram newDiagram = new Diagram(user, problemDomain, algorithm, pseudoCode, bigONotation, verification, code, edgeCases, inputAndOutput, visual);
            user.addDiagram(newDiagram);

            userRepository.save(user);
        }
        return new RedirectView("/boards");
    }


    /*
                    GET ROUTES
    */
    @GetMapping("/boards")
    public String displayBoards() {

        return "boards";
    }

    // GETS SINGLE SELECTED WHITEBOARD
    @GetMapping("/boards/getBoard")
    public String getBoard(Model m, Diagram selectedDiagram) {

        Diagram diagramFromDB = diagramRepository.getOne(selectedDiagram.getId());
        m.addAttribute("diagram", diagramFromDB);

        return "boards";
    }


}
