package com.hassan.main.core.controller.prj;

import com.hassan.main.core.dto.prj.ProjectDTO;
import com.hassan.main.core.service.prj.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Timed;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping(value = "/projects")
@Controller
public class ProjectController {
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
    @GetMapping("getById/{prjId}")
    @Timed(millis = 2000)
    @ResponseBody
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
    @GetMapping("/getList")
    @ResponseBody
    public ResponseEntity<List<ProjectDTO>> getList(){
        List<ProjectDTO> projectDTOList = projectService.getList();
        if(projectDTOList!=null && projectDTOList.size()>0){
            return ResponseEntity.ok().body(projectDTOList);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //endregion

}
