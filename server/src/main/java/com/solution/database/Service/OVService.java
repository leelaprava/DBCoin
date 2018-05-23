package com.solution.database.Service;

import com.solution.database.DAO.DAOService;
import com.solution.database.Model.UVOVObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OVService {
    @Autowired
    private DAOService daoService;

    public OVService(){

    }

    public ArrayList<UVOVObj> getOVStock(String year){
        return daoService.getOVStock(year);
    }


}
