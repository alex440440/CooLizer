package CooLizer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    private static Gson gson = createGson();

    public static void main(String args[])
    {
        testUserWithRemovedImage();
        testUserWithAlwaysNullImage();
        testUserWithValidImage();

        testDeserializeWithAbsent();
        testDeserializeWithNull();
        testDeserializeWithValue();

    }

    private static void testUserWithAlwaysNullImage() {
        User user1 = new User();
        user1.setId(1);
        user1.setImage(null);

        serializeUser(gson, user1);
    }

    private static void testUserWithRemovedImage() {
        User user1 = new User();
        user1.setId(1);
        user1.removeImage();

        serializeUser(gson, user1);
    }

    private static void testUserWithValidImage() {
        User user1 = new User();
        user1.setId(1);
        user1.setImage("path/to/image");

        serializeUser(gson, user1);
    }

    private static void testDeserializeWithNull(){
        String s = "{\n" +
                "  \"id\": 1,\n" +
                "  \"image\": null\n" +
                "}";
        deserializeUser(s);
    }


    private static void testDeserializeWithAbsent(){
        String s = "{\n" +
                "  \"id\": 1\n" +
                "}";
        deserializeUser(s);
    }

    private static void testDeserializeWithValue(){
        String s = "{\n" +
                "  \"id\": 1,\n" +
                "  \"image\": \"path/to/image\"\n" +
                "}";
        deserializeUser(s);
    }

    private static void serializeUser(Gson gson, User user1) {
        String s = gson.toJson(user1);
        System.out.println(s);
    }

    private static User deserializeUser(String json){
        User user = gson.fromJson(json, User.class);
        if (user.getImage()==null){
            System.out.println("image is null");
        }else{
            System.out.println("image is: "+user.getImage());
        }
        return user;
    }

    private static Gson createGson() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeHierarchyAdapter(OptionalField.class, new GsonOptionalFieldAdapter());
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        gsonBuilder.setPrettyPrinting();

        final Gson gson = gsonBuilder.create();
        return gson;
    }
}
