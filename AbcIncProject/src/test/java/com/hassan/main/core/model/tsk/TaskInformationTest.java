package com.hassan.main.core.model.tsk;

import com.hassan.main.core.model.prj.Project;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TaskInformationTest {

    //region PRIVATE FIELDS
    private UUID uuid= UUID.randomUUID();
    private Project project =  new Project();
    private TaskInformation taskInformation =  new TaskInformation();
    //endregion

    @BeforeEach
    void setUp() {
        project =  new Project(uuid,"Project Test","Project Description","InProgress");
        taskInformation =  new TaskInformation(1L,project,"Task 1","Completed");
    }

    @Test
    void getTskId() {
        //region CASE 1- getTskId returns Property value
        assertEquals(1L,taskInformation.getTskId());
        //endregion
    }

    @Test
    void setTskId() {
        //region CASE 1- setTskId sets the Property value
        taskInformation.setTskId(3L);
        assertEquals(3L,taskInformation.getTskId());
        //endregion
    }

    @Test
    void getTskProject() {
        //region CASE 1- getTskProject returns Property value
        assertEquals(project,taskInformation.getTskProject());
        //endregion

        //region CASE 2 - if no value is set then getTskProject returns null
        taskInformation =  new TaskInformation();
        assertEquals(null,taskInformation.getTskProject());
        //endregion
    }

    @Test
    void setTskProject() {
        //region CASE 1- setTskProject returns Property value
        taskInformation =  new TaskInformation();
        project = new Project(UUID.randomUUID(),"New Project","New Dewsscription","Completed");
        taskInformation.setTskProject(project);
        assertEquals(project,taskInformation.getTskProject());
        //endregion
    }

    @Test
    void getTskName() {
        //region CASE 1- getTskName returns Property value
        assertEquals("Task 1",taskInformation.getTskName());
        //endregion

        //region CASE 2 - if no value is set then getTskName returns null
        taskInformation =  new TaskInformation();
        assertEquals(null,taskInformation.getTskName());
        //endregion
    }

    @Test
    void setTskName() {
        //region CASE 1- setTskName returns Property value
        taskInformation =  new TaskInformation();
        taskInformation.setTskName("Name Changed");
        assertEquals("Name Changed",taskInformation.getTskName());
        //endregion
    }

    @Test
    void getTskStatus() {
        //region CASE 1- getTskStatus returns Property value
        assertEquals("Completed",taskInformation.getTskStatus());
        //endregion

        //region CASE 2 - if no value is set then getTskStatus returns null
        taskInformation =  new TaskInformation();
        assertEquals(null,taskInformation.getTskStatus());
        //endregion
    }

    @Test
    void setTskStatus() {
        //region CASE 1- setTskStatus sets the Property value
        taskInformation =  new TaskInformation();
        taskInformation.setTskStatus("Completed");
        assertEquals("Completed",taskInformation.getTskStatus());
        //endregion
    }

    @AfterEach
    void tearDown() {
        //Set the Instance to Null For GC
        uuid =null;
        project=null;
        taskInformation=null;
    }

}