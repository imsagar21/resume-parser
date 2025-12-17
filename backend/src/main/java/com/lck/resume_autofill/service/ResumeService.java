package com.lck.resume_autofill.service;

import com.lck.resume_autofill.dto.AffindaResponseDTO;
import com.lck.resume_autofill.dto.EmployeeFormDTO;
import com.lck.resume_autofill.exeption.ResumeParseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final AffindaClient affindaClient;
    private final ResumeMappingService mappingService;

    public EmployeeFormDTO processResume(MultipartFile file) throws IOException {

        validateFile(file);

        AffindaResponseDTO affindaResponse =
                affindaClient.parseResume(file);

        EmployeeFormDTO formDTO =
                mappingService.mapToEmployeeForm(affindaResponse);

        // ✅ CORRECT USAGE
        formDTO.setMissingFields(
                mappingService.detectMissingFields(formDTO)
        );
        return formDTO;
    }

    private void validateFile(MultipartFile file) {
        if (file.isEmpty())
            throw new ResumeParseException("File is empty");

        List<String> allowedTypes = List.of(
                "application/pdf",
                "application/msword",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
        );

        if (!allowedTypes.contains(file.getContentType()))
            throw new ResumeParseException("Invalid file type");
    }
}
