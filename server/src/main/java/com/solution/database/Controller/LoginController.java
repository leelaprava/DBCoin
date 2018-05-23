package com.solution.database.Controller;

import com.solution.database.Model.AppUser;
import com.solution.database.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping("/Login")
    @CrossOrigin(origins = "http://localhost:3000")
    public AppUser loginCheck(@RequestBody String data){
        return loginService.checkLogin(data);
    }
}
