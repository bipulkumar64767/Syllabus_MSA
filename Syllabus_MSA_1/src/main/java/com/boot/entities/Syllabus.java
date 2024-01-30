package com.boot.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;

@Data
@Entity
@Table(name = "syllabus_table")
public class Syllabus implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer sId;

    @Column(nullable = false)
    private String sName;

    @NotNull
    private String sMedium;

    @Column(nullable = false)
    private String sStatus;

    private String sDesc;

    @Column(nullable = false)
    private Boolean sExpiry;

    public Syllabus(Boolean sExpiry) {
        this.sExpiry = sExpiry;
    }

    @Override
    public String toString() {
        return "Syllabus{" +
                "sId=" + sId +
                ", sName='" + sName + '\'' +
                ", sMedium='" + sMedium + '\'' +
                ", sStatus='" + sStatus + '\'' +
                ", sDesc='" + sDesc + '\'' +
                ", sExpiry=" + sExpiry +
                '}';
    }
    public Boolean getsExpiry() {
        return sExpiry;
    }
    public void setsExpiry(Boolean sExpiry) {
        this.sExpiry = sExpiry;
    }
    public Syllabus() {
    }
    public Syllabus(String sName, String sMedium, String sStatus, String sDesc) {
        this.sName = sName;
        this.sMedium = sMedium;
        this.sStatus = sStatus;
        this.sDesc = sDesc;
    }
    public Syllabus(Integer sId, String sName, String sMedium, String sStatus, String sDesc) {
        this.sId = sId;
        this.sName = sName;
        this.sMedium = sMedium;
        this.sStatus = sStatus;
        this.sDesc = sDesc;
    }
    public Integer getsId() {
        return sId;
    }
    public void setsId(Integer sId) {
        this.sId = sId;
    }
    public String getsName() {
        return sName;
    }=
    public void setsName(String sName) {
        this.sName = sName;
    }
    public String getsMedium() {
        return sMedium;
    }
    public void setsMedium(String sMedium) {
        this.sMedium = sMedium;
    }
    public String getsStatus() {
        return sStatus;
    }
    public void setsStatus(String sStatus) {
        this.sStatus = sStatus;
    }
    public String getsDesc() {
        return sDesc;
    }
    public void setsDesc(String sDesc) {
        this.sDesc = sDesc;
    }
}
