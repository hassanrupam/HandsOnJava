package com.hassan.main.core.controller.prj;

import com.hassan.main.core.controller.BaseController;
import com.hassan.main.core.dto.prj.ProjectDTO;
import com.hassan.main.core.enumurations.DataValidationEnum;
import com.hassan.main.core.service.prj.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Timed;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping(value = "/projects")
@RestController
public class ProjectController extends BaseController {
    //region PRIVATE FIELDS
    @Autowired
    private IProjectService projectService;
    //endregion

    //region PUBLIC METHODS

    /**
     * This endpoint provides the Project Data according to the provided path variable -prjId
     * If no information is Found then it will return with Http Status as NOT_FOUND
     *
     * @param prjId Project Id (Unique Id for the Project)
     * @return HTTP response with data if Found
     */
    @GetMapping(value="getById/{prjId}")
    @Timed(millis = 2000)
    public ResponseEntity<ProjectDTO> getById(@PathVariable("prjId")UUID prjId){
        return Optional.ofNullable(projectService.getById(prjId))
                .map(projectDTO -> new ResponseEntity<>(projectDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * This endpoint provides all the Project Data as List
     * If no information is Found then it will return with Http Status as NOT_FOUND
     *
     * @return HTTP response with data if Found
     */
    @GetMapping(value="/getList")
    public ResponseEntity<List<ProjectDTO>> getList(){
        List<ProjectDTO> projectDTOList = projectService.getList();
        if(projectDTOList!=null && projectDTOList.size()>0){
            return ResponseEntity.ok().body(projectDTOList);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody ProjectDTO projectDTO, HttpServletRequest request) throws URISyntaxException {

        if(projectDTO.getPrjId()!=null){
            customServerResponse.setStatus(DataValidationEnum.INVALID_STATUS.status());
            customServerResponse.setMessage("You are trying to save duplicate content! Project Id must be null while Saving!");
            return ResponseEntity.badRequest().header("Failure","You are trying to save duplicate content! Project Id must be null while Saving!").body(customServerResponse);
        }
        customServerResponse =  projectService.save(projectDTO);
        if(customServerResponse.getStatus().equals(DataValidationEnum.INVALID_STATUS.status())){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).header("Failure", customServerResponse.getMessage()).body(customServerResponse);
        }
        return ResponseEntity.created(new URI("/getById/"+customServerResponse.getSavedIdentity()))
                .header("Success",customServerResponse.getMessage())
                .body(customServerResponse);
    }


    @PutMapping(value = "/update")
    public ResponseEntity<?> update(@RequestBody ProjectDTO projectDTO, HttpServletRequest request) throws URISyntaxException {

        if(projectDTO.getPrjId()==null){
            customServerResponse.setStatus(DataValidationEnum.INVALID_STATUS.status());
            customServerResponse.setMessage("You are trying to update an Unsaved content! Project Id must be provided while Updating!");
            return ResponseEntity.badRequest().header("Failure","You are trying to update Unsaved content! Project Id must be provided while Updating!").body(customServerResponse);
        }
        customServerResponse =  projectService.update(projectDTO);
        if(customServerResponse.getStatus().equals(DataValidationEnum.INVALID_STATUS.status())){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).header("Failure", customServerResponse.getMessage()).body(customServerResponse);
        }
        return ResponseEntity.ok().header("Success",customServerResponse.getMessage())
                .body(customServerResponse);
    }

    @DeleteMapping(value="delete/{prjId}")
    public ResponseEntity<?> delete(@PathVariable("prjId")UUID prjId){
        customServerResponse =  projectService.delete(prjId);
        if(customServerResponse.getStatus().equals(DataValidationEnum.INVALID_STATUS.status())){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(customServerResponse);
        }
        return ResponseEntity.ok().body(customServerResponse);
    }

    //endregion

}
