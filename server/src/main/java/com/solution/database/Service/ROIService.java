package com.solution.database.Service;

import com.solution.database.DAO.DAOService;
import com.solution.database.Model.MonthwiseIndustryGrowthObj;
import com.solution.database.Model.ROIObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class ROIService {
    @Autowired
    private DAOService daoService;

    public ROIService(){

    }

    public ArrayList<ROIObj> getROI(String startDate, String endDate){
        return daoService.getROI(startDate,endDate);
    }

}
