package com.polsl.jakubwidlak.LoyaltyManagement.Controllers;

import com.polsl.jakubwidlak.LoyaltyManagement.Services.UserData;
import com.polsl.jakubwidlak.LoyaltyManagement.Services.UserDataService;
import com.polsl.jakubwidlak.LoyaltyManagement.View.AdminPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserViewController {
    @Autowired
    private UserDataService userDataService;
    @Autowired
    private AdminPanel adminPanel;
    private Long currentUserId;

    private Model putUserDataInModel(Model model){
        UserData userData = userDataService.getUserDataWithId(this.currentUserId);
        model.addAttribute("userObject", userData);
        return model;
    }

    @RequestMapping(value = "/adminlogin", method = RequestMethod.POST)
    public String loginDmin(@RequestParam String password){
        if(password.equals("1234")){
            adminPanel.setupAdminGui();
        }
        return "login";
    }

    @RequestMapping(value = "/userview", method = RequestMethod.POST)
    public String userView(@RequestParam String user_mail, @RequestParam String password, Model model){
        Long user_id = userDataService.checkUserAndGetId(user_mail, password);
        String errorInfo;
        if(user_id>0){
            this.currentUserId = user_id;
            model = putUserDataInModel(model);
            return "userview";
        }else if(user_id==0){
            errorInfo = "No user with this mail";
        }else {
            errorInfo = "Wrong password";
        }
        model.addAttribute("errorInfo", errorInfo);
        this.currentUserId = 0L;
        return "login";
    }

    @RequestMapping(value = "/registerPage", method = RequestMethod.GET)
    public String openRegisterPage(){
        return "register";
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String openLoginPage(){
        this.currentUserId = 0L;
        return "login";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String registerUser(@RequestParam String mail, @RequestParam String password, @RequestParam String name, @RequestParam String surname, @RequestParam String referralCode, Model model){
        if(userDataService.checkIfUserAlreadyExist(mail)){
            model.addAttribute("errorInfo", "User already exists");
            this.currentUserId = 0L;
            return "login";
        }
        this.currentUserId = userDataService.addNewUser(mail, password, name, surname, referralCode);
        model = putUserDataInModel(model);
        return "userview";
    }

    @RequestMapping(value = "/review", method = RequestMethod.GET)
    public String addReview(Model model){
        userDataService.addReview(this.currentUserId);
        model = putUserDataInModel(model);
        return "userview";
    }

    @RequestMapping(value = "/rate", method = RequestMethod.GET)
    public String addRating(Model model){
        userDataService.addRating(this.currentUserId);
        model = putUserDataInModel(model);
        return "userview";
    }

    @RequestMapping(value = "/buy", method = RequestMethod.GET)
    public String addTransaction(@RequestParam Double price,@RequestParam Integer pointsSpent, Model model){
        if(pointsSpent==null)
            pointsSpent = 0;
        userDataService.addTransaction(this.currentUserId, pointsSpent, price);
        model = putUserDataInModel(model);
        return "userview";
    }
}
