package com.solution.database.Service;


import com.solution.database.DAO.DAOService;
import com.solution.database.Model.MonthwiseAvgClosePriceofaYearObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MonthwiseAvgClosePriceofaYear {

    @Autowired
    private DAOService daoService;

    public MonthwiseAvgClosePriceofaYear(){

    }

    public ArrayList<MonthwiseAvgClosePriceofaYearObj> getMonthwiseAvgClosePriceofaYear(String company, String year){
        return daoService.getMonthwiseAvgClosePriceofaYear(company,year);
    }

}
