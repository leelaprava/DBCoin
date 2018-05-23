package com.solution.database.Controller;

import com.solution.database.DAO.DAOService;
import com.solution.database.Model.Company;
import com.solution.database.Model.MonthwiseAvgClosePriceofaYearObj;
import com.solution.database.Service.MonthwiseAvgClosePriceofaYear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
public class MonthwiseAvgClosePriceofaYearController {
    @Autowired
    private MonthwiseAvgClosePriceofaYear monthwiseAvgClosePriceofaYear;

    @Autowired
    private DAOService daoService;

    @RequestMapping("/MonthwiseAvgClosePriceofaYear")
    @CrossOrigin(origins = "http://localhost:3000")
    public ArrayList<MonthwiseAvgClosePriceofaYearObj> MonthwiseAvgClosePriceofaYear(@RequestBody String param_for_monthly_average){

        System.out.println(param_for_monthly_average);
        String[] sarr=param_for_monthly_average.split("\"");
        System.out.println(sarr.length);
        System.out.println(sarr[3]+"---"+sarr[7]);
        return monthwiseAvgClosePriceofaYear.getMonthwiseAvgClosePriceofaYear(sarr[3],sarr[7]);
    }
}
