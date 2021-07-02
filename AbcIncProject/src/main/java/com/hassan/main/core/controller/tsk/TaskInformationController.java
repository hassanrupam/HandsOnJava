package com.hassan.main.core.controller.tsk;

import com.hassan.main.core.controller.BaseController;
import com.hassan.main.core.dto.tsk.TaskInformationDTO;
import com.hassan.main.core.enumurations.DataValidationEnum;
import com.hassan.main.core.service.tsk.ITaskInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Timed;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping(value = "/tasks")
@RestController
public class TaskInformationController extends BaseController {
    
    //region PRIVATE FIELDS
    @Autowired
    private ITaskInformationService taskInformationService;
    //endregion
    
    //region PUBLIC METHODS

    /**
     *
     * @param tskId
     * @return
     */
    @GetMapping(value = "/getById/{tskId}")
    @Timed(millis = 2000)
    public ResponseEntity<TaskInformationDTO> getById(@PathVariable("tskId") Long tskId){
        return Optional.ofNullable(taskInformationService.getById(tskId))
                .map(taskInformationDTO -> new ResponseEntity<>(taskInformationDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    /**
     * This endpoint provides all the Project Data as List
     * If no information is Found then it will return with Http Status as NOT_FOUND
     *
     * @return HTTP response with data if Found
     */
    @GetMapping(value="/getList")
    public ResponseEntity<List<TaskInformationDTO>> getList(){
        List<TaskInformationDTO> TaskInformationDTOList = taskInformationService.getList();
        if(TaskInformationDTOList!=null && TaskInformationDTOList.size()>0){
            return ResponseEntity.ok().body(TaskInformationDTOList);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody TaskInformationDTO taskInformationDTO, HttpServletRequest request) throws URISyntaxException {

        if(taskInformationDTO.getTskId()!=null){
            customServerResponse.setStatus(DataValidationEnum.INVALID_STATUS.status());
            customServerResponse.setMessage("You are trying to save duplicate content! Project Id must be null while Saving!");
            return ResponseEntity.badRequest().header("Failure","You are trying to save duplicate content! Project Id must be null while Saving!").body(customServerResponse);
        }
        customServerResponse =  taskInformationService.save(taskInformationDTO);
        if(customServerResponse.getStatus().equals(DataValidationEnum.INVALID_STATUS.status())){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).header("Failure", customServerResponse.getMessage()).body(customServerResponse);
        }
        return ResponseEntity.created(new URI("/getById/"+customServerResponse.getSavedIdentity()))
                .header("Success",customServerResponse.getMessage())
                .body(customServerResponse);
    }


    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody TaskInformationDTO taskInformationDTO, HttpServletRequest request) throws URISyntaxException {

        if(taskInformationDTO.getTskId()==null){
            customServerResponse.setStatus(DataValidationEnum.INVALID_STATUS.status());
            customServerResponse.setMessage("You are trying to update an Unsaved content! Project Id must be provided while Updating!");
            return ResponseEntity.badRequest().header("Failure","You are trying to update Unsaved content! Project Id must be provided while Updating!").body(customServerResponse);
        }
        customServerResponse =  taskInformationService.update(taskInformationDTO);
        if(customServerResponse.getStatus().equals(DataValidationEnum.INVALID_STATUS.status())){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).header("Failure", customServerResponse.getMessage()).body(customServerResponse);
        }
        return ResponseEntity.ok().header("Success",customServerResponse.getMessage())
                .body(customServerResponse);
    }

    @DeleteMapping(value="delete/{tskId}")
    public ResponseEntity<?> delete(@PathVariable("tskId") Long tskId){
        customServerResponse =  taskInformationService.delete(tskId);
        if(customServerResponse.getStatus().equals(DataValidationEnum.INVALID_STATUS.status())){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(customServerResponse);
        }
        return ResponseEntity.ok().body(customServerResponse);
    }

    //endregion


}
