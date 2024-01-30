package com.boot.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SyllabusDto
{
    private Integer sId;

    @NotNull
    private String sName;
    @NotNull
    private String sMedium;
    @NotNull
    private String sStatus;

    private String sDesc;
    @NotNull
    private Boolean sExpiry;

    public Integer getsId() {
        return sId;
    }

    public SyllabusDto(){
    }
    public SyllabusDto(String sName, String sMedium, String sStatus, String sDesc, Boolean sExpiry) {
        this.sName = sName;
        this.sMedium = sMedium;
        this.sStatus = sStatus;
        this.sDesc = sDesc;
        this.sExpiry = sExpiry;
    }
    public SyllabusDto(Integer sId, String sName, String sMedium, String sStatus, String sDesc, Boolean sExpiry) {
        this.sId = sId;
        this.sName = sName;
        this.sMedium = sMedium;
        this.sStatus = sStatus;
        this.sDesc = sDesc;
        this.sExpiry = sExpiry;
    }
    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

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

    public Boolean getsExpiry() {
        return sExpiry;
    }

    public void setsExpiry(Boolean sExpiry) {
        this.sExpiry = sExpiry;
    }
}
