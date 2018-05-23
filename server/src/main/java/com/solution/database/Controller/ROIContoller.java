package com.solution.database.Controller;

import com.solution.database.Model.ROIObj;
import com.solution.database.Service.ROIService;
import com.solution.database.Utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ROIContoller {

   @Autowired
    ROIService roiService;

   @RequestMapping("/ROI")
   @CrossOrigin(origins = "http://localhost:3000")
    public ArrayList<ROIObj> roi(@RequestBody String time_duration){
       String[] sarr=time_duration.split("\"");
       System.out.println(sarr[3]+"-----"+sarr[7]);
       return  roiService.getROI(Utility.getDate(sarr[3]),Utility.getDate(sarr[7]));
   }

}
