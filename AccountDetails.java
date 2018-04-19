package CooLizer;

import com.google.gson.annotations.Expose;

public class AccountDetails {

    @Expose
    private int accountNumber;

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
}
