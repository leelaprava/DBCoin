package com.solution.database.Utility;

import com.solution.database.Model.AppUser;

import java.util.ArrayList;

public  class Utility {
   public static String getDate(String s1){
       String[] s=s1.split("-");           // 2018-04-27
       //13-SEP-2012
       if(s[1]=="01")
           return s[2]+"-JAN-"+s[0];
       else if(s[1]=="02")
           return s[2]+"-FEB-"+s[0];
       else if(s[1]=="03")
           return s[2]+"-MAR-"+s[0];
       else if(s[1]=="04")
           return s[2]+"-APR-"+s[0];
       else if(s[1]=="05")
           return s[2]+"-MAY-"+s[0];
       else if(s[1]=="06")
           return s[2]+"-JUN-"+s[0];
       else if(s[1]=="07")
           return s[2]+"-JUL-"+s[0];
       else if(s[1]=="08")
           return s[2]+"-AUG-"+s[0];
       else if(s[1]=="09")
           return s[2]+"-SEP-"+s[0];
       else if(s[1]=="10")
           return s[2]+"-OCT-"+s[0];
       else if(s[1]=="11")
           return s[2]+"-NOV-"+s[0];
       else
           return s[2]+"-DEC-"+s[0];
   }

   public static AppUser getUserObj(String data){
//       {
//           "name":"aniket",
//               "email":"a@ufl.edu",
//               "username":"ank",
//               "number":"3522813000",
//               "password":"pass",
//               "favorite1":"Broadcom",
//               "favorite2":"CA, Inc.",
//               "favorite3":"Carmax Inc"
//       }
       String sarr[]=data.split("\"");
       return new AppUser(sarr[3],sarr[7],Long.parseLong(sarr[15]),sarr[11],sarr[19],sarr[23],sarr[27],sarr[31]);
   }
}
