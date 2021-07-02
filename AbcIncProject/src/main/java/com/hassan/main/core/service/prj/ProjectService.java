package com.hassan.main.core.service.prj;

import com.hassan.main.core.dao.prj.IProjectDao;
import com.hassan.main.core.dto.prj.ProjectDTO;
import com.hassan.main.core.enumurations.DataValidationEnum;
import com.hassan.main.core.model.prj.Project;
import com.hassan.main.core.service.BaseService;
import com.hassan.main.core.utility.CustomServerResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.hassan.main.core.enumurations.ServerActionEnum.*;

/**
 * Service Layer Implementation for the Project
 * This Will Serve all the Business and Validations for Project
 *
 * @Author: Hassan Sakib Afrin
 * @Created: 02-07-2021 00.03 PM
 */
@Service
public class ProjectService extends BaseService implements IProjectService {

    //region PRIVATE FIELDS
    private Project project = new Project();

    @Autowired
    private IProjectDao projectDao;
    //endregion

    //region PUBLIC METHODS

    /**
     * This method serves as the Data Transfer with Validation from
     * the Data Layer to The Controller Layer
     *
     * @param prjId Project Id (Unique Id For the Project)
     * @return Information Of Project as ProjectDTO Object
     */
    @Override
    public ProjectDTO getById(UUID prjId) {
        return  projectDao.getById(prjId).map(this::convertFromEntityToDTO).orElse(new ProjectDTO());
    }

    /**
     * This method serves to get All the Information of Project as List
     *
     * @return List Of ProjectDTO
     */
    @Override
    public List<ProjectDTO> getList() {
        return projectDao.getList().stream().map(this::convertFromEntityToDTO).collect(Collectors.toList());
    }

    /**
     * This method serves to get Information for Project as Page with the parameters passed
     *
     * @param page No of Page
     * @param size Total Size
     * @return Page<ProjectDTO> - Page Object Containing List Of Project
     */
    @Override
    public Page<Project> getListAsPage(int page, int size) {
        return projectDao.getListAsPage(page,size);
    }

    /**
     * This method serves to Validate the Provided Data and Save the Project Information.
     *
     * @param projectDTO Project Information as an Object
     * @return CustomServerResponse containing the response Message
     */
    @Override
    public CustomServerResponse save(ProjectDTO projectDTO) {

        try {
            project = convertFromDTOToEntity(projectDTO);
        } catch (Exception e) {
            response.setStatus(DataValidationEnum.INVALID_STATUS.status());
            response.setMessage(messageSource.getMessage("common.dataConversionError", null, null));
            return response;
        }
        try {
            project = projectDao.save(project);
            response = setResponseAfterAction(project, SAVE.getAction());
        } catch (Exception e) {
            response.setStatus(DataValidationEnum.INVALID_STATUS.status());
            response.setMessage(e.getMessage());
            return response;
        }
        return response;
    }

    @Override
    public CustomServerResponse update(ProjectDTO projectDTO) {
        response = validateDTO(projectDTO);
        if (response.getStatus().equals(DataValidationEnum.INVALID_STATUS.status())) {
            return response;
        }
        if (!projectDao.getById(projectDTO.getPrjId()).isPresent()) {
            response.setStatus(DataValidationEnum.INVALID_STATUS.status());
            response.setMessage(messageSource.getMessage("project.notExist", null, null));
            return response;
        }

        try {
            project = convertFromDTOToEntity(projectDTO);
        } catch (Exception e) {
            response.setStatus(DataValidationEnum.INVALID_STATUS.status());
            response.setMessage(messageSource.getMessage("common.dataConversionError", null, null));
            return response;
        }
        try {
            project = projectDao.save(project);
            response = setResponseAfterAction(project, UPDATE.getAction());
        } catch (Exception e) {
            response.setStatus(DataValidationEnum.INVALID_STATUS.status());
            response.setMessage(e.getMessage());
            return response;
        }
        return response;
    }

    @Override
    public CustomServerResponse delete(UUID prjId) {
        if (!projectDao.getById(prjId).isPresent()) {
            response.setStatus(DataValidationEnum.INVALID_STATUS.status());
            response.setMessage(messageSource.getMessage("project.notExist", null, null));
            return response;
        }
        try {
            project = projectDao.delete(prjId);
            response = setResponseAfterAction(project, DELETE.getAction());
        } catch (Exception e) {
            response.setStatus(DataValidationEnum.INVALID_STATUS.status());
            response.setMessage(e.getMessage());
            return response;
        }
        return response;
    }
    //endregion

    //region PRIVATE METHODS

    /**
     * This method copies all values from the Entity to the Data Transfer Object
     *
     * @param project Entity Object Of Project
     * @return ProjectDTO - Data Transfer Object Of Project
     */
    private ProjectDTO convertFromEntityToDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        BeanUtils.copyProperties(project, projectDTO);
        return projectDTO;
    }

    /**
     * This method copies all values from the Data Transfer Object to the Entity Object
     *
     * @param projectDTO Data Transfer Object Of Project
     * @return Entity Object Of Project
     */
    private Project convertFromDTOToEntity(ProjectDTO projectDTO) {
        Project project = new Project();
        BeanUtils.copyProperties(projectDTO, project);
        return project;
    }

    /**
     * This method sets the proper response according to the Server Action
     * and by the Fact, if the Repository is able to Persist the requested action or not
     *
     * @param project      the entity object to check if the repository has returned with valid response
     * @param serverAction Character Stating the Flags for  either Save, Update Or Delete Action is Occurring
     * @return
     */
    private CustomServerResponse setResponseAfterAction(Project project, Character serverAction) {
        //setting Proper Response corresponding to Server Action
        switch (getServerAction(serverAction)) {
            case SAVE:
                successMessageCode = "project.dataSaved";
                errorMessageCode = "project.dataNotSaved";
                break;
            case UPDATE:
                successMessageCode = "project.dataUpdated";
                errorMessageCode = "project.dataNotUpdated";
                break;
            case DELETE:
                successMessageCode = "project.dataDeleted";
                errorMessageCode = "project.dataNotDeleted";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + serverAction);
        }
        //Checking if the Repository returned Full object With Response to check if the action is persisted
        if (!Objects.isNull(project)) {
            response.setStatus(DataValidationEnum.VALID_STATUS.status());
            response.setDto(convertFromEntityToDTO(project));
            response.setSavedIdentity(project.getPrjId());
            response.setMessage(messageSource.getMessage(successMessageCode, new String[]{project.getPrjName()}, null));
        } else {
            response.setStatus(DataValidationEnum.INVALID_STATUS.status());
            response.setMessage(messageSource.getMessage(errorMessageCode, null, null));
        }
        return response;
    }
    //endregion
}
