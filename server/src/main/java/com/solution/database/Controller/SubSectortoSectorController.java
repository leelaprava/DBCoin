package com.solution.database.Controller;

import com.solution.database.DAO.DAOService;
import com.solution.database.Model.SubSectortoSectorObj;
import com.solution.database.Service.SubSectortoSector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class SubSectortoSectorController {
    @Autowired
    private SubSectortoSector subSectortoSector;

    @Autowired
    private DAOService daoService;

    @RequestMapping("/subSectortoSector")
    @CrossOrigin(origins = "http://localhost:3000")
    public ArrayList<SubSectortoSectorObj> subSectortoSector(@RequestBody String sector){
        System.out.println(sector);
        String[] sarr=sector.split("\"");
        return subSectortoSector.getSubSectortoSector(sarr[3],Integer.parseInt(sarr[7]));
    }

}
