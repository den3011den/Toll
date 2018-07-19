package bds.dao;

import bds.dto.PointDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name="usertable")
public class UserRecord {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "ID")
    int id;

    @Column(name = "NAME")
    String name;

    @Column(name = "FIRSTNAME")
    String firstName;

    @Column(name = "LASTNAME")
    String lastName;

    @Column(name = "MIDLENAME")
    String midleName;

    @Column(name = "PASSWORD")
    String password;

    @Column(name = "ARCHIVE")
    boolean archive;

    @Column(name = "ROLE")
    boolean role;

    public String toString() {
        return "UserRecord{ id=" + id + ", name=" + name + ", firstName=" + firstName +
                ", midleName=" + midleName + ", password=" + password + ", archive=" + archive +
                ", role=" + role + " }";
    }

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMidleName() {
        return midleName;
    }

    public void setMidleName(String midleName) {
        this.midleName = midleName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }
}
