package com.solution.database.Service;

import com.solution.database.DAO.DAOService;
import com.solution.database.Model.DataStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DataStatService {
    @Autowired
    private DAOService daoService;


    public ArrayList<DataStat> getCompanies(){

        return daoService.getDataStat();
    }


}
