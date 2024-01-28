package com.yupi.springbootinit.model.dto;

import lombok.Data;

@Data
public class IssueSolutionDTO {
    private String rootCause;
    private String issueDescription;
    private String solutionDescription;

}