package com.hassan.main.core.dao.prj;

import com.hassan.main.core.model.prj.Project;
import com.hassan.main.core.repository.prj.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Data Access Object For Project Implementation
 * This Will Serve all the Database Functionality Regarding Project Entity
 *
 * @Author: Hassan Sakib Afrin
 * @Created: 01-07-2021 11.27 PM
 */
@Repository
public class ProjectDao implements IProjectDao {

    //region PRIVATE FIELDS
    @Autowired
    private IProjectRepository projectRepository;
    //endregion

    //region PUBLIC METHODS
    /**
     * This Method return the Project Details by the Provided Id
     * If no Project is found then it will return Empty Object
     *
     * @param prjId Task Id ( Unique Id For the Project)
     * @return Optional<Project> object
     */
    @Override
    public Optional<Project> getById(UUID prjId) {
        return projectRepository.findByPrjId(prjId);
    }

    /**
     * This Method return all the Existing Project as
     * List opf Project objects
     *
     * @return  List<Project>
     */
    @Override
    public List<Project> getList() {
        return projectRepository.findAll();
    }

    /**
     * This method gets the Project as Page according to the parameter passed
     *
     * @param page No of Page
     * @param size Total Size
     * @return
     */
    @Override
    public Page<Project> getListAsPage(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return projectRepository.findAll(pageable);
    }

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
    @Override
    public Project save(Project project) {
      return projectRepository.saveAndFlush(project);
    }

    /**
     * This method serves for Deleting the Project with the Provided id
     *
     * @param prjId Task Id ( Unique Id For the Project)
     * @return The Deleted Information as Project Object
     */
    @Override
    public Project delete(UUID prjId) {
        return projectRepository.deleteByPrjId(prjId);
    }
    //endregion
}
