package com.hassan.main.core.dao.tsk;

import com.hassan.main.core.model.tsk.TaskInformation;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * Data Access Object For Task Information
 * This Will Serve all the Database Functionality Regarding TaskInformation Entity
 *
 * @Author: Hassan Sakib Afrin
 * @Created: 01-07-2021 11.39 PM
 */
public interface ITaskInformationDao {
    /**
     * This Method return the Task Information Details by the Provided Id
     * If no Task Information is found then it will return Empty Object
     *
     * @param tskId Task Id ( Unique Id For the Task Information)
     * @return Optional<TaskInformation> object
     */
    public Optional<TaskInformation> getById(Long tskId);

    /**
     * This Method return all the Existing Task information as
     * List opf TaskInformation objects
     *
     * @return  List<TaskInformation>
     */
    public List<TaskInformation> getList();

    /**
     * This method gets the Task Information as Page according to the parameter passed
     *
     * @param page No of Page
     * @param size Total Size
     * @return
     */
    public Page<TaskInformation> getListAsPage(int page, int size);

    /**
     * This method serves for save and Update together for the Task Information in Database
     * If The primary Key is Set to Null or any non existing Id from Database,
     * it will save the Information
     *
     * If The Primary Key has an existing Id, this method will update
     * the Information with the Provided information.
     *
     * @param taskInformation The Task Information Object
     * @return The Saved/Updated Information as TaskInformation Object
     */
    public TaskInformation save(TaskInformation taskInformation);

    /**
     * This method serves for Deleting the Task Information with the Provided id
     *
     * @param tskId Task Id ( Unique Id For the Task Information)
     * @return The Deleted Information as TaskInformation Object
     */
    public TaskInformation delete(Long tskId);
}
