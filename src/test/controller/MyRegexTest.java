package controller;


import aukroview.controller.MyRegex;
import aukroview.model.Bidder;
import aukroview.model.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class MyRegexTest {
    public static final String HTML_REGEX = ".*|\\s*<\\s*html\\s*|>";
    private static final String HTML_TEG1 = "<html class = ";
    private static final String HTML_TEG2 = "<html>";


    MyRegex myRegex;

    @Before
    public void initialize() {
        myRegex = new MyRegex();
    }

    @Test
    public void testIsEqual(){
        Assert.assertTrue(myRegex.isEqual(HTML_REGEX, HTML_TEG1));
        Assert.assertTrue(myRegex.isEqual(HTML_REGEX, HTML_TEG2));

    }

    @Test
    public void testReadFile() throws FileNotFoundException {
        String testpage = "src/test/resources/testpage.html";
        File testFile = new File(testpage);
        BufferedReader b = new BufferedReader(new FileReader(testFile));
        Item testItem = myRegex.getItem(b);
        System.out.println(testItem.toString());
        Assert.assertTrue(testItem.getItemName().equals("ЭНЦИКЛОПЕДИЧЕСКИЙ СЛОВАРЬ. В 3 тт 1953г От 1 грн "));
        Assert.assertTrue(testItem.getItemId().equals("3910812661"));
        Assert.assertTrue(testItem.getWatches() == 96);
        Assert.assertTrue(testItem.getBuyNowPrice().equals("-"));
        Assert.assertTrue(testItem.getBidPrice().equals("105,00"));
        Assert.assertTrue(testItem.getStatus().equals("завершен Чтв 13 Фев 2014 21:30:00"));

        ArrayList<Bidder> testBidders = testItem.getBidders();

        Assert.assertTrue(testBidders.get(0).getName().equals("patriotes"));
        Assert.assertTrue(testBidders.get(0).getData().equals("Сбт 25 Янв 2014 16:59:03"));
        Assert.assertTrue(testBidders.get(0).getPrice().equals("105,00 грн."));
        Assert.assertTrue(testBidders.get(0).getRating() == 238);

        Assert.assertTrue(testBidders.get(1).getName().equals("sergeygavrilyuk"));
        Assert.assertTrue(testBidders.get(1).getData().equals("Птн 24 Янв 2014 11:26:26"));
        Assert.assertTrue(testBidders.get(1).getPrice().equals("100,00 грн."));
        Assert.assertTrue(testBidders.get(1).getRating() == 75);




    }

}
