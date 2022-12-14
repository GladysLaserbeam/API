package pojo;

import java.util.List;
import java.util.Map;

public class PokemonPojo {
    //defines all unique keys from response body
    private int count;
    private String next;
    private String previous; //previous is null but using string
    private List<Map<String, String>> results;

    //since the variables are private we need to generate getters and setters

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Map<String, String>> getResults() {
        return results;
    }

    public void setResults(List<Map<String, String>> results) {
        this.results = results;
    }
}
