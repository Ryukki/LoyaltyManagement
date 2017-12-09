package com.polsl.jakubwidlak.LoyaltyManagement.Controllers;

import com.polsl.jakubwidlak.LoyaltyManagement.Entities.User;
import com.polsl.jakubwidlak.LoyaltyManagement.Services.UserData;
import com.polsl.jakubwidlak.LoyaltyManagement.Services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by Ryukki on 06.12.2017.
 */
@Controller
public class UserViewController {
    @Autowired
    private UserDataService userDataService;

    @RequestMapping(value = "/userview", method = RequestMethod.GET)
    public String userView(@RequestParam String user_mail, @RequestParam String password, Model model){
        Long user_id = userDataService.checkUserAndGetId(user_mail, password);
        UserData userData;
        String errorInfo;
        if(user_id>0){
            userData = userDataService.getUserDataWithId(user_id);
            model.addAttribute("userObject", userData);
            return "userview";
        }else if(user_id==0){
            errorInfo = "No user with this mail";
        }else {
            errorInfo = "Wrong password";
        }
        model.addAttribute("errorInfo", errorInfo);
        return "login";
    }

    @RequestMapping(value = "/registerPage", method = RequestMethod.GET)
    public String openRegisterPage(Model model){
        return "register";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.GET)
    public String registerUser(@RequestParam String mail, @RequestParam String password, @RequestParam String name, @RequestParam String surname, @RequestParam String referralCode, Model model){
        if(userDataService.checkIfUserAlreadyExist(mail)){
            model.addAttribute("errorInfo", "User already exists");
            return "login";
        }
        UserData userData = userDataService.getUserDataWithId(userDataService.addNewUser(mail, password, name, surname, referralCode));
        model.addAttribute("userObject", userData);
        return "userview";
    }
}
