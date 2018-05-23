package com.solution.database.Service;

import com.solution.database.DAO.DAOService;
import com.solution.database.Model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    DAOService daoService;

    public AppUser registerUser(AppUser appUser){
    return  daoService.addUser(appUser);
    }
}
