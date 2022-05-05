package hiberlab.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "t_plans")
public class Plan implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    private long id;

    @NotNull
    private String subjects;

    public Plan() {
    }

    public Plan(String subjects) {
        this.subjects = subjects;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String toString() {
        return "[ID: " + this.id +
                ", Subjects: " + this.subjects + "]";
    }
}
