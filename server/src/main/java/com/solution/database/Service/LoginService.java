package com.solution.database.Service;

import com.solution.database.DAO.DAOService;
import com.solution.database.Model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    DAOService daoService;

    public AppUser checkLogin(String data){

        String[] sarr=data.split("\"");
        AppUser user=daoService.testLogin(sarr[3]);

        System.out.println(user.toString());
        if(user.getUsername()==null)
            return new AppUser();
        if(user.getPassword().equals(sarr[7])){
            user.setPassword("");
            return user;
        }
        else
            return new AppUser();

    }
}
