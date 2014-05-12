package br.com.ita.bdic3;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/admin")
    public String loginHome(Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        return "view.admin";
    }
}
