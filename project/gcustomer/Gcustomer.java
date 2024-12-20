package io.mobile.finalproject.gcustomer;

public class Gcustomer {
    private String customerId;
    private String customerName;
    private String phoneNumber;
    private int year;
    private int month;
    private int day;
    private String email;
    private int savedMoney;

    public Gcustomer(String customerId, String customerName, String phoneNumber, int year, int month, int day, String email, int savedMoney) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.year = year;
        this.month = month;
        this.day = day;
        this.email = email;
        this.savedMoney = savedMoney;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getEmail() {
        return email;
    }

    public int getSavedMoney() {
        return savedMoney;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPhone_number(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSavedMoney(int savedMoney) {
        this.savedMoney = savedMoney;
    }

    @Override
    public String toString() {
        return "Gcustomer{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", email='" + email + '\'' +
                ", savedMoney=" + savedMoney +
                '}';
    }
}
