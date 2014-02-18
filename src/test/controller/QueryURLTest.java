package controller;


import aukroview.controller.QueryURL;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;

public class QueryURLTest {

    private static final String GOOGLE = "https://www.google.com.ua";

    QueryURL queryURL;

    @Before
    public void initialize() {
        queryURL = new QueryURL();
    }

    @Test
    public void testGetPage () throws IOException {
        BufferedReader d = new QueryURL().getPage();
        Assert.assertTrue(d != null);
    }

}
