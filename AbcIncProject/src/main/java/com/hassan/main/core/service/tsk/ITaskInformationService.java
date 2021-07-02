package com.hassan.main.core.service.tsk;

import com.hassan.main.core.dto.tsk.TaskInformationDTO;
import com.hassan.main.core.model.tsk.TaskInformation;
import com.hassan.main.core.utility.CustomServerResponse;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;

/**
 * Service Layer Interface for the TaskInformation
 * This Will Serve all the Business and Validations for TaskInformation
 *
 * @Author: Hassan Sakib Afrin
 * @Created: 01-07-2021 02.23 PM
 */
public interface ITaskInformationService {
    /**
     * This method serves as the Data Transfer with Validation from
     * the Data Layer to The Controller Layer
     *
     * @param tskId Task Information Id (Unique Id For the TaskInformation)
     * @return Information Of TaskInformation as TaskInformationDTO Object
     */
    public TaskInformationDTO getById(Long tskId);

    /**
     * This method serves to get All the Information of TaskInformation as List
     *
     * @return List Of TaskInformationDTO
     */
    public List<TaskInformationDTO> getList();

    /**
     * This method serves to get Information for TaskInformation as Page with the parameters passed
     *
     * @param page No of Page
     * @param size Total Size
     * @return
     */
    public Page<TaskInformation> getListAsPage(int page, int size);

    /**
     * This method serves to Validate the Provided Data and Save the TaskInformation Object.
     *
     * @param taskInformationDTO TaskInformation Information as an Object
     * @return CustomServerResponse containing the response Message
     */
    public CustomServerResponse save(TaskInformationDTO taskInformationDTO);

    /**
     * This method serves to Validate the Provided Data and Update the TaskInformation Object.
     *
     * @param taskInformationDTO TaskInformation Information as an Object
     * @return CustomServerResponse containing the response Message
     */
    public CustomServerResponse update(TaskInformationDTO taskInformationDTO);

    /**
     * This method serves to Validate the Provided Data and delete the TaskInformation Information.
     *
     * @param tskId TaskInformation Id (Unique Id For the TaskInformation)
     * @return CustomServerResponse containing the response Message
     */
    public CustomServerResponse delete(Long tskId);
}
