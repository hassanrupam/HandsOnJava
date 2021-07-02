package com.hassan.main.core.service.tsk;

import com.hassan.main.core.dao.tsk.ITaskInformationDao;
import com.hassan.main.core.dto.tsk.TaskInformationDTO;
import com.hassan.main.core.enumurations.DataValidationEnum;
import com.hassan.main.core.model.tsk.TaskInformation;
import com.hassan.main.core.service.BaseService;
import com.hassan.main.core.utility.CustomServerResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.hassan.main.core.enumurations.ServerActionEnum.*;

/**
 * Service Layer Interface for the TaskInformation
 * This Will Serve all the Business and Validations for TaskInformation
 *
 * @Author: Hassan Sakib Afrin
 * @Created: 01-07-2021 02.30 PM
 */
@Service
public class TaskInformationService extends BaseService implements ITaskInformationService {

    //region PRIVATE FIELDS
    private TaskInformation taskInformation = new TaskInformation();

    @Autowired
    private ITaskInformationDao taskInformationDao;
    //endregion

    //region PUBLIC METHODS

    /**
     * This method serves as the Data Transfer with Validation from
     * the Data Layer to The Controller Layer
     *
     * @param tskId Task Information Id (Unique Id For the TaskInformation)
     * @return Information Of TaskInformation as TaskInformationDTO Object
     */
    @Override
    public TaskInformationDTO getById(Long tskId) {
        return taskInformationDao.getById(tskId).map(this::convertFromEntityToDTO).orElse(new TaskInformationDTO());
    }

    /**
     * This method serves to get All the Information of TaskInformation as List
     *
     * @return List Of TaskInformationDTO
     */
    @Override
    public List<TaskInformationDTO> getList() {
        return taskInformationDao.getList().stream().map(this::convertFromEntityToDTO).collect(Collectors.toList());
    }

    /**
     * This method serves to get Information for TaskInformation as Page with the parameters passed
     *
     * @param page No of Page
     * @param size Total Size
     * @return Page Object Containing List Of TaskInformationDTO
     */
    @Override
    public Page<TaskInformation> getListAsPage(int page, int size) {
        return taskInformationDao.getListAsPage(page, size);
    }


    /**
     * This method serves to Validate the Provided Data and Save the TaskInformation Object.
     *
     * @param taskInformationDTO TaskInformation Information as an Object
     * @return CustomServerResponse containing the response Message
     */
    @Override
    public CustomServerResponse save(TaskInformationDTO taskInformationDTO) {
        response = validateDTO(taskInformationDTO);
        try {
            taskInformation = convertFromDTOToEntity(taskInformationDTO);
        } catch (Exception e) {
            response.setStatus(DataValidationEnum.INVALID_STATUS.status());
            response.setMessage(messageSource.getMessage("common.dataConversionError", null, null));
            return response;
        }
        try {
            taskInformation = taskInformationDao.save(taskInformation);
            response = setResponseAfterAction(taskInformation, SAVE.getAction());
        } catch (Exception e) {
            response.setStatus(DataValidationEnum.INVALID_STATUS.status());
            response.setMessage(e.getMessage());
            return response;
        }
        return response;
    }

    /**
     * This method serves to Validate the Provided Data and Update the TaskInformation Object.
     *
     * @param taskInformationDTO TaskInformation Information as an Object
     * @return CustomServerResponse containing the response Message
     */
    @Override
    public CustomServerResponse update(TaskInformationDTO taskInformationDTO) {
        response = validateDTO(taskInformationDTO);
        if (response.getStatus().equals(DataValidationEnum.INVALID_STATUS.status())) {
            return response;
        }
        if (!taskInformationDao.getById(taskInformationDTO.getTskId()).isPresent()) {
            response.setStatus(DataValidationEnum.INVALID_STATUS.status());
            response.setMessage(messageSource.getMessage("taskInformation.notExist", null, null));
            return response;
        }

        try {
            taskInformation = convertFromDTOToEntity(taskInformationDTO);
        } catch (Exception e) {
            response.setStatus(DataValidationEnum.INVALID_STATUS.status());
            response.setMessage(messageSource.getMessage("common.dataConversionError", null, null));
            return response;
        }
        try {
            taskInformation = taskInformationDao.save(taskInformation);
            response = setResponseAfterAction(taskInformation, UPDATE.getAction());
        } catch (Exception e) {
            response.setStatus(DataValidationEnum.INVALID_STATUS.status());
            response.setMessage(e.getMessage());
            return response;
        }
        return response;
    }


