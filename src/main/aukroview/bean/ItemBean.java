package aukroview.bean;

import aukroview.dao.DBWriting;
import aukroview.model.Item;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Администратор
 * Date: 10.02.14
 * Time: 16:40
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "itemFind", eager = true)
@SessionScoped
public class ItemBean implements Serializable {

    private ArrayList<Item> items;

    public ArrayList<Item> getItems() {
        DBWriting db = new DBWriting();
       System.out.println("detItems<>");
        return db.readItemInfo();
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
