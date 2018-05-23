package com.solution.database.Service;


import com.solution.database.DAO.DAOService;
import com.solution.database.Model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CompanyService {

    @Autowired
    private DAOService daoService;

    public CompanyService(){

    }

    public ArrayList<Company>getCompanies(){
        return daoService.getCompanies();
    }
    public ArrayList<String>getCompanyNames(){return daoService.getCompanyNames();}
    public String getWords(String year){return daoService.getWordMap(year);}
}
