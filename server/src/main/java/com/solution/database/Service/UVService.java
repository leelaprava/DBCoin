package com.solution.database.Service;

import com.solution.database.DAO.DAOService;
import com.solution.database.Model.UVOVObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UVService {
    @Autowired
    private DAOService daoService;

    public UVService(){

    }

    public ArrayList<UVOVObj> getUVStock(String year){
        return daoService.getUVStock(year);
    }


}
