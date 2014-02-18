package aukroview.model;


public class Bidder {
    private String name;
    private String price;
    private String data;
    private int rating;


    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Bidder name is ");
        sb.append(name);
        sb.append(" , rating ");
        sb.append(rating);
        sb.append(" , price =  ");
        sb.append(price);
        sb.append(" , date  ");
        sb.append(data);
        return sb.toString();
    }
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
