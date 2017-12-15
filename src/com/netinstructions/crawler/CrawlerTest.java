package com.netinstructions.crawler;

public class CrawlerTest {
    /**
     * This is our test. It creates a spider (which creates spider legs) and crawls the web.
     *
     * @param args - not used
     */
    public static void main(String[] args) {
        WebCrawler spider = new WebCrawler();
        Hrqst req = new Hrqst();
        spider.search("http://www.hospital-directory.info");
        req.getArticles();

       // ReadFileFromURL input = new ReadFileFromURL();
      //  input.ReadFileFromURLl(https://en.wikipedia.org/wiki/List_of_hospitals_in_Ghana");
        //req.getArticles();
       // req.writeToFile("Hospital Crawler");
    }
}


