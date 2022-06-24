package com.lsx.pojo;

public class Student {
    private int id;
    private String name;
    private String phone;
    private String avatar;
    private int studentId;

//    private String email;
//    private String qq;
//    private String wechat;
//    private String address;
//    private String school;
//    private String major;
//    private String grade;
//    private String className;
//    private String idCard;
//    private String studentId;
//    private String


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public String toString(){
    return "{id:"
        + id
        + "\n"
        + "name:"
        + name
        + "\n"
        + "phone:"
        + phone
        + "\n"
        + "avatar:"
        + avatar
        + "\n"
        + "studentId:"
        + studentId
        + "\n}";
    }
}
