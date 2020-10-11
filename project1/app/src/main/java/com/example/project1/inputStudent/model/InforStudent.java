package com.example.project1.inputStudent.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Student")
public class InforStudent {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo
    private String msv;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String classRoom;
    @ColumnInfo
    private String sex;
    @ColumnInfo
    private String matchPoint;
    @ColumnInfo
    private String chemistryPoint;
    @ColumnInfo
    private String physicalPoint;

    public InforStudent() {
    }

    public InforStudent(String msv, String name, String classRoom, String sex, String matchPoint, String chemistryPoint, String physicalPoint) {
        this.msv = msv;
        this.name = name;
        this.classRoom = classRoom;
        this.sex = sex;
        this.matchPoint = matchPoint;
        this.chemistryPoint = chemistryPoint;
        this.physicalPoint = physicalPoint;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMatchPoint() {
        return matchPoint;
    }

    public void setMatchPoint(String matchPoint) {
        this.matchPoint = matchPoint;
    }

    public String getChemistryPoint() {
        return chemistryPoint;
    }

    public void setChemistryPoint(String chemistryPoint) {
        this.chemistryPoint = chemistryPoint;
    }

    public String getPhysicalPoint() {
        return physicalPoint;
    }

    public void setPhysicalPoint(String physicalPoint) {
        this.physicalPoint = physicalPoint;
    }
}
