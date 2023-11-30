package com.example.courtreservationwebsite.Controller;

import com.example.courtreservationwebsite.DAO.CourtDAO;
import com.example.courtreservationwebsite.DAO.UserDAO;
import com.example.courtreservationwebsite.Model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class LoginController {

    private UserDAO userDAO;
    private CourtDAO courtDAO;

    public LoginController(UserDAO userDAO, CourtDAO courtDAO) {
        this.userDAO = userDAO;
        this.courtDAO = courtDAO;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if(user != null && user.isLogin()){
            if(user.getRole().equals("user"))
                return "redirect:/user";
            else if(user.getRole().equals("admin"))
                return "redirect:/admin";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpSession httpSession, @RequestParam("username") String username, @RequestParam("password") String password, Model model) throws IOException {
        User user = new User();
        user.setUsername(username);
        user.setPassowrd(password);
        boolean checkLogin = userDAO.checkLogin(user);
        if(checkLogin){
            user.setLogin(true);
            httpSession.setAttribute("user", user);
            if(user.getRole().equals("user"))
                return "redirect:/user";
            else if(user.getRole().equals("admin"))
                return "redirect:/admin";
        }
        return "login-incorrect";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession, Model model) throws  IOException{
        User user = (User) httpSession.getAttribute("user");
        if(user != null){
            user.setLogin(false);
        }
        httpSession.invalidate();
        return "redirect:/login";
    }

}
