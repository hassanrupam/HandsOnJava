package com.hassan.main.core.dto.prj;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * This class Serves as The Data Transfer Object for TaskInformation Model.
 *
 *
 * @Author: Hassan Sakib Afrin
 * @Created: 01-07-2021 11.09 PM
 */
public class ProjectDTO {

    //region PRIVATE FIELDS
    private UUID prjId;

    @NotNull(message = "project.pstNameRequired")
    @Size(max = 100, message = "project.pstNameLength")
    private String prjName;

    @NotNull(message = "project.prjDescriptionRequired")
    @Size(max = 512,message = "project.prjDescriptionLength")
    private String prjDescription;

    @NotNull(message = "project.prjStatusRequired")
    @Size(max = 20,message = "project.prjStatusLength")
    private String prjStatus;
    //endregion

    //region CONSTRUCTOR
    public ProjectDTO() {
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
}
