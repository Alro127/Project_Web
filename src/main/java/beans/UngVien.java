package beans;

import java.util.Date;

public class UngVien {
    private String fullName;     // Họ tên
    private String gender;       // Giới tính
    private Date dob;            // Ngày sinh
    private String phone;        // Số điện thoại
    private String email;        // Email
    private String location;     // Tỉnh thành
    private String address;      // Địa chỉ
    private String introduction; // Giới thiệu bản thân

    // Constructor
    public UngVien(String fullName, String gender, Date dob, String phone, String email, String location, String address, String introduction) {
        this.fullName = fullName;
        this.gender = gender;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.location = location;
        this.address = address;
        this.introduction = introduction;
    }

    // Getters and Setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
