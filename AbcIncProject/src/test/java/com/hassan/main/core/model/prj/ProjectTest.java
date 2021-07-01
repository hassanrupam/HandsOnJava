package com.hassan.main.core.model.prj;

import com.hassan.main.core.model.prj.Project;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    //region PRIVATE FIELDS
    private  UUID uuid= UUID.randomUUID();
    private Project project =  new Project();
    //endregion

    //region Tst Methods
    @BeforeEach
    void setUp() {
        project.setPrjId(uuid);
        project.setPrjName("Project Test");
        project.setPrjDescription("Project Description");
        project.setPrjStatus("InProgress");
    }

    @Test
    void getPrjId() {
        //region CASE 1- getPrjId returns Property value
        assertEquals(uuid,project.getPrjId());
        //endregion
    }

    @Test
    void setPrjId() {
        //region CASE 1- setPrjId sets the Property value
        project.setPrjId(uuid);
        assertEquals(uuid,project.getPrjId());
        //endregion
    }

    @Test
    void getPrjName() {
        //region CASE 1- getPrjName returns Property value
        assertEquals("Project Test",project.getPrjName());
        //endregion

        //region CASE 2 - if no value is set then getPrjName returns null
        project =  new Project();
        assertEquals(null,project.getPrjName());
        //endregion
    }

    @Test
    void setPrjName() {
        //region CASE 1- getPrjName returns Property value
        project =  new Project();
        project.setPrjName("Name Changed");
        assertEquals("Name Changed",project.getPrjName());
        //endregion
    }

    @Test
    void getPrjDescription() {
        //region CASE 1- getPrjDescription returns Property value
        assertEquals("Project Description",project.getPrjDescription());
        //endregion

        //region CASE 2 - if no value is set then getPrjDescription returns null
        project =  new Project();
        assertEquals(null,project.getPrjDescription());
        //endregion
    }

    @Test
    void setPrjDescription() {
        //region CASE 1- setPrjDescription sets the Property value
        project.setPrjDescription("Description Changed");
        assertEquals("Description Changed",project.getPrjDescription());
        //endregion
    }

    @Test
    void getPrjStatus() {
        //region CASE 1- getPrjStatus returns Property value
        assertEquals("InProgress",project.getPrjStatus());
        //endregion

        //region CASE 2 - if no value is set then getPrjStatus returns null
        project =  new Project();
        assertEquals(null,project.getPrjStatus());
        //endregion
    }

    @Test
    void setPrjStatus() {
        //region CASE 1- setPrjStatus sets the Property value
        project =  new Project();
        project.setPrjStatus("Completed");
        assertEquals("Completed",project.getPrjStatus());
        //endregion
    }

    @AfterEach
    void tearDown() {
        //Set the Instance to Null For GC
        uuid =null;
        project=null;
    }
    //endregion
}