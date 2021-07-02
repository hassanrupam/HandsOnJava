package com.hassan.main.core.service.common;

import com.hassan.main.core.dto.Common.CommonDropDownDTO;
import com.hassan.main.core.enumurations.StatusEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusService implements IStatusService {

    //region PUBLIC METHODS

    /**
     * This method Creates the List Of Status from the Enum
     *
     * @return List Of CommonDropDownDTO containing List of Status
     */
    @Override
    public List<CommonDropDownDTO> getStatusDropdown() {
        return Arrays.stream(StatusEnum.values()).map(statusEnum -> new CommonDropDownDTO(statusEnum.getStatus(), statusEnum.getStatus()))
                .collect(Collectors.toList());
    }
    //endregion
}
