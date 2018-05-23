package com.solution.database.DAO;
import com.solution.database.Connection.ConnectionManager;
import com.solution.database.Model.*;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


@Service
public class DAOService {


    private ConnectionManager connectionManager;

    public DAOService() {
        connectionManager = ConnectionManager.getInstance();
    }

    public ArrayList<Company> getCompanies() {

        ArrayList<Company> companyList=new ArrayList<Company>();
        Connection connection = null;
        try {

            // DataSource dataSource = connectionManager.getC();
            connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            String queryString = "SELECT *  FROM COMPANY ";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                Company c=new Company();
                //System.out.println(rs.getString("NAME"));
                c.setName(rs.getString("NAME"));
                //System.out.println(rs.getString("ADDRESS"));
                c.setAddress(rs.getString("ADDRESS"));
                //System.out.println(rs.getString("DATE_ADDED"));
                c.setDateAdded(rs.getString("DATE_ADDED"));
                //System.out.println(rs.getString("CIK"));
                c.setCik(Integer.parseInt(rs.getString("CIK")));
                //System.out.println(rs.getString("SECTOR"));
                c.setSector(rs.getString("SECTOR"));
                //System.out.println(rs.getString("SUBSECTOR"));
                c.setSubsector(rs.getString("SUBSECTOR"));
                //System.out.println(rs.getString("TICKER_SYMBOL"));
                c.setTickerSymbol(rs.getString("TICKER_SYMBOL"));
                //               c.setDateAdded(rs.getString("DATE_ADDED"));
//                c.setCik(Integer.parseInt(rs.getString("CIK")));
//                c.setSector(rs.getString("SECTOR"));
//                c.setSubsector(rs.getString("SUBSECTOR"));
//                c.setTickerSymbol(rs.getString("TICKER_SYMBOL"));
                companyList.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connectionManager.closeConnection(connection);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return companyList;
    }


    public ArrayList<MonthwiseAvgClosePriceofaYearObj> getMonthwiseAvgClosePriceofaYear(String company,String year){
        ArrayList<MonthwiseAvgClosePriceofaYearObj> avgClosePrice=new ArrayList<MonthwiseAvgClosePriceofaYearObj>();
        Connection connection = null;
        try {
            // DataSource dataSource = connectionManager.getC();
            connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            String queryString = "SELECT EXTRACT(MONTH FROM DATE_TRADED) as Month ,EXTRACT(YEAR FROM DATE_TRADED) as Year,AVG(CLOSE_PRICE) As AVGPrice FROM STOCK_PRICE WHERE TICKER_SYMBOL IN (SELECT TICKER_SYMBOL FROM COMPANY WHERE NAME = '"+company+"' ) AND EXTRACT(YEAR FROM DATE_TRADED) = "+year+" GROUP BY EXTRACT(MONTH FROM DATE_TRADED),EXTRACT(YEAR FROM DATE_TRADED) ORDER BY EXTRACT(MONTH FROM DATE_TRADED)";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                MonthwiseAvgClosePriceofaYearObj c=new MonthwiseAvgClosePriceofaYearObj();
                c.setMonth(Integer.parseInt(rs.getString("Month")));
                c.setYear(Integer.parseInt(rs.getString("Year")));
                c.setAvgClosePrice(Float.parseFloat(rs.getString("AVGPrice")));
                avgClosePrice.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connectionManager.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return avgClosePrice;
    }

    public ArrayList<SubSectortoSectorObj> getSubSectortoSector(String sector,int year) {
        ArrayList<SubSectortoSectorObj> result=new ArrayList<SubSectortoSectorObj>();
        Connection connection = null;
        try {
            // DataSource dataSource = connectionManager.getC();
            connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            String queryString = "SELECT COUNT(*) AS NC,SUM(COMPANY_PROFILE.GROSS_PROFIT)/(SELECT SUM(COMPANY_PROFILE.GROSS_PROFIT)FROM COMPANY,COMPANY_PROFILE where COMPANY.TICKER_SYMBOL=COMPANY_PROFILE.TICKER_SYMBOL AND SECTOR='"+sector+"' AND COMPANY_PROFILE.REPORT_YEAR="+year+")  AS FRACTION, COMPANY.SUBSECTOR FROM COMPANY,COMPANY_PROFILE where COMPANY.TICKER_SYMBOL=COMPANY_PROFILE.TICKER_SYMBOL AND SECTOR='"+sector+"' AND COMPANY_PROFILE.REPORT_YEAR="+year+" GROUP BY COMPANY.SUBSECTOR";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                SubSectortoSectorObj c=new SubSectortoSectorObj();
                c.setNc(Integer.parseInt(rs.getString("NC")));
                c.setFraction(Float.parseFloat(rs.getString("FRACTION")));
                c.setSubSector((rs.getString("SUBSECTOR")));
                result.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connectionManager.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public ArrayList<MonthwiseAvgClosePriceofaYearObj> getRegression(String name) {
        ArrayList<MonthwiseAvgClosePriceofaYearObj> result=new ArrayList<MonthwiseAvgClosePriceofaYearObj>();
        Connection connection = null;
        try {
            // DataSource dataSource = connectionManager.getC();
            connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            System.out.println("past data populated");
            String regQuery="SELECT TICKER_SYMBOL, AVG(CLOSE_PRICE) as CP, AVG(slope) as SL, AVG(intcpt) as INT, AVG(rsqr), AVG(cnt), AVG(avgx), AVG(avgy)\n" +
                    "FROM (SELECT TICKER_SYMBOL, CLOSE_PRICE,\n" +
                    "REGR_SLOPE((select max(date_traded) from stock_price)-DATE_TRADED, CLOSE_PRICE)\n" +
                    "   OVER (PARTITION BY TICKER_SYMBOL) slope,\n" +
                    "REGR_INTERCEPT((select max(date_traded) from stock_price)-DATE_TRADED, CLOSE_PRICE)\n" +
                    "   OVER (PARTITION BY TICKER_SYMBOL) intcpt,\n" +
                    "REGR_R2((select max(date_traded) from stock_price)-DATE_TRADED, CLOSE_PRICE)\n" +
                    "   OVER (PARTITION BY TICKER_SYMBOL) rsqr,\n" +
                    "REGR_COUNT((select max(date_traded) from stock_price)-DATE_TRADED, CLOSE_PRICE)\n" +
                    "   OVER (PARTITION BY TICKER_SYMBOL) cnt,\n" +
                    "REGR_AVGX((select max(date_traded) from stock_price)-DATE_TRADED, CLOSE_PRICE)\n" +
                    "   OVER (PARTITION BY TICKER_SYMBOL) avgx,\n" +
                    "REGR_AVGY((select max(date_traded) from stock_price)-DATE_TRADED, CLOSE_PRICE)\n" +
                    "   OVER (PARTITION BY TICKER_SYMBOL) avgy\n" +
                    "   FROM STOCK_PRICE\n" +
                    "   WHERE TICKER_SYMBOL IN (SELECT TICKER_SYMBOL FROM COMPANY WHERE NAME = '"+name+"')\n" +
                    "   ORDER BY TICKER_SYMBOL)\n" +
                    "   GROUP BY TICKER_SYMBOL";

            ResultSet rs1 = statement.executeQuery(regQuery);
            System.out.println("Regression query executed");
            float closePrice=0;
            float slope=0;
            float intercept=0;
            while (rs1.next()) {
                closePrice = rs1.getFloat("CP");
                slope = rs1.getFloat("SL");
                intercept = rs1.getFloat("int");
            }
            int month=1;
            int year=2017;
            int days=15;
            System.out.println("popuklating new data");
            for(int i=0;i<24;i++){
                System.out.println("i"+i);
                MonthwiseAvgClosePriceofaYearObj c=new MonthwiseAvgClosePriceofaYearObj();
                c.setYear(year);
                c.setMonth(month);
                System.out.println("days"+days);
                System.out.println("int"+intercept);
                System.out.println("slope"+slope);
                float avgPrice=(days - intercept)/slope;
                System.out.println("Avg"+avgPrice);
                c.setAvgClosePrice(avgPrice);
                result.add(c);
                days=days+30;
                month=month+1;
                if(month==13) {
                    year = year+1;
                    month = 1;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connectionManager.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public ArrayList<BestPerformingStock> getBestPerformingStock(String startDate,String endDate,int price) {
        ArrayList<BestPerformingStock> result=new ArrayList<BestPerformingStock>();
        Connection connection = null;
        try {
            // DataSource dataSource = connectionManager.getC();
            connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            String queryString = "select * from (select (select name from company where company.TICKER_SYMBOL=o3.ticker_symbol) as company,startprice,endprice,roi\n" +
                    "    from (\n" +
                    "            select o1.ticker_symbol,((o2.close_price-o1.close_price)/o1.close_price)*100 as roi,o1.close_price as startprice,\n" +
                    "            o2.close_price as endprice\n" +
                    "            from (\n" +
                    "                    select ticker_symbol,close_price \n" +
                    "                    from stock_price \n" +
                    "                    where date_traded=(Select MIN(DATE_TRADED) from STOCK_PRICE where date_traded>='"+startDate+"' and close_price<"+price+"))o1,\n" +
                    "                (\n" +
                    "                    select ticker_symbol,close_price \n" +
                    "                    from stock_price \n" +
                    "                    where date_traded=(Select MIN(DATE_TRADED) from STOCK_PRICE where date_traded>='"+endDate+"'))o2\n" +
                    "            where\n" +
                    "    o1.ticker_symbol=o2.ticker_symbol \n" +
                    "    order by roi DESC)o3) where rownum<11";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                BestPerformingStock c=new BestPerformingStock();
                c.setName(rs.getString("COMPANY"));
                c.setStartprice(Float.parseFloat(rs.getString("STARTPRICE")));
                c.setEndprice(Float.parseFloat(rs.getString("ENDPRICE")));
                c.setRoi(Float.parseFloat(rs.getString("ROI")));
                result.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connectionManager.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public ArrayList<MonthwiseIndustryGrowthObj> getMonthwiseIndustryGrowth(String sector, String year) {
        ArrayList<MonthwiseIndustryGrowthObj> result=new ArrayList<MonthwiseIndustryGrowthObj>();
        Connection connection = null;
        try {
            // DataSource dataSource = connectionManager.getC();
            connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            String queryString = "select EXTRACT(MONTH FROM date_traded) as month,AVG(CLOSE_PRICE) as avgcp from stock_price where ticker_symbol in (select ticker_symbol from company where sector="+"'"+sector+"'"+") and EXTRACT(YEAR FROM date_traded) = "+year+" GROUP BY EXTRACT(MONTH FROM date_traded) ORDER BY month";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                MonthwiseIndustryGrowthObj c=new MonthwiseIndustryGrowthObj();
                c.setMonth(Integer.parseInt(rs.getString("MONTH")));
                c.setAvgClosePrice(Float.parseFloat(rs.getString("AVGCP")));
                result.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connectionManager.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public ArrayList<YearwiseMostProfitObj> getYearwiseMostProfit(String year) {
        ArrayList<YearwiseMostProfitObj> result=new ArrayList<YearwiseMostProfitObj>();
        Connection connection = null;
        try {
            // DataSource dataSource = connectionManager.getC();
            connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            String queryString = "select sector , sum(year_change) as year_profit\n" +
                    "from (with temp as ( select ticker_symbol ticker_symbol, extract(year from date_traded) year, rank() over ( partition by ticker_symbol order by date_traded ) RANK,  date_traded ,open_price,  close_price\n" +
                    "    from  split_adjusted_stock_price\n" +
                    "    where extract(year from date_traded) = "+year+"\n" +
                    "    order by  ticker_symbol ,date_traded)\n" +
                    "select  fd.ticker_symbol, fd.year, ld.close_price - fd.open_price year_change\n" +
                    "from\n" +
                    "( select *\n" +
                    "    from temp t1\n" +
                    "    where rank = ( select max(rank) \n" +
                    "                    from temp t2 \n" +
                    "                    where t1.year = t2.year\n" +
                    "                    group by t1.year) ) ld , \n" +
                    "(  select *\n" +
                    "   from temp\n" +
                    "   where rank = 1) fd\n" +
                    "where ld.year = fd.year and fd.ticker_symbol = ld.ticker_symbol ) t1, company\n" +
                    "where t1.ticker_symbol = company.ticker_symbol\n" +
                    "group by sector";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                YearwiseMostProfitObj c=new YearwiseMostProfitObj();
                c.setSector(rs.getString("SECTOR"));
                c.setProfit(Float.parseFloat(rs.getString("YEAR_PROFIT")));
                result.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connectionManager.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }




    public ArrayList<ROIObj> getROI(String startDate,String endDate) {
        ArrayList<ROIObj> result=new ArrayList<ROIObj>();
        Connection connection = null;
        try {
            // DataSource dataSource = connectionManager.getC();
            connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            String queryString = "select * from (select (select name from company where ticker_symbol=o2.ticker_symbol) as company,o1.close_price as start_price,o2.close_price as endprice,((o2.close_price-o1.close_price)/o1.close_price)*100 as roi \n" +
                    "            from (\n" +
                    "                    select ticker_symbol,close_price \n" +
                    "                    from stock_price \n" +
                    "                    where date_traded=(Select MIN(DATE_TRADED) from STOCK_PRICE where date_traded>="+"'"+startDate+"'"+"))o1,\n" +
                    "                (\n" +
                    "                    select ticker_symbol,close_price \n" +
                    "                    from stock_price \n" +
                    "                    where date_traded=(Select MIN(DATE_TRADED) from STOCK_PRICE where date_traded>="+"'"+endDate+"'"+"))o2\n" +
                    "            where\n" +
                    "o1.ticker_symbol=o2.ticker_symbol order by roi DESC) where rownum<11";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                ROIObj c=new ROIObj();
                c.setCompany((rs.getString("COMPANY")));
                c.setStartprice(Float.parseFloat(rs.getString("START_PRICE")));
                c.setEndprice(Float.parseFloat(rs.getString("ENDPRICE")));
                c.setRoi(Float.parseFloat(rs.getString("ROI")));
                result.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connectionManager.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public AppUser addUser(AppUser appUser){
    //insert into FAVORITE values((select ticker_symbol from company where name='Broadcom'),'r_k');
        //insert into APPUSER values('raheen','rah@ufl.edu',3522814000,'rah','passw');
        Connection connection = null;
        try {
            // DataSource dataSource = connectionManager.getC();
            connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            String queryString = "insert into APPUSER values('"+appUser.getName()+"','"+appUser.getEmail()+"',"+appUser.getPhone_number()+",'"+appUser.getUsername()+"','"+appUser.getPassword()+"')";
            Statement preparedStatement = connection.prepareStatement(queryString);

            int count = preparedStatement.executeUpdate(queryString);
            System.out.println(count);
             queryString = "insert into FAVORITE values((select ticker_symbol from company where name='"+appUser.getFavorite1()+"'),'"+appUser.getUsername()+"')";
             preparedStatement = connection.prepareStatement(queryString);

             count = preparedStatement.executeUpdate(queryString);
            System.out.println(count);
            queryString = "insert into FAVORITE values((select ticker_symbol from company where name='"+appUser.getFavorite2()+"'),'"+appUser.getUsername()+"')";
            preparedStatement = connection.prepareStatement(queryString);

            count = preparedStatement.executeUpdate(queryString);
            System.out.println(count);
            queryString = "insert into FAVORITE values((select ticker_symbol from company where name='"+appUser.getFavorite3()+"'),'"+appUser.getUsername()+"')";
            preparedStatement = connection.prepareStatement(queryString);

            count = preparedStatement.executeUpdate(queryString);
            System.out.println(count);
//            ResultSet rs = statement.executeQuery(queryString);
//            while (rs.next()) {
//                result.add(rs.getString("NAME"));
//            }
        } catch (Exception e) {
            e.printStackTrace();
            appUser=new AppUser();
        } finally {
            try {
                connectionManager.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
            return appUser;
    }
    public ArrayList<String> getCompanyNames(){
        ArrayList<String> result=new ArrayList<String>();
        Connection connection = null;
        try {
            // DataSource dataSource = connectionManager.getC();
            connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            String queryString = "Select NAME from COMPANY";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                result.add(rs.getString("NAME"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connectionManager.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
    public String getWordMap(String year){
       // ArrayList<String> result=new ArrayList<String>();
        Connection connection = null;
        String result="";
        try {
            // DataSource dataSource = connectionManager.getC();
            connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            String queryString = "SELECT SECTOR\n" +
                    "FROM (\n" +
                    "      SELECT *\n" +
                    "      FROM (\n" +
                    "               SELECT TICKER_SYMBOL AS TS\n" +
                    "                   FROM (\n" +
                    "                         SELECT COUNT(*) AS COUNT,TICKER_SYMBOL\n" +
                    "                         FROM (\n" +
                    "                               select  STOCK_PRICE.OPEN_PRICE,STOCK_PRICE.CLOSE_PRICE,STOCK_PRICE.TICKER_SYMBOL,STOCK_PRICE.DATE_TRADED\n" +
                    "                               from COMPANY,STOCK_PRICE\n" +
                    "                               where \n" +
                    "                               COMPANY.TICKER_SYMBOL=STOCK_PRICE.TICKER_SYMBOL \n" +
                    "                               AND  \n" +
                    "                               EXTRACT(YEAR FROM STOCK_PRICE.DATE_TRADED)="+year+"\n" +
                    "                              )\n" +
                    "                         WHERE CLOSE_PRICE>(1.05*OPEN_PRICE) OR CLOSE_PRICE<(0.95*OPEN_PRICE)\n" +
                    "                         group by    TICKER_SYMBOL\n" +
                    "                         order by count(*) DESC\n" +
                    "                        )\n" +
                    "            ),STOCK_PRICE\n" +
                    "WHERE STOCK_PRICE.TICKER_SYMBOL=TS \n" +
                    "      AND \n" +
                    "      EXTRACT(YEAR FROM STOCK_PRICE.DATE_TRADED)="+year+
                    "      AND \n" +
                    "      (CLOSE_PRICE>(1.05*OPEN_PRICE) OR CLOSE_PRICE<(0.95*OPEN_PRICE)) order by TS), COMPANY \n" +
                    "      WHERE TS=COMPANY.TICKER_SYMBOL";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                result=result+" "+rs.getString("SECTOR");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connectionManager.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public ArrayList<DataStat> getDataStat() {
        ArrayList<DataStat> result=new ArrayList<DataStat>();
        Connection connection = null;
        try {
            // DataSource dataSource = connectionManager.getC();
            int sum=0;
            connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            String queryString = "select table_name, num_rows from user_tables";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                DataStat c=new DataStat();
                c.setName((rs.getString("TABLE_NAME")));
                c.setTuples(Integer.parseInt(rs.getString("NUM_ROWS")));
                result.add(c);
                sum=sum+Integer.parseInt(rs.getString("NUM_ROWS"));
            }
            DataStat total=new DataStat();
            total.setName("Total");
            total.setTuples(sum);
            result.add(total);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connectionManager.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public ArrayList<UVOVObj> getOVStock(String year) {
        ArrayList<UVOVObj> result=new ArrayList<UVOVObj>();
        Connection connection = null;
        try {
            // DataSource dataSource = connectionManager.getC();
            connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            String queryString = "select * from (select name, valuation_ratio, year\n" +
                    "from (\n" +
                    "select company.name, company_profile.ticker_symbol, stock_price.DATE_TRADED, company_profile.EARNING_PER_SHARE, stock_price.close_price,\n" +
                    "        stock_price.close_price/company_profile.EARNING_PER_SHARE valuation_ratio , to_char(date_traded,'yy') YEAR \n" +
                    "from company_profile, stock_price, company\n" +
                    "where company.ticker_symbol = company_profile.ticker_symbol and \n" +
                    "     company_profile.ticker_symbol = stock_price.ticker_symbol and\n" +
                    "     company_profile.date_ending = stock_price.DATE_TRADED and \n" +
                    "     company_profile.EARNING_PER_SHARE is not null)\n" +
                    "where valuation_ratio > 30 or valuation_ratio between -25 and 0) \n" +
                    "where year='"+year+"'and rownum<11";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                UVOVObj c=new UVOVObj();
                c.setName((rs.getString("NAME")));
                c.setvRatio(Float.parseFloat(rs.getString("valuation_ratio")));
                c.setYear(Integer.parseInt(rs.getString("YEAR")));
                result.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connectionManager.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public ArrayList<UVOVObj> getUVStock(String year) {
        ArrayList<UVOVObj> result=new ArrayList<UVOVObj>();
        Connection connection = null;
        try {
            // DataSource dataSource = connectionManager.getC();
            connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            String queryString = "select * from(select name, valuation_ratio, year\n" +
                    "from (\n" +
                    "select company.name, company_profile.ticker_symbol, stock_price.DATE_TRADED, company_profile.EARNING_PER_SHARE, stock_price.close_price,\n" +
                    "        stock_price.close_price/company_profile.EARNING_PER_SHARE valuation_ratio , to_char(date_traded,'yy') YEAR \n" +
                    "from company_profile, stock_price, company\n" +
                    "where company.ticker_symbol = company_profile.ticker_symbol and \n" +
                    "     company_profile.ticker_symbol = stock_price.ticker_symbol and\n" +
                    "     company_profile.date_ending = stock_price.DATE_TRADED and \n" +
                    "     company_profile.EARNING_PER_SHARE is not null)\n" +
                    "where valuation_ratio between 0 and 15)\n" +
                    "where year='"+year+"' and rownum<11";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                UVOVObj c=new UVOVObj();
                c.setName((rs.getString("NAME")));
                c.setvRatio(Float.parseFloat(rs.getString("valuation_ratio")));
                c.setYear(Integer.parseInt(rs.getString("YEAR")));
                result.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connectionManager.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }


    public CompanyProfile getCompanyDetails(String name) {
        CompanyProfile result=new CompanyProfile();
        Connection connection = null;
        try {
            // DataSource dataSource = connectionManager.getC()
            connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            String tickerSymbol="";
            String queryString = "select ticker_symbol from company where name='"+name+"'";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                tickerSymbol=rs.getString("TICKER_SYMBOL");
                result.setName(name);
                result.setTicketSymbol(tickerSymbol);
            }
            String changeQuery="select o2.close_price as lastprice,((o2.close_price-o1.close_price)/o1.close_price)*100 as perChange, (o2.close_price-o1.close_price) as change\n" +
                    "            from (\n" +
                    "                    select ticker_symbol,close_price \n" +
                    "                    from stock_price \n" +
                    "                    where date_traded=(select max(date_traded) from stock_price) and ticker_symbol='"+tickerSymbol+"')o1,\n" +
                    "                (\n" +
                    "                    select ticker_symbol,close_price \n" +
                    "                    from stock_price \n" +
                    "                    where date_traded=(select max(date_traded)-1 from stock_price) and ticker_symbol='"+tickerSymbol+"')o2\n" +
                    "            where\n" +
                    "    o1.ticker_symbol=o2.ticker_symbol";
            ResultSet rs1 = statement.executeQuery(changeQuery);
            while (rs1.next()) {
                result.setLastPrice(Float.parseFloat(rs1.getString("lastprice")));
                result.setChange(Float.parseFloat(rs1.getString("PERCHANGE")));
                result.setPriceChange(Float.parseFloat(rs1.getString("CHANGE")));
            }
            String getVolumeTraded="select volume_traded as volume_traded from " +
                    "stock_price where date_traded=(select max(date_traded) from stock_price) and ticker_symbol='"+tickerSymbol+"'";
            ResultSet rs2 = statement.executeQuery(getVolumeTraded);
            while (rs2.next()) {
                result.setVolTraded(Integer.parseInt(rs2.getString("volume_traded")));
            }
            String getHigh="select min(close_price) as low,max(close_price) as high from stock_price where date_traded between \n" +
                    "(select max(date_traded)-(52*7) from stock_price) and \n" +
                    "(select max(date_traded) from stock_price) and TICKER_SYMBOL='"+tickerSymbol+"'";
            ResultSet rs3 = statement.executeQuery(getHigh);
            float low=0;
            float high=0;
            while (rs3.next()) {
                low=rs3.getFloat("LOW");
                high=rs3.getFloat("HIGH");
                result.setWeekHigh(rs3.getFloat("HIGH"));
                result.setWeekLow(rs3.getFloat("LOW"));
            }
            String getlowDate="select date_traded as low from stock_price where date_traded between \n" +
                    "(select max(date_traded)-(52*7) from stock_price) and \n" +
                    "(select max(date_traded) from stock_price) and TICKER_SYMBOL='"+tickerSymbol+"' and close_price="+low;
            ResultSet rs4 = statement.executeQuery(getlowDate);
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            while (rs4.next()) {
                result.setLowDate(df.format(rs4.getDate("LOW")));
            }
            String gethighDate="select date_traded as high from stock_price where date_traded between \n" +
                    "(select max(date_traded)-(52*7) from stock_price) and \n" +
                    "(select max(date_traded) from stock_price) and TICKER_SYMBOL='"+tickerSymbol+"' and close_price="+high;
            ResultSet rs5 = statement.executeQuery(gethighDate);
            while (rs5.next()) {
                result.setHighDate(df.format(rs5.getDate("HIGH")));
            }
            String getDetails="select gross_profit,net_income,total_asset,total_liability,total_equity,goodwill,total_revenue,earning_per_share\n" +
                    "from company_profile where ticker_symbol=(SELECT TICKER_SYMBOL FROM COMPANY WHERE NAME = '"+name+"') and \n" +
                    "EXTRACT(YEAR FROM DATE_ENDING)=(select MAX(EXTRACT(YEAR FROM DATE_ENDING)) from company_profile where \n" +
                    "ticker_symbol=(SELECT TICKER_SYMBOL FROM COMPANY WHERE NAME = '"+name+"'))";
            ResultSet rs6 = statement.executeQuery(getDetails);
            while (rs6.next()) {
                result.setgProfit(rs6.getFloat("GROSS_PROFIT"));
                result.setNetIncome(rs6.getFloat("NET_INCOME"));
                result.setTotAsset(rs6.getFloat("TOTAL_ASSET"));
                result.setTotLiabilities(rs6.getFloat("total_liability"));
                result.setCashRatio(rs6.getFloat("total_equity"));
                result.setGoodwill(rs6.getFloat("goodwill"));
                result.setTotRevenue(rs6.getFloat("total_revenue"));
                result.setEps(rs6.getFloat("earning_per_share"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connectionManager.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }


    public ArrayList<GainLossObj> getGainLoss(String companyname,int year){

        ArrayList<GainLossObj> result=new ArrayList<GainLossObj>();
        Connection connection=null;

        try {

            connection=connectionManager.getConnection();
            Statement statement=connection.createStatement();
            String queryString="WITH tally_table AS \n" +
                    "(SELECT\n" +
                    "first_day_data.SYMBOL symbol\n" +
                    ",first_day_data.year year\n" +
                    ",first_day_data.month month\n" +
                    ",first_day_data.first_day first_day\n" +
                    ",OpeningPrice open\n" +
                    ",first_day_data.MA10_first_day mav_10_first_day\n" +
                    ",first_day_data.MA30_first_day mav_30_first_day\n" +
                    ",last_day_data.last_day last_day\n" +
                    ",last_day_data.ClosingPrice close\n" +
                    "FROM \n" +
                    "(\n" +
                    "-- first_day data\n" +
                    "SELECT \n" +
                    "Symbol\n" +
                    ",EXTRACT(YEAR FROM MarketDate) year\n" +
                    ",EXTRACT(MONTH FROM MarketDate) month\n" +
                    ",MarketDate first_day\n" +
                    ",OpeningPrice\n" +
                    ",round(MA10,2) MA10_first_day\n" +
                    ",round(MA30,2) MA30_first_day\n" +
                    "FROM \n" +
                    "(\n" +
                    "SELECT MarketDate,\n" +
                    "       Symbol,\n" +
                    "       RNum,\n" +
                    "       OpeningPrice,\n" +
                    "       ClosingPrice,\n" +
                    "       CASE\n" +
                    "          WHEN RNum > 9 THEN MA10\n" +
                    "          ELSE NULL\n" +
                    "       END as MA10,\n" +
                    "       CASE\n" +
                    "          WHEN RNum > 29 THEN MA30\n" +
                    "          ELSE NULL\n" +
                    "       END as MA30,\n" +
                    "       CASE\n" +
                    "          WHEN RNum > 29 AND MA10 > MA30 THEN 'Over'\n" +
                    "          WHEN RNum > 29 AND MA10 < MA30 THEN 'Below'\n" +
                    "          ELSE NULL\n" +
                    "       END as Signal\n" +
                    "FROM \n" +
                    "(\n" +
                    "SELECT DATE_TRADED MarketDate,\n" +
                    "       TICKER_SYMBOL Symbol, \n" +
                    "       CLOSE_PRICE ClosingPrice,\n" +
                    "       ROW_NUMBER() OVER (ORDER BY DATE_TRADED ASC) RNum,\n" +
                    "       OPEN_PRICE OpeningPrice,\n" +
                    "       AVG(CLOSE_PRICE) OVER (ORDER BY DATE_TRADED ASC ROWS 9 PRECEDING) AS MA10,\n" +
                    "       AVG(CLOSE_PRICE) OVER (ORDER BY DATE_TRADED ASC ROWS 29 PRECEDING) AS MA30\n" +
                    "FROM STOCK_PRICE\n" +
                    "WHERE TICKER_SYMBOL IN\n" +
                    "(SELECT TICKER_SYMBOL FROM COMPANY WHERE NAME = '"+companyname+"')\n" +
                    ")\n" +
                    "ORDER BY MarketDate)\n" +
                    "WHERE MarketDate IN\n" +
                    "(\n" +
                    "SELECT\n" +
                    "MIN(MarketDate) first_day\n" +
                    "FROM \n" +
                    "(\n" +
                    "SELECT MarketDate,\n" +
                    "       Symbol,\n" +
                    "       RNum,\n" +
                    "       OpeningPrice,\n" +
                    "       ClosingPrice,\n" +
                    "       CASE\n" +
                    "          WHEN RNum > 9 THEN MA10\n" +
                    "          ELSE NULL\n" +
                    "       END as MA10,\n" +
                    "       CASE\n" +
                    "          WHEN RNum > 29 THEN MA30\n" +
                    "          ELSE NULL\n" +
                    "       END as MA30,\n" +
                    "       CASE\n" +
                    "          WHEN RNum > 29 AND MA10 > MA30 THEN 'Over'\n" +
                    "          WHEN RNum > 29 AND MA10 < MA30 THEN 'Below'\n" +
                    "          ELSE NULL\n" +
                    "       END as Signal\n" +
                    "FROM \n" +
                    "(\n" +
                    "SELECT DATE_TRADED MarketDate,\n" +
                    "       TICKER_SYMBOL Symbol, \n" +
                    "       CLOSE_PRICE ClosingPrice,\n" +
                    "       ROW_NUMBER() OVER (ORDER BY DATE_TRADED ASC) RNum,\n" +
                    "       OPEN_PRICE OpeningPrice,\n" +
                    "       AVG(CLOSE_PRICE) OVER (ORDER BY DATE_TRADED ASC ROWS 9 PRECEDING) AS MA10,\n" +
                    "       AVG(CLOSE_PRICE) OVER (ORDER BY DATE_TRADED ASC ROWS 29 PRECEDING) AS MA30\n" +
                    "FROM   STOCK_PRICE\n" +
                    "WHERE TICKER_SYMBOL IN\n" +
                    "(SELECT TICKER_SYMBOL FROM COMPANY WHERE NAME = '"+companyname+"')\n" +
                    ")\n" +
                    "ORDER BY MarketDate)\n" +
                    "GROUP BY EXTRACT(YEAR FROM MarketDate), EXTRACT(MONTH FROM MarketDate)\n" +
                    ")\n" +
                    ") first_day_data\n" +
                    "\n" +
                    "INNER JOIN\n" +
                    "\n" +
                    "(\n" +
                    "-- last_day data\n" +
                    "SELECT \n" +
                    "Symbol\n" +
                    ",EXTRACT(YEAR FROM MarketDate) year\n" +
                    ",EXTRACT(MONTH FROM MarketDate) month\n" +
                    ",MarketDate last_day\n" +
                    ",ClosingPrice\n" +
                    ",MA10 MA10_last_day\n" +
                    ",MA30 MA30_last_day\n" +
                    "FROM \n" +
                    "(\n" +
                    "SELECT MarketDate,\n" +
                    "       Symbol,\n" +
                    "       RNum,\n" +
                    "       OpeningPrice,\n" +
                    "       ClosingPrice,\n" +
                    "       CASE\n" +
                    "          WHEN RNum > 9 THEN MA10\n" +
                    "          ELSE NULL\n" +
                    "       END as MA10,\n" +
                    "       CASE\n" +
                    "          WHEN RNum > 29 THEN MA30\n" +
                    "          ELSE NULL\n" +
                    "       END as MA30,\n" +
                    "       CASE\n" +
                    "          WHEN RNum > 29 AND MA10 > MA30 THEN 'Over'\n" +
                    "          WHEN RNum > 29 AND MA10 < MA30 THEN 'Below'\n" +
                    "          ELSE NULL\n" +
                    "       END as Signal\n" +
                    "FROM \n" +
                    "(\n" +
                    "SELECT DATE_TRADED MarketDate,\n" +
                    "       TICKER_SYMBOL Symbol, \n" +
                    "       CLOSE_PRICE ClosingPrice,\n" +
                    "       ROW_NUMBER() OVER (ORDER BY DATE_TRADED ASC) RNum,\n" +
                    "       OPEN_PRICE OpeningPrice,\n" +
                    "       AVG(CLOSE_PRICE) OVER (ORDER BY DATE_TRADED ASC ROWS 9 PRECEDING) AS MA10,\n" +
                    "       AVG(CLOSE_PRICE) OVER (ORDER BY DATE_TRADED ASC ROWS 29 PRECEDING) AS MA30\n" +
                    "FROM   STOCK_PRICE\n" +
                    "WHERE TICKER_SYMBOL IN\n" +
                    "(SELECT TICKER_SYMBOL FROM COMPANY WHERE NAME = '"+companyname+"')\n" +
                    ")\n" +
                    "ORDER BY MarketDate)\n" +
                    "WHERE MarketDate IN\n" +
                    "(\n" +
                    "SELECT\n" +
                    "MAX(MarketDate) last_day\n" +
                    "FROM \n" +
                    "(\n" +
                    "SELECT MarketDate,\n" +
                    "       Symbol,\n" +
                    "       RNum,\n" +
                    "       OpeningPrice,\n" +
                    "       ClosingPrice,\n" +
                    "       CASE\n" +
                    "          WHEN RNum > 9 THEN MA10\n" +
                    "          ELSE NULL\n" +
                    "       END as MA10,\n" +
                    "       CASE\n" +
                    "          WHEN RNum > 29 THEN MA30\n" +
                    "          ELSE NULL\n" +
                    "       END as MA30,\n" +
                    "       CASE\n" +
                    "          WHEN RNum > 29 AND MA10 > MA30 THEN 'Over'\n" +
                    "          WHEN RNum > 29 AND MA10 < MA30 THEN 'Below'\n" +
                    "          ELSE NULL\n" +
                    "       END as Signal\n" +
                    "FROM \n" +
                    "(\n" +
                    "SELECT DATE_TRADED MarketDate,\n" +
                    "       TICKER_SYMBOL Symbol, \n" +
                    "       CLOSE_PRICE ClosingPrice,\n" +
                    "       ROW_NUMBER() OVER (ORDER BY DATE_TRADED ASC) RNum,\n" +
                    "       OPEN_PRICE OpeningPrice,\n" +
                    "       AVG(CLOSE_PRICE) OVER (ORDER BY DATE_TRADED ASC ROWS 9 PRECEDING) AS MA10,\n" +
                    "       AVG(CLOSE_PRICE) OVER (ORDER BY DATE_TRADED ASC ROWS 29 PRECEDING) AS MA30\n" +
                    "FROM   STOCK_PRICE\n" +
                    "WHERE TICKER_SYMBOL IN\n" +
                    "(SELECT TICKER_SYMBOL FROM COMPANY WHERE NAME = '"+companyname+"')\n" +
                    ")\n" +
                    "ORDER BY MarketDate)\n" +
                    "GROUP BY EXTRACT(YEAR FROM MarketDate), EXTRACT(MONTH FROM MarketDate)\n" +
                    ")\n" +
                    ") last_day_data\n" +
                    "\n" +
                    "ON \n" +
                    "first_day_data.SYMBOL = last_day_data.SYMBOL\n" +
                    "AND\n" +
                    "first_day_data.year = last_day_data.year\n" +
                    "AND\n" +
                    "first_day_data.month = last_day_data.month)\n" +
                    "SELECT \n" +
                    "monthly_GL_per_share.SYMBOL\n" +
                    ",YEAR\n" +
                    ",MONTH\n" +
                    ",monthly_GL_per_share.mav_10_first_day mav10\n" +
                    ",monthly_GL_per_share.mav_30_first_day mav30\n" +
                    ",ROUND(GL_for_month_per_share,2) GL_for_month_per_share\n" +
                    ",shares_to_buy_sell lot_size\n" +
                    ",ROUND(shares_to_buy_sell * GL_for_month_per_share,2) GL_for_lot_size\n" +
                    ",CASE\n" +
                    "          WHEN GL_for_month_per_share > 0 THEN 'Trade'\n" +
                    "          WHEN GL_for_month_per_share < 0 THEN 'No Trade'\n" +
                    "          ELSE NULL\n" +
                    "       END as Signal\n" +
                    "\n" +
                    "FROM\n" +
                    "(\n" +
                    "-- monthly G/L per share\n" +
                    "SELECT symbol\n" +
                    "      ,year\n" +
                    "      ,month\n" +
                    "      ,first_day\n" +
                    "      ,mav_10_first_day\n" +
                    "      ,mav_30_first_day\n" +
                    "      ,Open\n" +
                    "      ,last_day\n" +
                    "      ,Close\n" +
                    "      ,Close - Open GL_for_month_per_share\n" +
                    "FROM tally_table\n" +
                    ") monthly_GL_per_share\n" +
                    "\n" +
                    "INNER JOIN\n" +
                    "\n" +
                    "(\n" +
                    "-- shares (rounded to 10) to buy for\n" +
                    "--  around an average $4000 open\n" +
                    "SELECT SYMBOL\n" +
                    "    ,(FLOOR(4000/AVG(open)/10)+1)*10 shares_to_buy_sell\n" +
                    "FROM tally_table\n" +
                    "GROUP BY SYMBOL\n" +
                    ") shares_to_buy_sell\n" +
                    "\n" +
                    "ON monthly_GL_per_share.SYMBOL = shares_to_buy_sell.SYMBOL\n" +
                     "where year="+year+
                    " ORDER BY YEAR,MONTH";
            ResultSet rs=statement.executeQuery(queryString);
            while (rs.next()){
                //String symbol, int year, int month, double mav10, double mav30, double gl_for_month_per_share, int lot_size, double gl_for_lot_size, String signal
                result.add(new GainLossObj(rs.getString("SYMBOL"),rs.getInt("YEAR"),rs.getInt("MONTH"),rs.getDouble("MAV10"),rs.getDouble("MAV30"),rs.getDouble("GL_FOR_MONTH_PER_SHARE"),rs.getInt("LOT_SIZE"),rs.getDouble("GL_FOR_LOT_SIZE"),rs.getString("SIGNAL")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connectionManager.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
    public AppUser testLogin(String username){

        AppUser result=new AppUser();
        Connection connection = null;
        try {
            // DataSource dataSource = connectionManager.getC();
            boolean entered=false;
            connection = connectionManager.getConnection();
            Statement statement = connection.createStatement();
            String queryString = "select * from appuser where USERNAME='"+username+"'";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                entered=true;
                result.setName(rs.getString("NAME"));
                result.setEmail(rs.getString("EMAIL"));
                result.setUsername(rs.getString("USERNAME"));
                result.setPhone_number(rs.getLong("PHONE_NUMBER"));
                result.setPassword(rs.getString("PASSWORD"));
            }
            if(!entered)
                return new AppUser();
             queryString = "select * from FAVORITE where USERNAME='"+username+"'";
             rs = statement.executeQuery(queryString);

             if(rs.next())
                 result.setFavorite1(rs.getString("TICKER_SYMBOL"));
             if(rs.next())
                 result.setFavorite2(rs.getString("TICKER_SYMBOL"));
             if(rs.next())
                 result.setFavorite3(rs.getString("TICKER_SYMBOL"));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connectionManager.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
