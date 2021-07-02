package com.hassan.main.core.dao.prj;

import com.hassan.main.core.model.prj.Project;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Data Access Object For Project
 * This Will Serve all the Database Functionality Regarding Project Entity
 *
 * @Author: Hassan Sakib Afrin
 * @Created: 01-07-2021 11.25 PM
 */
public interface IProjectDao {
    /**
     * This Method return the Project Details by the Provided Id
     * If no Project is found then it will return Empty Object
     *
     * @param prjId Task Id ( Unique Id For the Project)
     * @return Optional<Project> object
     */
    public Optional<Project> getById(UUID prjId);

    /**
     * This Method return all the Existing Project as  
     * List opf Project objects
     *
     * @return  List<Project>
     */
    public List<Project> getList();

    /**
     * This method gets the Project as Page according to the parameter passed
     *
     * @param page No of Page
     * @param size Total Size
     * @return
     */
    public Page<Project> getListAsPage(int page,int size);

    /**
     * This method serves for save and Update together for the Project in Database
     * If The primary Key is Set to Null or any non existing Id from Database, 
     * it will save the Information
     *
     * If The Primary Key has an existing Id, this method will update 
     * the Information with the Provided information.
     *
     * @param project The Project Object
     * @return The Saved/Updated Information as Project Object
     */
    public Project save(Project project);
    
    /**
     * This method serves for Deleting the Project with the Provided id
     *
     * @param project TEntity For the Project to be Deleted
     * @return The Deleted Information as Project Object
     */
    public Project delete(Project project);
}
