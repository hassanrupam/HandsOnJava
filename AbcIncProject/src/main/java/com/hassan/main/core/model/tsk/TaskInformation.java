package com.hassan.main.core.model.tsk;

import com.hassan.main.core.model.prj.Project;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
/**
 * This class Serves as The Model for the Entity Task Information.
 *
 *
 * @Author: Hassan Sakib Afrin
 * @Created: 01-07-2021 10.33 PM
 */
@Entity
@Table(name = "task_information")
public class TaskInformation  implements Serializable {
    //region PRIVATE FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tsk_id")
    private Long tskId;

    @JoinColumn(name = "tsk_project")
    @ManyToOne
    @NotNull
    private Project tskProject;

    @Column(name = "tsk_name")
    @NotNull
    @Size(max = 250)
    private String tskName;

    @Column(name = "tsk_status")
    @NotNull
    @Size(max = 20)
    private String tskStatus;
    //endregion

    //region CONSTRUCTOR
    public TaskInformation() {
    }

    public TaskInformation(Long tskId, @NotNull Project tskProject, @NotNull @Size(max = 250) String tskName, @NotNull @Size(max = 20) String tskStatus) {
        this.tskId = tskId;
        this.tskProject = tskProject;
        this.tskName = tskName;
        this.tskStatus = tskStatus;
    }
    //endregion

    //region GETTER & SETTER
    public Long getTskId() {
        return tskId;
    }

    public void setTskId(Long tskId) {
        this.tskId = tskId;
    }

    public Project getTskProject() {
        return tskProject;
    }

    public void setTskProject(Project tskProject) {
        this.tskProject = tskProject;
    }

    public String getTskName() {
        return tskName;
    }

    public void setTskName(String tskName) {
        this.tskName = tskName;
    }

    public String getTskStatus() {
        return tskStatus;
    }

    public void setTskStatus(String tskStatus) {
        this.tskStatus = tskStatus;
    }
    //endregion

    //region HASHCODE , EQUALS & TO STRING
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskInformation that = (TaskInformation) o;
        return Objects.equals(tskId, that.tskId) && Objects.equals(tskProject, that.tskProject) && Objects.equals(tskName, that.tskName) && Objects.equals(tskStatus, that.tskStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tskId, tskProject, tskName, tskStatus);
    }

    @Override
    public String toString() {
        return "TaskInformation{" +
                "tskId=" + tskId +
                ", tskProject=" + tskProject +
                ", tskName='" + tskName + '\'' +
                ", tskStatus='" + tskStatus + '\'' +
                '}';
    }
    //endregion
}
