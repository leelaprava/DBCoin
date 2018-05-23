package com.solution.database.Controller;

import com.solution.database.Model.YearwiseMostProfitObj;
import com.solution.database.Service.YearwiseMostProfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class getYearwiseMostProfitController {

   @Autowired
    YearwiseMostProfitService yearwiseMostProfitService;

   @RequestMapping("/YearWiseMostProfit")
   @CrossOrigin(origins = "http://localhost:3000")
    public ArrayList<YearwiseMostProfitObj> YearWIseMostProfit(@RequestBody String param_year_most_profit){
//       System.out.println(param_for_year);
       String[] sarr=param_year_most_profit.split("\"");
//       System.out.println(sarr.length);
//       System.out.println(sarr[3]+"---"+sarr[7]);
       return yearwiseMostProfitService.getYearwiseMostProfit(sarr[3]);
   }
}
