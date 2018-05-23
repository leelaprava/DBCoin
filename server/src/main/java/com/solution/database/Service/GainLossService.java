package com.solution.database.Service;

import com.solution.database.DAO.DAOService;
import com.solution.database.Model.GainLossObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GainLossService {

    @Autowired
    DAOService daoService;

    public ArrayList<GainLossObj> getgainloss(String data){

        String[] sarr=data.split("\"");
        return daoService.getGainLoss(sarr[3],Integer.parseInt(sarr[7]));
    }
}
