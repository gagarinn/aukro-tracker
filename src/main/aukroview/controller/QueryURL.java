package aukroview.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


public class QueryURL {

    private static final String proxy = "172.17.10.2";
    private static final String proxyPort = "3128";
//    TODO isSystemProxyUse

            String urlStr = "http://aukro.ua/planshet-bomba-q88-v-3-ferarri-chehol-klaviatura-i3945006692.html";
//    String urlStr = "http://aukro.ua/show_item.php?item=3958389941&sh_dwh_token=d0b6dd74a8394345128e20d296cb1c5e";
//    String urlStr = "http://aukro.ua/moneta-plemeni-majya-i3966286427.html";
    URL myUrl;
    InputStreamReader isr = null;

    public BufferedReader getPage(){
         return getPage(urlStr);
    }

    public BufferedReader getPage(String url){
        BufferedReader d = null;
        try {

            if (isSystemProxyUse()){
                System.out.print("proxy using... " );
                myUrl = new URL(url);
                System.setProperty("http.proxyHost", proxy);
                System.setProperty("http.proxyPort", proxyPort);
                isr = new InputStreamReader(myUrl.openStream());
            }

            myUrl = new URL(url);
            isr = new InputStreamReader(myUrl.openStream());

            d = new BufferedReader(isr);
        } catch (IOException e){
            e.printStackTrace();
        }
        return d;
    }
    private boolean isSystemProxyUse(){
        //TODO
//        return true;
        return false;
    }
}
