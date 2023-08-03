package com.example.ehospital;

public class Doctor_AppoinmentTableModel {
    private int serialNo;
    private String AppointmentDate,AppointmentTime, ProblemDescription,PatientId,Department,DoctorId,DoctorName,appointmentId;

    public Doctor_AppoinmentTableModel(String appointmentDate, String appointmentTime, String problemDescription, String patientId, String department, String doctorId, String doctorName, String appointmentId) {
        AppointmentDate = appointmentDate;
        AppointmentTime = appointmentTime;
        ProblemDescription = problemDescription;
        PatientId = patientId;
        Department = department;
        DoctorId = doctorId;
        DoctorName = doctorName;
        this.appointmentId = appointmentId;
    }

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    public String getAppointmentDate() {
        return AppointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        AppointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return AppointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        AppointmentTime = appointmentTime;
    }

    public String getProblemDescription() {
        return ProblemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        ProblemDescription = problemDescription;
    }

    public String getPatientId() {
        return PatientId;
    }

    public void setPatientId(String patientId) {
        PatientId = patientId;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(String doctorId) {
        DoctorId = doctorId;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }
}
