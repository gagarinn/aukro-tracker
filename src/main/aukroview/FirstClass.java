package aukroview;

import aukroview.controller.MyRegex;
import aukroview.dao.DBWriting;
import aukroview.model.Item;

/**
 * User: Viktor Burmaka
 * Date: 07.02.14
 * Time: 19:43
 */
public class FirstClass {


    public static void main(String[] args) {
        System.out.println("main runing... ");

        Item obtainedItem = new MyRegex().getItem();

        System.out.println(obtainedItem.toString());
//        writeDataToDB(testItem);
        DBWriting db = new DBWriting();
        db.writeItemInfo(obtainedItem);
    }

}
