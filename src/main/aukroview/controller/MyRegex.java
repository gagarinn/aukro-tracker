package aukroview.controller;

import aukroview.model.Bidder;
import aukroview.model.Item;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


public class MyRegex {
    private static final String WATCH_REGEX = "\\s*Просмотры:\\s*(\\d+)";
    private static final String ITEM_PRICE = "buyNowPrice\\s*\":\"\\s*(\\d+,?\\s*[0-9]{2}?|-).+bidPrice\\s*\":\"\\s*(\\d+,?\\s*[0-9]{2}?|-)";
    private static final String ITEM_NAME = "\\s*<\\s*title\\s*>(.+[^(])\\((.+)\\).+</title\\s*>"; //   .+bidHistoryList(.+|\s*|\n*)*</table>
    private static final String STATUS = ".+timeLeftCounter.+\\>\\s*(.+)\\<.+\\s*\\((.+)\\)";
    private static final String BIDTABLE = "uname.+<a\\s*href.*>(.+[^<|>])</a.*user-rating\\s*\"\\s*>\\s*\\((\\d+).*</td>\\s*\\n\\s*<td>\\s*(.+)\\s*</td>\\s*\\n\\s*<td>\\s*(.+)\\s*</td>\\s*\\n\\s*<td>\\s*(.+)\\s*</td>";
    private static final String BIDHISTORYBEGIN = ".+\"bidHistoryList\".+";
    private static final String BIDHISTORYEND = ".*</table>.*";

    //TODO timeLeftCounter
    //TODO bidHistoryList

    private Item item = new Item();
    BufferedReader d;

    public Item getItem() {
       return getItem(new QueryURL().getPage());
    }

    public Item getItem(BufferedReader bufferedReader) {
        Boolean tableBidFlag = false;
        StringBuilder tableBid = new StringBuilder();
        d = bufferedReader;
        String line = new String();
        while (line != null) {

            try {
                line = d.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (getWatchesFromHTML(WATCH_REGEX, line) != -1)
                item.setWatches(getWatchesFromHTML(WATCH_REGEX, line));

            if (getItemNameFromHTML(ITEM_NAME, line)[0] != null) {
                item.setItemName(getItemNameFromHTML(ITEM_NAME, line)[0]);
                item.setItemId(getItemNameFromHTML(ITEM_NAME, line)[1]);

            }

            if (getPriceFromHTML(ITEM_PRICE, line)[0] != null) {
                item.setBuyNowPrice(getPriceFromHTML(ITEM_PRICE, line)[0]);
                item.setBidPrice(getPriceFromHTML(ITEM_PRICE, line)[1]);
            }

            if (getStatusFromHTML (STATUS, line)!= null) {
                item.setStatus(getStatusFromHTML (STATUS, line));
            }

            // start bilding of bidHistory table
            if (isEqual(BIDHISTORYBEGIN,line)){
               tableBidFlag = true;
            }

            if (tableBidFlag){
                tableBid.append(line);
                tableBid.append("\n");
                if (isEqual(BIDHISTORYEND,line)){
                    tableBidFlag =false;
                }
            } //end start bilding of bidHistory table
        }

        if (tableBid!=null){
              item.setBidders(getTableBidFromHTML(BIDTABLE, tableBid.toString()));
        }

           item.setQueryTime(new Date().toString());
        return item;
    }

    public boolean isEqual(String myRegex, String testText){
        if (testText != null) {
            try {
                Pattern p = Pattern.compile(myRegex);
                Matcher m = p.matcher(testText);

                while (m.find()) {
                    if (m.group()== testText)
                    return true;
                }
            } catch (PatternSyntaxException e) {
                e.printStackTrace();
            }
        }
    return false;
    }

    private ArrayList<Bidder> getTableBidFromHTML(String myRegex, String testText) {
        ArrayList<Bidder> bidders = new ArrayList<Bidder>();
        if (testText != null) {
            try {
                Pattern p = Pattern.compile(myRegex);
                Matcher m = p.matcher(testText);

                while (m.find()) {
                    if (m.group()!= null){
                        Bidder b = new Bidder();
                        b.setName(m.group(1));
                        b.setRating(Integer.parseInt(m.group(2)));
                        b.setPrice(m.group(3));
                        b.setData(m.group(5));

                        bidders.add(b);
                    }
                }
                return bidders;
            } catch (PatternSyntaxException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    private  int getWatchesFromHTML(String myRegex, String testText) {
        int result = -1;
        if (testText != null) {
            try {
                Pattern p = Pattern.compile(myRegex);
                Matcher m = p.matcher(testText);

                while (m.find()) {
                    result = Integer.parseInt(m.group(1));
                }
                return result;
            } catch (PatternSyntaxException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private  String[] getItemNameFromHTML(String myRegex, String testText) {
        String[] result = new String[2];
        if (testText != null) {
            try {
                Pattern p = Pattern.compile(myRegex);
                Matcher m = p.matcher(testText);

                while (m.find()) {
                    result[0] = m.group(1);
                    result[1] = m.group(2);
                }
                return result;
            } catch (PatternSyntaxException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private String[] getPriceFromHTML(String myRegex, String testText) {
        String[] result = new String[2];
        if (testText != null) {
            try {
                Pattern p = Pattern.compile(myRegex);
                Matcher m = p.matcher(testText);

                while (m.find()) {
                    result[0] = m.group(1);
                    result[1] = m.group(2);
                }
                return result;
            } catch (PatternSyntaxException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private  String getStatusFromHTML(String myRegex, String testText) {
        String result = null;
        if (testText != null) {
            try {
                Pattern p = Pattern.compile(myRegex);
                Matcher m = p.matcher(testText);

                while (m.find()) {
                    result = m.group(1) + " " + m.group(2);
                }
                return result;
            } catch (PatternSyntaxException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


}
