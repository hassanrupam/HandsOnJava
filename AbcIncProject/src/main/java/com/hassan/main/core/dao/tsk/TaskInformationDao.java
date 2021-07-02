package com.hassan.main.core.dao.tsk;

import com.hassan.main.core.model.tsk.TaskInformation;
import com.hassan.main.core.repository.tsk.ITaskInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Data Access Object For Task Information Implementation
 * This Will Serve all the Database Functionality Regarding TaskInformation Entity
 *
 * @Author: Hassan Sakib Afrin
 * @Created: 01-07-2021 11.41 PM
 */
@Repository
public class TaskInformationDao implements ITaskInformationDao{

    //region PRIVATE FIELDS
    @Autowired
    private ITaskInformationRepository taskInformationRepository;
    //endregion

    //region PUBLIC METHODS
    /**
     * This Method return the Task Information Details by the Provided Id
     * If no Task Information is found then it will return Empty Object
     *
     * @param tskId Task Id ( Unique Id For the Task Information)
     * @return Optional<TaskInformation> object
     */
    @Override
    public Optional<TaskInformation> getById(Long tskId) {
        return taskInformationRepository.findByTskId(tskId);
    }

    /**
     * This Method return all the Existing Task information as
     * List opf TaskInformation objects
     *
     * @return  List<TaskInformation>
     */
    @Override
    public List<TaskInformation> getList() {
        return taskInformationRepository.findAll();
    }

    /**
     * This method gets the Task Information as Page according to the parameter passed
     *
     * @param page No of Page
     * @param size Total Size
     * @return
     */
    @Override
    public Page<TaskInformation> getListAsPage(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return taskInformationRepository.findAll(pageable);
    }

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
    @Override
    public TaskInformation save(TaskInformation taskInformation) {
        return taskInformationRepository.saveAndFlush(taskInformation);
    }

    /**
     * This method serves for Deleting the Task Information with the Provided id
     *
     * @param taskInformation Task Information Entity for Delete
     * @return The Deleted Information as TaskInformation Object
     */
    @Override
    @Transactional
    public TaskInformation delete(TaskInformation taskInformation) {
        try{
            TaskInformation deletedTaskInformation = taskInformation;
            taskInformationRepository.deleteByTskId(taskInformation.getTskId());
            return deletedTaskInformation;
        }catch (Exception e){
            return  null;
        }
    }

    /**
     * This method return the List Of Task Information based on passed Project ID
     *
     * @param prjId
     * @return List Of Task Information List Base On Project
     */
    @Override
    public List<TaskInformation> getListByProject(UUID prjId) {
        return taskInformationRepository.findByTskProject_PrjId(prjId);
    }
    //endregion
}
