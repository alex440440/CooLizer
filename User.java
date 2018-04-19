package CooLizer;

import com.google.gson.annotations.Expose;

public class User {

    @Expose
    private int id;

    @Expose
    private OptionalField<String> image = OptionalField.createWithValue(null);

    @Expose
    private OptionalField<AccountDetails> accountDetails = OptionalField.createWithValue(null);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image.getValue();
    }

    public AccountDetails getAccountDetails(){
        return accountDetails.getValue();
    }

    public void setImage(String imagePath){
        this.image= OptionalField.createWithValue(imagePath);
    }

    public void removeImage(){
        this.image=OptionalField.createWithNull();
    }

    public void setAccountDetails(AccountDetails accountDetailsField) {
        this.accountDetails = OptionalField.createWithValue(accountDetailsField);
    }

    public void removeAccountDetails(){
        this.accountDetails = OptionalField.createWithNull();
    }
}
