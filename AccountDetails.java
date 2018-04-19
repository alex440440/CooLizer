package CooLizer;

import com.google.gson.annotations.Expose;

public class AccountDetails {

    @Expose
    OptionalField<String> accountCredentials=OptionalField.createWithValue(null);

    @Expose
    private int accountNumber;

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountCredentials() {
        return accountCredentials.getValue();
    }

    public void setAccountCredentials(String accountCredentials) {
        this.accountCredentials=OptionalField.createWithValue(accountCredentials);
    }
}
