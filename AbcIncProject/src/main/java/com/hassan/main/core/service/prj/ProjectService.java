package com.hassan.main.core.service.prj;

import com.hassan.main.core.dao.prj.IProjectDao;
import com.hassan.main.core.dto.prj.ProjectDTO;
import com.hassan.main.core.enumurations.DataValidationEnum;
import com.hassan.main.core.model.prj.Project;
import com.hassan.main.core.service.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service Layer Implementation for the Project
 * This Will Serve all the Business and Validations for Project
 *
 * @Author: Hassan Sakib Afrin
 * @Created: 02-07-2021 00.03 PM
 */
@Service
public class ProjectService extends BaseService implements IProjectService{

    //region PRIVATE FIELDS
    private Project project =  new Project();

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
        ProjectDTO projectDTO = new ProjectDTO();
        Optional<Project> projectOptional =  projectDao.getById(prjId);
        if(projectOptional.isPresent()){
            projectDTO =  convertFromEntityToDTO(projectOptional.get());
        }
        return projectDTO;
    }

    /**
     * This method serves to get All the Information of Project as List
     *
     * @return List Of ProjectDTO
     */
    @Override
    public List<ProjectDTO> getList() {
        return convertFromEntityToDTOList(projectDao.getList());
    }

    /**
     * This method serves to get Information for Project as Page with the parameters passed
     *
     * @param page No of Page
     * @param size Total Size
     * @return Page<ProjectDTO> - Page Object Containing List Of Project
     */
    @Override
    public Page<ProjectDTO> getListAsPage(int page, int size) {
        return convertFromEntityToDTOPage(projectDao.getListAsPage(page,size));
    }

    /**
     * This method serves to Validate the Provided Data and Save the Project Information.
     *
     * @param projectDTO Project Information as an Object
     * @return HashMap<String,Object> containing the response Message
     */
    @Override
    public HashMap<String, Object> save(ProjectDTO projectDTO){
        for(Map.Entry<Boolean,String> entry: validateDTO(projectDTO).entrySet()){
            response.put("status", DataValidationEnum.INVALID_STATUS.status());
            response.put("message", entry.getValue());
            return response;
        }
        try{
            project =  convertFromDTOToEntity(projectDTO);
        } catch (Exception e) {
            response.put("status", DataValidationEnum.INVALID_STATUS.status());
            response.put("message", messageSource.getMessage("common.dataConversionError",null,null));
            return response;
        }
        try{
            project = projectDao.save(project);
            if(!Objects.isNull(project)){
                response.put("status", DataValidationEnum.VALID_STATUS.status());
                response.put("message", messageSource.getMessage("project.dataSaved", new String[]{project.getPrjName(),project.getPrjId().toString()},null));
                response.put("dto", project);
                response.put("saved_identity", project.getPrjId());
            }else{
                response.put("status", DataValidationEnum.INVALID_STATUS.status());
                response.put("message", messageSource.getMessage("project.dataNotSaved",null,null));
            }
        } catch (Exception e) {
            response.put("status", DataValidationEnum.INVALID_STATUS.status());
            response.put("message", messageSource.getMessage("project.dataNotSaved",null,null));
            return response;
        }
        return response;
    }

    @Override
    public HashMap<String, Object> update(ProjectDTO projectDTO) {
        for(Map.Entry<Boolean,String> entry: validateDTO(projectDTO).entrySet()){
            response.put("status", DataValidationEnum.INVALID_STATUS.status());
            response.put("message", entry.getValue());
            return response;
        }
        if(!projectDao.getById(projectDTO.getPrjId()).isPresent()){
            response.put("status", DataValidationEnum.INVALID_STATUS.status());
            response.put("message", messageSource.getMessage("project.notExist",null,null));
            return response;
        }

        try{
            project =  convertFromDTOToEntity(projectDTO);
        } catch (Exception e) {
            response.put("status", DataValidationEnum.INVALID_STATUS.status());
            response.put("message", messageSource.getMessage("common.dataConversionError",null,null));
            return response;
        }
        try{
            project = projectDao.save(project);
            if(!Objects.isNull(project)){
                response.put("status", DataValidationEnum.VALID_STATUS.status());
                response.put("message", messageSource.getMessage("project.dataUpdated", new String[]{project.getPrjName()},null));
                response.put("dto", project);
                response.put("saved_identity", project.getPrjId());
            }else{
                response.put("status", DataValidationEnum.INVALID_STATUS.status());
                response.put("message", messageSource.getMessage("project.dataNotUpdated",null,null));
            }
        } catch (Exception e) {
            response.put("status", DataValidationEnum.INVALID_STATUS.status());
            response.put("message", messageSource.getMessage("project.dataUpdated",null,null));
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
    private ProjectDTO convertFromEntityToDTO(Project project){
        ProjectDTO projectDTO =  new ProjectDTO();
        BeanUtils.copyProperties(project,projectDTO);
        return projectDTO;
    }

    /**
     * This method copies all values from the Data Transfer Object to the Entity Object
     *
     * @param projectDTO Data Transfer Object Of Project
     * @return Entity Object Of Project
     */
    private Project convertFromDTOToEntity(ProjectDTO projectDTO){
        Project project = new Project();
        BeanUtils.copyProperties(projectDTO,project);
        return project;
    }

    /**
     * This method copies all values from the List of Entity to the Data Transfer Object List
     *
     * @param projectList List of Entity Object Of Project
     * @return List<ProjectDTO>  projectDTOList - List of Data Transfer Object Of Project
     */
    private List<ProjectDTO> convertFromEntityToDTOList(List<Project> projectList){
        List<ProjectDTO> projectDTOList =  new ArrayList<>();
        BeanUtils.copyProperties(projectList,projectDTOList);
        return projectDTOList;
    }

    /**
     * This method copies all values from the List of Entity to the Data Transfer Object List
     *
     * @param projectPage Page containing List of Entity Object Of Project
     * @return  Page<ProjectDTO> projectDTOPage -Page containing List of Data Transfer Object Of Project
     */
    private Page<ProjectDTO> convertFromEntityToDTOPage(Page<Project> projectPage){
        Page<ProjectDTO> projectDTOPage = null;
        BeanUtils.copyProperties(projectPage,projectDTOPage);
        return projectDTOPage;
    }
    //endregion
}
