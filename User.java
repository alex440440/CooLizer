package CooLizer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;

public class User {

    @Expose
    private int id;

    @Expose
    private OptionalField<String> image = OptionalField.createWithValue(null);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image.getValue();
    }

    public void setImage(String imagePath){
        this.image= OptionalField.createWithValue(imagePath);
    }

    public void removeImage(){
        this.image=OptionalField.createWithNull();
    }
}
