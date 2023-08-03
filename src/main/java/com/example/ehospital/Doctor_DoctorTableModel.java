package com.example.ehospital;

public class Doctor_DoctorTableModel {
    private String FirstName, LastName, Department, EmailAddress,MobileNo,PhoneNo,Address
            ,Sex,BloodGroup,DOB,JoinDate,Status;
    private int SerialNo,Fees;

    public Doctor_DoctorTableModel(int serialNo, String firstName, String lastName, String department, String emailAddress, String mobileNo, String phoneNo, String address, String sex, String bloodGroup, String DOB, String joinDate, String status, int fees) {
        SerialNo = serialNo;
        FirstName = firstName;
        LastName = lastName;
        Department = department;
        EmailAddress = emailAddress;
        MobileNo = mobileNo;
        PhoneNo = phoneNo;
        Address = address;
        Sex = sex;
        BloodGroup = bloodGroup;
        this.DOB = DOB;
        JoinDate = joinDate;
        Status = status;

        Fees = fees;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        BloodGroup = bloodGroup;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getJoinDate() {
        return JoinDate;
    }

    public void setJoinDate(String joinDate) {
        JoinDate = joinDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getFees() {
        return Fees;
    }

    public void setFees(int fees) {
        Fees = fees;
    }

    public int getSerialNo() {
        return SerialNo;
    }

    public void setSerialNo(int serialNo) {
        SerialNo = serialNo;
    }
}
