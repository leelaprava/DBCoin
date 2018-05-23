package com.solution.database.Controller;

import com.solution.database.Service.CompanyService;
import com.solution.database.DAO.DAOService;
import com.solution.database.Model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private DAOService daoService;

    @RequestMapping("/search")
    @CrossOrigin(origins = "http://localhost:3000")
    public ArrayList<Company> search(){
        return companyService.getCompanies();
    }

    @RequestMapping("/getCompanyNames")
    @CrossOrigin(origins = "http://localhost:3000")
    public ArrayList<String> getCompanyName(){
        return companyService.getCompanyNames();
    }

    @RequestMapping("/getWords")
    @CrossOrigin(origins = "http://localhost:3000")
    public String getWord(@RequestBody String input){
        String [] sarr=input.split("\"");
        return companyService.getWords(sarr[3]);
    }
}
