package com.solution.database.Service;


import com.solution.database.DAO.DAOService;
import com.solution.database.Model.MonthwiseAvgClosePriceofaYearObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class RegressionService {

    @Autowired
    private DAOService daoService;

    public RegressionService(){

    }

    public ArrayList<MonthwiseAvgClosePriceofaYearObj> getRegression(String company){
        return daoService.getRegression(company.split("\"")[3]);
    }

}
