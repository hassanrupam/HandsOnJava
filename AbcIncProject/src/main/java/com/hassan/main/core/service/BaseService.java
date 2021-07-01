package com.hassan.main.core.service;

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
    @Autowired
    protected MessageSource messageSource;

    protected HashMap<String, Object> response = new HashMap<>();

    public Map<Boolean, String> validateDTO(Object _DTO){
        Map<Boolean,String> beanValidationMessageCodes = new HashMap<>();
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
            beanValidationMessageCodes.put(false,violationMessageCodes.toString());
            return beanValidationMessageCodes;
        }
        return beanValidationMessageCodes;
    }
    //enregion
}
