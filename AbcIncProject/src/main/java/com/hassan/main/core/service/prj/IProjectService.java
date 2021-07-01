package com.hassan.main.core.service.prj;

import com.hassan.main.core.dto.prj.ProjectDTO;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Service Layer Interface for the Project
 * This Will Serve all the Business and Validations for Project
 *
 * @Author: Hassan Sakib Afrin
 * @Created: 01-07-2021 11.59 PM
 */
public interface IProjectService {

    /**
     * This method serves as the Data Transfer with Validation from
     * the Data Layer to The Controller Layer
     *
     * @param prjId Project Id (Unique Id For the Project)
     * @return Information Of Project as ProjectDTO Object
     */
    public ProjectDTO getById(UUID prjId);

    /**
     * This method serves to get All the Information of Project as List
     *
     * @return List Of ProjectDTO
     */
    public List<ProjectDTO> getList();

    /**
     * This method serves to get Information for Project as Page with the parameters passed
     *
     * @param page No of Page
     * @param size Total Size
     * @return
     */
    public Page<ProjectDTO> getListAsPage(int page,int size);

    /**
     * This method serves to Validate the Provided Data and Save the Project Information.
     *
     * @param projectDTO Project Information as an Object
     * @return HashMap<String,Object> containing the response Message
     */
    public HashMap<String,Object> save(ProjectDTO projectDTO);

    /**
     * This method serves to Validate the Provided Data and Update the Project Information.
     *
     * @param projectDTO Project Information as an Object
     * @return HashMap<String,Object> containing the response Message
     */
    public HashMap<String,Object> update(ProjectDTO projectDTO);

}
