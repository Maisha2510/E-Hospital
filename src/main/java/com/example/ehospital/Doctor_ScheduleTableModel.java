package com.example.ehospital;

public class Doctor_ScheduleTableModel {
    private String  DepartmentName, DoctorName, DoctorId, VisitingDays, Time;
    private int SerialNo;

    public Doctor_ScheduleTableModel(String departmentName, String doctorName, String doctorId, String visitingDays, String time, int serialNo) {
        DepartmentName = departmentName;
        DoctorName = doctorName;
        DoctorId = doctorId;
        VisitingDays = visitingDays;
        Time = time;
        SerialNo = serialNo;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(String doctorId) {
        DoctorId = doctorId;
    }

    public String getVisitingDays() {
        return VisitingDays;
    }

    public void setVisitingDays(String visitingDays) {
        VisitingDays = visitingDays;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getSerialNo() {
        return SerialNo;
    }

    public void setSerialNo(int serialNo) {
        SerialNo = serialNo;
    }
}
