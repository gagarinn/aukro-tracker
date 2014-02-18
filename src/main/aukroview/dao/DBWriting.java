package aukroview.dao;

import aukroview.model.Item;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * User: Viktor Burmaka
 * Date: 09.02.14
 * Time: 16:16
 */
public class DBWriting {
//    con = DriverManager.getConnection("jdbc:mysql:///dbname?useUnicode=true&characterEncoding=utf-8", "user", "pass");
    private static final String drivers =  "com.mysql.jdbc.Driver";
    private static final  String url = "jdbc:mysql://localhost/aukroview?useUnicode=true&characterEncoding=utf-8";
    private static final String username = "user";
    private static final String password = "hello";

    public  Connection getPropConnection() {

        Properties props = new Properties();
        FileInputStream in = null;
        try{
            System.out.println("src/resources/database.properties");
            in = new FileInputStream("src/resources/database.properties");
            props.load(in);
            in.close();
        }
        catch (FileNotFoundException e) {
           e.printStackTrace();
            try {
                System.out.println("DB connection from string ...");

                Class.forName(drivers);
            } catch (ClassNotFoundException ecnf) {
                ecnf.printStackTrace();
            }
            try {
                return  DriverManager.getConnection(url, username, password);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        String drivers = props.getProperty("jdbc.drivers");
        if (drivers != null)
            System.setProperty("jdbc.drivers", drivers);

        try {
            Class.forName(drivers);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        try {
            System.out.println("DB connection created");
            return
                    DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public  Connection getConnection(){

        try {
            Class.forName(drivers);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            return
                    DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public boolean writeItemInfo(Item item){
        Connection con = getConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
        String createTable = "CREATE TABLE IF NOT EXISTS viewresult(itemname TEXT, itemid TEXT, watches BIGINT, buynowprice TEXT, bidprice TEXT, queryTime TEXT) DEFAULT CHARACTER SET utf8 COLLATE utf8_bin";
            stmt.executeUpdate(createTable);
            String itemInfo = "INSERT INTO viewresult " +
                    "VALUES ("  + "'"+ item.getItemName()+"'"+","
                                + "'"+ item.getItemId() + "'"+ ","
                                + "'"+ item.getWatches() + "'" + ","
                                + "'"+ item.getBuyNowPrice() + "'" +","
                                + "'"+ item.getBidPrice() + "'" + ","
                                + "'"+ item.getQueryTime() + "'" + ")";
            stmt.executeUpdate(itemInfo);
           return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            if (con != null)
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return false;
    }


    public ArrayList<Item> readItemInfo(){

        ArrayList<Item> items = new ArrayList<Item>();

        Connection con = getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String readInfo = "Select * from viewresult";
            pst = con.prepareStatement(readInfo);
            pst.execute();
            rs = pst.getResultSet();

            while(rs.next()){
                Item item = new Item();

                item.setItemName(rs.getString(1));
                item.setItemId(rs.getString(2));
                item.setWatches(rs.getInt(3));
                item.setBuyNowPrice(rs.getString(4));
                item.setBidPrice(rs.getString(5));
                item.setQueryTime(rs.getString(6));

                items.add(item);
          }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            if (con != null)
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return items;
    }

}
