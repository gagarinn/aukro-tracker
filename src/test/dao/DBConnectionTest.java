package dao;

import aukroview.dao.DBWriting;
import aukroview.model.Bidder;
import aukroview.model.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Date;


public class DBConnectionTest {

    private static final String username = "user";

    private static final String dbUserQuery = "SELECT user();";


    Bidder bid1;
    Bidder bid2;
    Bidder bid3;
    ArrayList<Bidder> bidders;
    Item testItem;
    DBWriting db;

    @Before
    public void initialize() {
        bid1 = new Bidder();
        bid2 = new Bidder();
        bid3 = new Bidder();
        bidders = new ArrayList<Bidder>();

        testItem = new Item();
        testItem.setItemName("testName");
        testItem.setItemId("testId");
        testItem.setWatches(11111);
        testItem.setBidPrice("testPrice1");
        testItem.setBuyNowPrice("testPrice2");
        testItem.setStatus("testStatus");
        testItem.setQueryTime(new Date().toString());
        testItem.setBidders(bidders);

         db = new DBWriting();
    }

    @Test
    public void dbConnectionTest(){
        Connection conn = db.getConnection();

        Assert.assertFalse(conn == null);
    }

    @Test
    public void dbPropConnectionTest(){
        Connection conn = db.getPropConnection();
        Assert.assertFalse(conn == null);
    }



    @Test
    public void dbUserTest(){
        String actualUser = null;
        String expectedUser =  username + "@localhost";
        Connection con = db.getConnection();

        try {
            Statement stat = con.createStatement();
            ResultSet result  = stat.executeQuery(dbUserQuery);
            result.next();
            actualUser = result.getString(1);
            result.close();
            stat.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

       Assert.assertTrue(actualUser.equals(expectedUser));
    }


//    ----------------------------------- Real BD tests

    @Test
    public void simpleDBWriting(){
        Connection con = db.getConnection();
        Statement stmt = null;
        try {
            stmt = con.createStatement();

          String createTable = "CREATE TABLE IF NOT EXISTS test1(test TEXT)";
            stmt.executeUpdate(createTable);
          String insertData = "INSERT INTO test1 VALUES('testWord')";
            stmt.executeUpdate(insertData);
          String delTable = "DROP TABLE test1";
            stmt.executeUpdate(delTable);

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
    }

    @Test
    public void testDBWriting(){
        Connection con = db.getConnection();
        Statement stmt = null;

        try {
            stmt = con.createStatement();
            Assert.assertTrue(db.writeItemInfo(testItem));
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

    }

    @Test
    public void testReadItemInfo(){
        //TODO parsing testItem
        Assert.assertFalse(db.readItemInfo() == null);
    }


}
