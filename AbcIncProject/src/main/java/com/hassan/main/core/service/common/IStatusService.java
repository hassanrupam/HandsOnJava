package com.hassan.main.core.service.common;

import com.hassan.main.core.dto.Common.CommonDropDownDTO;

import java.util.List;

public interface IStatusService {
    /**
     * This method Creates the List Of Status from the Enum
     *
     * @return List Of CommonDropDownDTO containing List of Status
     */
    public List<CommonDropDownDTO> getStatusDropdown();
}
