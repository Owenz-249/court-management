package com.example.courtreservationwebsite.Controller;

import com.example.courtreservationwebsite.DAO.CourtDAO;
import com.example.courtreservationwebsite.DAO.UserDAO;
import com.example.courtreservationwebsite.Model.Court;
import com.example.courtreservationwebsite.Model.SearchForm;
import com.example.courtreservationwebsite.Model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private CourtDAO courtDAO;

    @GetMapping("/user")
    public String home(Model model, HttpSession httpSession) throws IOException {
        boolean isUserLoggin = false;
        User user = (User) httpSession.getAttribute("user");
        if(user != null && user.isLogin() && user.getRole().equals("user")){
            isUserLoggin = true;
            model.addAttribute("loggedIn", isUserLoggin);
            model.addAttribute("user", user);
            return "user";
        }
        else
            return "redirect:/login";
    }

    @GetMapping("/profile")
    public String getInfo(HttpSession httpSession, Model model) throws IOException{
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile")
    public String saveEditUser(HttpSession httpSession,
                               @RequestParam("name") String name,
                               @RequestParam("username") String username,
                               @RequestParam("email") String email,
                               @RequestParam("address") String address,
                               //@RequestParam("dob") Date dob,
                               @RequestParam(value = "dob", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dob,
                               Model model) {
        User user = (User) httpSession.getAttribute("user");
        if(userDAO.isEmailExists(email, user.getId()) || userDAO.isUsernameExists(username, user.getId())){
            return "error";
        }
        user.setName(name);
        user.setUsername(username);
        user.setEmail(email);
        user.setAddress(address);
        user.setDob(dob);

        if(userDAO.updateUser(user)){
//            model.addAttribute("user", user);
            return "redirect:/profile";
        }
        return "error";
    }

    @GetMapping("/search-court")
    public String getSearchCourtPage(Model model) {
        model.addAttribute("searchForm", new SearchForm());
        return "search-court";
    }

    @PostMapping("/search-court")
    public String searchCourt(@ModelAttribute SearchForm searchForm, Model model) {
        int kipTime = searchForm.getKipTime();
        String courtType = searchForm.getCourtType();
        String dayBegin = searchForm.getDayBegin();
        String dayEnd = searchForm.getDayEnd();

        List<Court> searchResults = courtDAO.searchCourts(kipTime, courtType, dayBegin, dayEnd);

        model.addAttribute("searchResults", searchResults);
        return "list-court-result";
    }

    @GetMapping("/booking")
    public String getBookingPage(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");

        List<Court> selectedCourts = new ArrayList<>();
        if (model.containsAttribute("selectedCourts")) {
            List<Integer> selectedCourtIds = (List<Integer>) model.getAttribute("selectedCourts");
            for (Integer courtId : selectedCourtIds) {
//                Court court = courtDAO.getCourtById(courtId);
//                selectedCourts.add(court);
            }
        }
        model.addAttribute("user", user);
        model.addAttribute("selectedCourts", selectedCourts);

        return "booking";
    }

}