    /**
     * This method serves to Validate the Provided Data and delete the TaskInformation Information.
     *
     * @param tskId TaskInformation Id (Unique Id For the TaskInformation)
     * @return CustomServerResponse containing the response Message
     */
    @Override
    public CustomServerResponse delete(Long tskId) {
        Optional<TaskInformation> optionalTaskInformation =  taskInformationDao.getById(tskId);
        if (!optionalTaskInformation.isPresent()) {
            response.setStatus(DataValidationEnum.INVALID_STATUS.status());
            response.setMessage(messageSource.getMessage("taskInformation.notExist", null, null));
            return response;
        }
        try {
            taskInformation = taskInformationDao.delete(optionalTaskInformation.get());
            response = setResponseAfterAction(taskInformation, DELETE.getAction());
        } catch (Exception e) {
            response.setStatus(DataValidationEnum.INVALID_STATUS.status());
            response.setMessage(e.getMessage());
            return response;
        }
        return response;
    }

    @Override
    public List<TaskInformationDTO> getListByProject(UUID prjId) {
        return taskInformationDao.getListByProject(prjId).stream().map(this::convertFromEntityToDTO).collect(Collectors.toList());
    }
    //endregion

    //region PRIVATE METHODS

    /**
     * This method copies all values from the Entity to the Data Transfer Object
     *
     * @param taskInformation Entity Object Of TaskInformation
     * @return TaskInformationDTO - Data Transfer Object Of TaskInformation
     */
    private TaskInformationDTO convertFromEntityToDTO(TaskInformation taskInformation) {
        TaskInformationDTO taskInformationDTO = new TaskInformationDTO();
        BeanUtils.copyProperties(taskInformation, taskInformationDTO);
        return taskInformationDTO;
    }

    /**
     * This method copies all values from the Data Transfer Object to the Entity Object
     *
     * @param taskInformationDTO Data Transfer Object Of TaskInformation
     * @return Entity Object Of TaskInformation
     */
    private TaskInformation convertFromDTOToEntity(TaskInformationDTO taskInformationDTO) {
        TaskInformation taskInformation = new TaskInformation();
        BeanUtils.copyProperties(taskInformationDTO, taskInformation);
        return taskInformation;
    }

    /**
     * This method sets the proper response according to the Server Action
     * and by the Fact, if the Repository is able to Persist the requested action or not
     *
     * @param taskInformation the entity object to check if the repository has returned with valid response
     * @param serverAction    Character Stating the Flags for  either Save, Update Or Delete Action is Occurring
     * @return CustomServerResponse with the proper response
     */
    private CustomServerResponse setResponseAfterAction(TaskInformation taskInformation, Character serverAction) {
        //setting Proper Response corresponding to Server Action
        switch (getServerAction(serverAction)) {
            case SAVE:
                successMessageCode = "taskInformation.dataSaved";
                errorMessageCode = "taskInformation.dataNotSaved";
                break;
            case UPDATE:
                successMessageCode = "taskInformation.dataUpdated";
                errorMessageCode = "taskInformation.dataNotUpdated";
                break;
            case DELETE:
                successMessageCode = "taskInformation.dataDeleted";
                errorMessageCode = "taskInformation.dataNotDeleted";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + serverAction);
        }
        //Checking if the Repository returned Full object With Response to check if the action is persisted
        if (!Objects.isNull(taskInformation)) {
            response.setStatus(DataValidationEnum.VALID_STATUS.status());
            response.setDto(convertFromEntityToDTO(taskInformation));
            response.setSavedIdentity(taskInformation.getTskId());
            response.setMessage(messageSource.getMessage(successMessageCode, new String[]{taskInformation.getTskName(),taskInformation.getTskId().toString()}, null));
        } else {
            response.setStatus(DataValidationEnum.INVALID_STATUS.status());
            response.setMessage(messageSource.getMessage(errorMessageCode, null, null));
        }
        return response;
    }
    //endregion
}
