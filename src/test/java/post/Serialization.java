package post;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import pojo.PetPojo;

import java.io.File;
import java.io.IOException;

public class Serialization {

    //java to json
    @Test
    public void serialization() throws IOException {
        PetPojo pet = new PetPojo();

        pet.setId(67389323);
        pet.setName("Pet from Java");
        pet.setStatus("serialized");

        ObjectMapper objectMapper = new ObjectMapper();
        File file= new File("src/test/java/post/pet.json");
        objectMapper.writeValue(file, pet);
    }
}
