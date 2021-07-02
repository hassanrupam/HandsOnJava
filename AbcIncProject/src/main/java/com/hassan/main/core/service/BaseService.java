package com.hassan.main.core.service;

import com.hassan.main.core.enumurations.DataValidationEnum;
import com.hassan.main.core.utility.CustomServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BaseService {

    //region PROTECTED FIELDS
    protected String successMessageCode = "";
    protected String errorMessageCode = "";

    @Autowired
    protected MessageSource messageSource;

    protected CustomServerResponse response = new CustomServerResponse(DataValidationEnum.INVALID_STATUS.status(), "");

    public CustomServerResponse validateDTO(Object _DTO){
        CustomServerResponse beanValidationMessageCodes = new CustomServerResponse();
        StringBuilder violationMessageCodes =  new StringBuilder();

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator=  validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(_DTO);

        for(final ConstraintViolation<?> constraintViolation : constraintViolationSet){
            String message = constraintViolation.getMessageTemplate();
            if(!StringUtils.isEmpty(message)){
                violationMessageCodes.append("<li>").append(message).append("<li>");
            }
        }

        if(!StringUtils.isEmpty(violationMessageCodes.toString())){
            beanValidationMessageCodes.setStatus(DataValidationEnum.INVALID_STATUS.status());
            beanValidationMessageCodes.setMessage(violationMessageCodes.toString());
            return beanValidationMessageCodes;
        }
        return beanValidationMessageCodes;
    }
    //enregion
}
