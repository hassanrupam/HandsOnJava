package com.hassan.main.core.controller.common;

import com.hassan.main.core.dto.Common.CommonDropDownDTO;
import com.hassan.main.core.dto.prj.ProjectDTO;
import com.hassan.main.core.service.common.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping(value = "/common")
@Controller
public class CommonController {
    //region PRIVATE FIELDS
    @Autowired
    private IStatusService statusService;
    //endregion

    /**
     * This endpoint provides all the Status Data as List
     * If no information is Found then it will return with Http Status as NOT_FOUND
     *
     * @return HTTP response with data if Found
     */
    @GetMapping("/getStatusList")
    @ResponseBody
    public ResponseEntity<List<CommonDropDownDTO>> getList(){
        List<CommonDropDownDTO> listOfStatus = statusService.getStatusDropdown();
        if(listOfStatus!=null && listOfStatus.size()>0){
            return ResponseEntity.ok().body(listOfStatus);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
