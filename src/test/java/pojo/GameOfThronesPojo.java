package pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
@Getter
@Setter
public class GameOfThronesPojo {

    private Map<String,String> titles;
    private Map<String,String> origin;
    private Map<String,String> siblings;
    private Map<String,String> spouse;
    private String culture;
    private Map<String,String> religion;
    private Map<String,String> allegiances;
    private String seasons;
    private List<String> appearences;
    private String id;
    private List<Map<String,String>> name;
    private List<Map<String,String>> slug;
    private String image;
    private String gender;
    private boolean alive;
    private int death;
    private String father;
    private String house;
    private String first_seen;
    private String actor;
    private List<Map<String,Object>> related;
    private String createdAt;
    private String updatedAt;
    private int __v;
    private Map<String,Object> age;
    private Map<String,Object> pagerank;
    private String mother;


}
