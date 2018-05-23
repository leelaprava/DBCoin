package com.solution.database.Service;

import com.solution.database.DAO.DAOService;
import com.solution.database.Model.SubSectortoSectorObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class SubSectortoSector {
    @Autowired
    private DAOService daoService;

    public SubSectortoSector(){

    }

    public ArrayList<SubSectortoSectorObj> getSubSectortoSector(String sector, int year){
        return daoService.getSubSectortoSector(sector,year);
    }
}
