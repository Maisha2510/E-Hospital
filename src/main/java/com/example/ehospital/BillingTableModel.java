package com.example.ehospital;

public class BillingTableModel {
    private String AppointmentID, BillDate, Status, PatientId,BillId;
    private int SerialNo,Discount,Total,AdditionalBill;

    public BillingTableModel(String appointmentID, String billDate, String status, String patientId, String billId, int serialNo, int discount, int total, int additionalBill) {
        AppointmentID = appointmentID;
        BillDate = billDate;
        Status = status;
        PatientId = patientId;
        BillId = billId;
        SerialNo = serialNo;
        Discount = discount;
        Total = total;
        AdditionalBill = additionalBill;
    }

    public String getAppointmentID() {
        return AppointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        AppointmentID = appointmentID;
    }

    public String getBillDate() {
        return BillDate;
    }

    public void setBillDate(String billDate) {
        BillDate = billDate;
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

    public String getBillId() {
        return BillId;
    }

    public void setBillId(String billId) {
        BillId = billId;
    }

    public int getSerialNo() {
        return SerialNo;
    }

    public void setSerialNo(int serialNo) {
        SerialNo = serialNo;
    }

    public int getDiscount() {
        return Discount;
    }

    public void setDiscount(int discount) {
        Discount = discount;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public int getAdditionalBill() {
        return AdditionalBill;
    }

    public void setAdditionalBill(int additionalBill) {
        AdditionalBill = additionalBill;
    }
}
