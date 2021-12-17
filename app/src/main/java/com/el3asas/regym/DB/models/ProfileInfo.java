package com.el3asas.regym.DB.models;

import androidx.annotation.Keep;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Keep
@Entity(tableName = "ProfileInfo")
public class ProfileInfo {

    @PrimaryKey
    private int id;
    private int age;
    private float weight;
    private String gender;
    private int height;
    private String jobStatus;
    private int BodyStatus;

    public ProfileInfo(){ }

    public ProfileInfo(int id, int age, float weight, String gender, int height, String jobStatus, int bodyStatus) {
        this.id = id;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
        this.height = height;
        this.jobStatus = jobStatus;
        BodyStatus = bodyStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBodyStatus() {
        return BodyStatus;
    }

    public void setBodyStatus(int bodyStatus) {
        BodyStatus = bodyStatus;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public int getAge() {
        return age;
    }

    public float getWeight() {
        return weight;
    }

    public String getGender() {
        return gender;
    }
}