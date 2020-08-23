package com.rest.bshape.bodytype;

import com.rest.bshape.user.UserDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class BodyTypeDTO {

    private Long id;

    private String typeOfBody;

    private List<UserDTO> users;

//    private List<UserHistoryDTO> userHistories;
}
