package com.solution.database.Service;

import com.solution.database.DAO.DAOService;
import com.solution.database.Model.MonthwiseIndustryGrowthObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class MonthwiseIndustryGrowthService {

    @Autowired
    private DAOService daoService;

    public MonthwiseIndustryGrowthService(){

    }

    public ArrayList<MonthwiseIndustryGrowthObj> getMonthwiseIndustryGrowth(String sector, String year){
        return daoService.getMonthwiseIndustryGrowth(sector,year);
    }
}
