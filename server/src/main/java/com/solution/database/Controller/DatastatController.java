package com.solution.database.Controller;

import com.solution.database.Model.DataStat;
import com.solution.database.Service.DataStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class DatastatController {

    @Autowired
    DataStatService dataStatService;

    @RequestMapping("/Datastats")
    @CrossOrigin(origins = "http://localhost:3000")
    public ArrayList<DataStat> getDataStats(){
        return dataStatService.getCompanies();
    }
}
