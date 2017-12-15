package com.netinstructions.crawler;
import org.jsoup.*;
import org.jsoup.helper.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Hrqst {
    // private static final int MAX_DEPTH = 2;
    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    private List<String> links = new LinkedList<String>(); // Just a list of URLs
   // private List<List<String>> articles;

    private Document htmlDocument; // This is our web page, or in other words, our document

    /**
     * This performs all the work. It makes an HTTP request, checks the response, and then gathers
     * up all the links on the page. Perform a searchForWord after the successful crawl
     *
     * @param nextURL - The URL to visit
     * @return whether or not the crawl was successful
     */

    public void crawl(String nextURL)
    /** Give it a URL and it makes an HTTP request for a web page*/
    {
        try {
            Connection connection = Jsoup.connect(nextURL).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            this.htmlDocument = htmlDocument;
            if (connection.response().statusCode() == 200) // 200 is the HTTP OK status code
            {  // indicating that everything is great.
                System.out.println("Received web page at " + nextURL);
            }
            if (!connection.response().contentType().contains("text/html"))//&& (depth < MAX_DEPTH))
            {
                System.out.println("**Failure** Retrieved something other than HTML");
               // return false;
            }
          //  String text = htmlDocument.select("div#entry-content").text();
           // System.out.println(text);

            Elements linksOnPage = htmlDocument.select(" a[href^=/hospitals]");
            System.out.println("Found (" + linksOnPage.size() + ") links");
            //depth++;
            for (Element link : linksOnPage) {
                this.links.add(link.absUrl("href"));
               // ReadFileFromURL.ReadFileFromURLl(htmlDocument);
            }

        } catch (IOException ioe) {
            // We were not successful in our HTTP request
            System.out.println("Error in out HTTP request " + ioe);
        }

       // return false;
         }
    public void getArticles() {
        links.forEach(x -> {
                    Document document;
                    try {
                        document = Jsoup.connect(x).get();
                        String hinfo = document.select("div.content").text();
                        System.out.print(hinfo);
                        output(hinfo);
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
    });
    }

    private void output(String hinfo)throws IOException  {
            java.io.File file = new java.io.File("HospitalDirr.txt");
            if (file.exists()) {
                System.out.println("File already exists");
                System.exit(0);
            }
            try( java.io.PrintWriter output = new java.io.PrintWriter(file);) {
                output.print(hinfo);
            }


        }
    ;

    public List<String> getLinks()
    /** Returns a list of all the URLs on the page*/
    {
        getArticles();
        return this.links;
    }

    ;


}

