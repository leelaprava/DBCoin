package com.solution.database.Service;


import com.solution.database.DAO.DAOService;
import com.solution.database.Model.CompanyProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CompanyProfileService {

    @Autowired
    private DAOService daoService;

    public CompanyProfileService(){

    }

    public CompanyProfile getCompanyDetails(String name){
        return daoService.getCompanyDetails(name);
    }
}