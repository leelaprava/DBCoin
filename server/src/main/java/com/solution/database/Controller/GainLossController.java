package com.solution.database.Controller;

import com.solution.database.Model.GainLossObj;
import com.solution.database.Service.GainLossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class GainLossController {

    @Autowired
    GainLossService gainLossService;

    @RequestMapping("/GainLoss")
    @CrossOrigin(origins = "http://localhost:3000")
    public ArrayList<GainLossObj> getGainLoss(@RequestBody String data){
        return gainLossService.getgainloss(data);
    }
}
