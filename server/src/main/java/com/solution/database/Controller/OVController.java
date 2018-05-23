package com.solution.database.Controller;

import com.solution.database.Model.UVOVObj;
import com.solution.database.Service.OVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class OVController {
    @Autowired
    OVService ovService;

    @RequestMapping("/OVStock")
    @CrossOrigin(origins = "http://localhost:3000")
    public ArrayList<UVOVObj> getOV(@RequestBody String ov_param)
    {
        String[] sarr=ov_param.split("\"");
        return ovService.getOVStock(sarr[3].substring(2));
    }
}
