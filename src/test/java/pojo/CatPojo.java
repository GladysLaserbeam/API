package pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
@Getter
@Setter
public class CatPojo {

    private int current_page;
    private List<Map<String,Object>>data;
    private String first_page_url;
    private int from;
    private int last_page;
    private String last_page_url;
    private List<Map<String,Object>>links;
    private String next_page_url;
    private String path;
    private int per_page;
    private String prev_page_url;
    private int to;
    private int total;


}
