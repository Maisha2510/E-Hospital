package com.example.ehospital;

public class Doctor_PatientTableModel {
    private String FirstName, LastName, EmailAddress, MobileNo, PhoneNo, Address, Gender, BloodGroup,DOB, CreateDate, Status, PatientId;
    private int SerialNo;

    public Doctor_PatientTableModel(String firstName, String lastName, String emailAddress, String mobileNo, String phoneNo, String address, String gender, String bloodGroup, String DOB, String createDate, String status, String patientId, int serialNo) {
        FirstName = firstName;
        LastName = lastName;
        EmailAddress = emailAddress;
        MobileNo = mobileNo;
        PhoneNo = phoneNo;
        Address = address;
        Gender = gender;
        BloodGroup = bloodGroup;
        this.DOB = DOB;
        CreateDate = createDate;
        Status = status;
        PatientId = patientId;
        SerialNo = serialNo;
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

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
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

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPatientId() {
        return PatientId;
    }

    public void setPatientId(String patientId) {
        PatientId = patientId;
    }

    public int getSerialNo() {
        return SerialNo;
    }

    public void setSerialNo(int serialNo) {
        SerialNo = serialNo;
    }
}
