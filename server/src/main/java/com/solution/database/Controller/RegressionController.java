package com.solution.database.Controller;

import com.solution.database.Model.MonthwiseAvgClosePriceofaYearObj;
import com.solution.database.Service.RegisterService;
import com.solution.database.Service.RegressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class RegressionController {
    @Autowired
    RegressionService regressionService;

    @RequestMapping("/Regression")
    @CrossOrigin(origins = "http://localhost:3000")
    public ArrayList<MonthwiseAvgClosePriceofaYearObj> getRegression(@RequestBody String data){
        return regressionService.getRegression(data);
    }
}
