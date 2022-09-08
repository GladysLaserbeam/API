package utils;

public class PayLoadUtils {
    public static String getPetPayLoad(){
        return "{\n" +
                "  \"id\": 567341222,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"Luna\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"in transit from java code\"\n" +
                "}";
    }

    public static String getSlackPayLoad(String message){
        return "{\n" +
                "    \"channel\": \"C03NJHUM474\",\n" +
                "    \"text\": \"" + message + "\"\n" +
                "}";
    }
}
