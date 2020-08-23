package com.rest.bshape.target;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class TargetDTO {

    private Long id;

    private String futureTarget;
}
