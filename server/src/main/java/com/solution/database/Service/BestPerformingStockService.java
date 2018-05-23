package com.solution.database.Service;

import com.solution.database.DAO.DAOService;
import com.solution.database.Model.BestPerformingStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class BestPerformingStockService {
    @Autowired
    private DAOService daoService;

    public BestPerformingStockService(){

    }

    public ArrayList<BestPerformingStock> getBestPerformingStock(String startDate, String endDate,int price){
        return daoService.getBestPerformingStock(startDate,endDate,price);
    }
}
