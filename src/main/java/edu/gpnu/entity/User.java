package edu.gpnu.entity;

import java.io.Serializable;

public class User implements Serializable {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 班级
     */
    private String clase;

    /**
     * 学号
     */
    private String studentId;

    /**
     * 性别
     */
    private String sex;

    /**
     * 学校
     */
    private String school;

    /**
     * 校区
     */
    private String campus;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", clase='" + clase + '\'' +
                ", studentId='" + studentId + '\'' +
                ", sex='" + sex + '\'' +
                ", school='" + school + '\'' +
                ", campus='" + campus + '\'' +
                '}';
    }
}
