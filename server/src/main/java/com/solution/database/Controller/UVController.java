package com.solution.database.Controller;

import com.solution.database.Model.UVOVObj;
import com.solution.database.Service.UVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class UVController {
    @Autowired
    UVService uvService;
    @RequestMapping("/UVStock")
    @CrossOrigin(origins = "http://localhost:3000")
    public ArrayList<UVOVObj> getUV(@RequestBody String uv_param){
        String[] sarr=uv_param.split("\"");
        return uvService.getUVStock(sarr[3].substring(2));
    }
}
