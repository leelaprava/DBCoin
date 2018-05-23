package com.solution.database.Controller;

import com.solution.database.Utility.Utility;
import com.solution.database.DAO.DAOService;
import com.solution.database.Model.BestPerformingStock;
import com.solution.database.Model.MonthwiseAvgClosePriceofaYearObj;
import com.solution.database.Model.SubSectortoSectorObj;
import com.solution.database.Service.BestPerformingStockService;
import com.solution.database.Service.MonthwiseAvgClosePriceofaYear;
import com.solution.database.Service.SubSectortoSector;
import com.solution.database.Utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
@RestController
public class BestPerformingStockController {
    @Autowired
    private BestPerformingStockService bestPerformingStock;

    @RequestMapping("/BestPerformingStock")
    @CrossOrigin(origins = "http://localhost:3000")
    public ArrayList<BestPerformingStock> BestPerformingStock(@RequestBody String best_performing_stock){
        System.out.println(best_performing_stock);
        String[] sarr=best_performing_stock.split("\"");
        return bestPerformingStock.getBestPerformingStock(Utility.getDate(sarr[3]),Utility.getDate(sarr[7]),Integer.parseInt(sarr[11]));
    }


}
