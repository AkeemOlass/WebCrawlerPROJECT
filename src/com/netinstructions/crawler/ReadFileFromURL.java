package com.netinstructions.crawler;

import java.net.URL;
import java.util.Scanner;
import org.jsoup.*;
import org.jsoup.nodes.Document;

public class ReadFileFromURL {
   public static void ReadFileFromURLl( Document htmlDocument) {
       String URLString = new Scanner(String.valueOf(htmlDocument)).next();
   }
     public static void reader( String URLString){
        try{
            java.net.URL url =new java.net.URL( URLString);
            int  count = 0;
            Scanner input = new Scanner(url.openStream());
            while(input.hasNext()) {
                    String line = input.nextLine();
                    count += line.length();
                    System.out.println(line );
                  }

             System.out.println("The file size is "  + count + " characters" );

              }

        catch(java.net.MalformedURLException ex) {
          System.out.println(   "Invalid URL");
               }

        catch(java.io.IOException ex) {
           System.out.println( "I/O Errors: no such file" );
                  }
            }
}
