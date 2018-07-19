package bds.dao;

import bds.dto.PointDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name="roletable")
public class RoleRecord {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "ID")
    int id;

    @Column(name = "NAME")
    String name;

    @Column(name = "ARCHIVE")
    boolean archive;

    @Override
    public String toString() {
        return "RoleRecord{ id=" + id + ", name=" + name + ", archive=" + archive  + " }";
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

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

}
