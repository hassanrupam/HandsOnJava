package com.hassan.main.core.dto.tsk;

import com.hassan.main.core.model.prj.Project;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This class Serves as The Data Transfer Object for TaskInformation Model.
 *
 *
 * @Author: Hassan Sakib Afrin
 * @Created: 01-07-2021 10.47 PM
 */
public class TaskInformationDTO {

    //region PRIVATE FIELDS
    private Long tskId;

    @NotNull(message = "taskInformation.tskProjectRequired")
    private Project tskProject;

    @NotNull(message = "taskInformation.tskNameRequired")
    @Size(max = 250,message = "taskInformation.tskNameLength")
    private String tskName;

    @NotNull(message = "taskInformation.tskStatusRequired")
    @Size(max = 20 ,message = "taskInformation.tskStatusLength")
    private String tskStatus;
    //endregion

    //region CONSTRUCTOR
    public TaskInformationDTO() {
    }
    //endregion

    //region GETTER * SETTER
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

}
