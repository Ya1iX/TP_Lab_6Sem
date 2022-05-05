package hiberlab.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_groups", uniqueConstraints = @UniqueConstraint(columnNames = "Name"))
public class Group implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Cipher", unique = true, nullable = false)
    private Integer cipher;

    @Column(name = "Name", unique = true, nullable = false, length = 50)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "CreateDate", nullable = false, length = 10)
    private Date createDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Plan", nullable = false)
    private Plan plan;

    @Column(name = "Status", length = 50)
    private String status;

    @Temporal(TemporalType.DATE)
    @Column(name = "StatusDate", length = 10)
    private Date statusDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    private List<Student> students;

    public Group() {
    }

    public Group(String name, Date createDate, Plan plan) {
        this.name = name;
        this.createDate = createDate;
        this.plan = plan;
    }

    public Group(String name, Date createDate, Plan plan, String status, Date statusDate, List<Student> students) {
        this.name = name;
        this.createDate = createDate;
        this.plan = plan;
        this.status = status;
        this.statusDate = statusDate;
        this.students = students;
    }

    public Integer getCipher() {
        return this.cipher;
    }

    public void setCipher(Integer cipher) {
        this.cipher = cipher;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
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

    public List<Student> getStudents() {
        return this.students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "[ID: " + this.cipher +
                ", Name: " + this.name +
                ", CreateDate: " + this.createDate +
                ", Plan: " + this.plan +
                ", Status: " + this.status +
                ", StatusDate: " + this.statusDate + "]";
    }
}


