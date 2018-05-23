package com.solution.database.Controller;

import com.solution.database.Model.AppUser;
import com.solution.database.Service.RegisterService;
import com.solution.database.Utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    RegisterService registerService;

    @RequestMapping("/RegisterUser")
    @CrossOrigin(origins = "http://localhost:3000")
    public AppUser registerUser(@RequestBody String data){
        return registerService.registerUser(Utility.getUserObj(data));
    }
}
