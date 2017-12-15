package  com.netinstructions.crawler;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WebCrawler  {
    /** PagesVisited contains unique entries. In other words, no duplicates.
    All the pages we visit will be unique (or at least their URL will be unique)
    PagesToVisit This is just storing a bunch of URLs we have to visit next.
     */
    private static final int MAX_PAGES_TO_SEARCH = 20;
    private Set<String> pagesVisited = new HashSet<String>();
    private List<String> pagesToVisit = new LinkedList<String>();

    private String nextUrl()
    /** We get the first entry from pagesToVisit, make sure that URL isn't in our set of URLs we visited, and then return it.
     *  If for some reason we've already visited the URL (meaning it's in our set pagesVisited) we keep looping through the list of pagesToVisit and returning the next URL.*/
    {
        String nextUrl;
        do
        {
            nextUrl = this.pagesToVisit.remove(0);
        } while(this.pagesVisited.contains(nextUrl));
        this.pagesVisited.add(nextUrl);
        return nextUrl;
    }
    public void search(String url)// String searchWord)
    /**
     * Our main launching point for the Crawler's functionality. Internally it creates s
     *  a HTTP request and parse the response (the web page).
     *
     * @param url
     *            - The starting point of the spider
     * @param searchWord
     *            - The word or string that you are searching for
     */
    {
        while(this.pagesVisited.size() < MAX_PAGES_TO_SEARCH)
        {
            String currentUrl;
            Hrqst leg = new Hrqst();
            if(this.pagesToVisit.isEmpty())
            {
                currentUrl = url;
                this.pagesVisited.add(url);
            }
            else
            {
                currentUrl = this.nextUrl();
            }
            leg.crawl(currentUrl); // Lots of stuff happening here. Look at the crawl method in
            // Htrqst
           /** boolean success = leg.searchForWord(searchWord);
            if(success)
            {
                System.out.println(String.format("**Success** Word %s found at %s", searchWord, currentUrl));
                break;
            }*/
            this.pagesToVisit.addAll(leg.getLinks());
        }
        System.out.println(String.format("**Done** Visited %s web page(s)", this.pagesVisited.size()));

            }
    }

