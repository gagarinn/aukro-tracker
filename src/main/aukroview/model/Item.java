package aukroview.model;

import java.util.ArrayList;

/**
 * User: Viktor Burmaka
 * Date: 09.02.14
 * Time: 17:31
 */
public class Item {
    private  String itemName = "";
    private  String itemId = "";
    private  int watches = 0;
    private  String buyNowPrice = "";
    private  String bidPrice = "";
    private String queryTime = null;
    private String status = "";
    private ArrayList<Bidder> bidders;

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Item name is ");
        sb.append(itemName);
        sb.append(" , id ");
        sb.append(itemId);
        sb.append(" , watches number =  ");
        sb.append(watches);
        sb.append(" , buyNowPrice  ");
        sb.append(buyNowPrice);
        sb.append(" , bidPrice  ");
        sb.append(bidPrice);
        sb.append(" , status  ");
        sb.append(status);
        sb.append(" , bidders  ");
            if (bidders != null){
                int i = 0;
                sb.append("\n");
                for (Bidder b : bidders){
                    sb.append(i);
                    sb.append(" | ");
                    sb.append(b.toString());
                    sb.append("\n");
                    i++;
                }
            }
        return sb.toString();
    }

    public Item() {
//        System.out.println( "Item empty constractor " );
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Bidder> getBidders() {
        return bidders;
    }

    public void setBidders(ArrayList<Bidder> bidders) {
        this.bidders = bidders;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getWatches() {
        return watches;
    }

    public void setWatches(int watches) {
        this.watches = watches;
    }

    public String getBuyNowPrice() {
        return buyNowPrice;
    }

    public void setBuyNowPrice(String buyNowPrice) {
        this.buyNowPrice = buyNowPrice;
    }

    public String getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(String bidPrice) {
        this.bidPrice = bidPrice;
    }

    public String getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(String queryTime) {
        this.queryTime = queryTime;
    }
}
