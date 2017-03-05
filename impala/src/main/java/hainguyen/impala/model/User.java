package hainguyen.impala.model;

import com.google.gson.annotations.SerializedName;

import hainguyen.impala.network.model.LoginResponse;

public class User {

    @SerializedName("FullName")
    public String fullName;

    @SerializedName("DateOfBirth")
    public String dateOfBirth;

    @SerializedName("Gender")
    public String gender;

    @SerializedName("Address")
    public String address;

    @SerializedName("Phone")
    public String phone;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User transformFrom(LoginResponse response) {
        this.fullName = response.fullName();
        this.address = response.address();
        this.dateOfBirth = response.dateOfBirth();
        this.gender = response.gender();
        this.phone = response.phone();
        return null;
    }
}
