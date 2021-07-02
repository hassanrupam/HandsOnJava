package com.hassan.main.core.model.prj;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * This class Serves as The Model for the Entity Project.
 *
 *
 * @Author: Hassan Sakib Afrin
 * @Created: 01-07-2021 10.12 PM
 */
@Entity
@Table(name = "project")
public class Project implements Serializable {

    //region PRIVATE FIELDS
    @Id
    @GeneratedValue(generator="uuid2")
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    @Column(name = "prj_id",columnDefinition = "CHAR(36)",nullable = false, unique = true, length = 36)
    @Type(type = "uuid-char")
    private UUID prjId;

    @Column(name = "prj_name")
    @NotNull
    @Size(max = 100)
    private String prjName;

    @Column(name = "prj_description")
    @NotNull
    @Size(max = 512)
    private String prjDescription;

    @Column(name = "prjStatus")
    @NotNull
    @Size(max = 20)
    private String prjStatus;
    //endregion

    //region CONSTRUCTOR
    public Project() {
    }

    public Project(UUID prjId, @NotNull @Size(max = 100) String prjName, @NotNull @Size(max = 512) String prjDescription, @NotNull @Size(max = 20) String prjStatus) {
        this.prjId = prjId;
        this.prjName = prjName;
        this.prjDescription = prjDescription;
        this.prjStatus = prjStatus;
    }

    //endregion

    //region GETTER & SETTER
    public UUID getPrjId() {
        return prjId;
    }

    public void setPrjId(UUID prjId) {
        this.prjId = prjId;
    }

    public String getPrjName() {
        return prjName;
    }

    public void setPrjName(String prjName) {
        this.prjName = prjName;
    }

    public String getPrjDescription() {
        return prjDescription;
    }

    public void setPrjDescription(String prjDescription) {
        this.prjDescription = prjDescription;
    }

    public String getPrjStatus() {
        return prjStatus;
    }

    public void setPrjStatus(String prjStatus) {
        this.prjStatus = prjStatus;
    }
    //endregion

    //region HASHCODE , EQUALS & TO STRING
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(prjId, project.prjId) && Objects.equals(prjName, project.prjName) && Objects.equals(prjDescription, project.prjDescription) && Objects.equals(prjStatus, project.prjStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prjId, prjName, prjDescription, prjStatus);
    }

    @Override
    public String toString() {
        return "Project{" +
                "prjId=" + prjId +
                ", prjName='" + prjName + '\'' +
                ", prjDescription='" + prjDescription + '\'' +
                ", prjStatus='" + prjStatus + '\'' +
                '}';
    }
    //endregion
}
