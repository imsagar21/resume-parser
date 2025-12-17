package com.lck.resume_autofill.service;

import com.lck.resume_autofill.dto.AffindaResponseDTO;
import com.lck.resume_autofill.dto.EmployeeFormDTO;
import com.lck.resume_autofill.exeption.ResumeParseException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResumeMappingService {

    public EmployeeFormDTO mapToEmployeeForm(AffindaResponseDTO affinda) {

        if (affinda == null) {
            throw new ResumeParseException("Affinda response is null");
        }

        EmployeeFormDTO dto = new EmployeeFormDTO();

        if (affinda.getName() != null) {
            dto.setFullName(affinda.getName().getRaw());
        }

        dto.setEmailAddress(first(affinda.getEmailAddress()));
        dto.setMobileNumber(first(affinda.getMobileNumbers()));
        dto.setExpectedCtc(first(affinda.getExpectedCtc()));   // ✅ FIXED
        dto.setDateOfBirth(first(affinda.getDateOfBirth()));
        dto.setGender(first(affinda.getGender()));
        dto.setSkills(extractSkills(affinda));


        return dto;
    }

    private String first(List<String> list) {
        return (list == null || list.isEmpty()) ? null : list.get(0);
    }

    private String extractSkills(AffindaResponseDTO affinda) {
        if (affinda.getSkills() == null) return null;

        return affinda.getSkills()
                .stream()
                .map(AffindaResponseDTO.Skill::getName)
                .collect(Collectors.joining(", "));
    }

    public List<String> detectMissingFields(EmployeeFormDTO dto) {

        List<String> missing = new ArrayList<>();

        if (dto.getFullName() == null || dto.getFullName().isBlank())
            missing.add("fullName");

        if (dto.getEmailAddress() == null || dto.getEmailAddress().isBlank())
            missing.add("emailAddress");

        if (dto.getMobileNumber() == null || dto.getMobileNumber().isBlank())
            missing.add("mobileNumber");

        if (dto.getSkills() == null || dto.getSkills().isBlank())
            missing.add("skills");

        if (dto.getExpectedCtc() == null || dto.getExpectedCtc().isBlank())
            missing.add("expectedCTC");

        if (dto.getDateOfBirth() == null || dto.getDateOfBirth().isBlank())
            missing.add("dateOfBirth");

        if (dto.getGender() == null || dto.getGender().isBlank())
            missing.add("gender");

        return missing;
    }
}
