package com.lck.resume_autofill.dto;

import lombok.Data;
import java.util.List;

@Data
public class EmployeeFormDTO {

        private String fullName;
        private String emailAddress;
        private String mobileNumber;
        private String skills;
        private String expectedCtc;
        private String dateOfBirth;
        private String gender;
        private List<String> missingFields;
    }

