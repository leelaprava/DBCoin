package com.solution.database.Controller;

import com.solution.database.Model.CompanyProfile;
import com.solution.database.Service.CompanyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyProfileController {

    @Autowired
    CompanyProfileService companyProfileService;

    @RequestMapping("/CompanyProfileData")
    @CrossOrigin(origins = "http://localhost:3000")
    public CompanyProfile getCompanyProfile(@RequestBody String company){
        return companyProfileService.getCompanyDetails(company.split("\"")[3]);
    }
}
