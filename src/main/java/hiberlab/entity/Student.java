package hiberlab.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_students")
public class Student implements java.io.Serializable {
    @Id
    @Column(name = "StudentNum", unique = true, nullable = false)
    private long studentNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GroupCipher", nullable = false)
    private Group group;

    @Column(name = "Surname", nullable = false, length = 30)
    private String surname;

    @Column(name = "Name", nullable = false, length = 30)
    private String name;

    @Column(name = "Patronymic", nullable = false, length = 30)
    private String patronymic;

    @Column(name = "City", nullable = false, length = 30)
    private String city;

    @Column(name = "Address", nullable = false, length = 80)
    private String address;

    @Column(name = "Phone", nullable = false, length = 30)
    private String phone;

    @Column(name = "Status", length = 50)
    private String status;

    @Temporal(TemporalType.DATE)
    @Column(name = "StatusDate", length = 10)
    private Date statusDate;

    public Student() {

    }

    public Student(long StudentNum, Group group, String surname, String name, String patronymic, String city, String address, String phone) {
        this.studentNum = StudentNum;
        this.group = group;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.city = city;
        this.address = address;
        this.phone = phone;
    }

    public Student(long StudentNum, Group group, String surname, String name, String patronymic, String city, String address, String phone, String status, Date statusDate) {
        this.studentNum = StudentNum;
        this.group = group;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.status = status;
        this.statusDate = statusDate;
    }

    public long getStudentNum() {
        return this.studentNum;
    }

    public void setStudentNum(long studentNum) {
        this.studentNum = studentNum;
    }

    public Group getGroup() {
        return this.group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return this.patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStatusDate() {
        return this.statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    @Override
    public String toString() {
        return "[ID: " + this.studentNum +
                ", Surname: " + this.surname +
                ", Name: " + this.name +
                ", Patronymic: " + this.patronymic +
                ", StudentNum: " + this.studentNum +
                ", Group: " + this.group.getName() +
                ", Status: " + this.status +
                ", StatusDate: " + this.statusDate + "]";
    }
}


