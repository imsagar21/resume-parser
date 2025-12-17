package com.lck.resume_autofill.controller;

import com.lck.resume_autofill.dto.EmployeeFormDTO;
import com.lck.resume_autofill.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
public class ResumeController {


    private final ResumeService resumeService;

    @PostMapping("/upload")
    public ResponseEntity<EmployeeFormDTO> uploadResume(
            @RequestParam("file") MultipartFile file) throws IOException {

        EmployeeFormDTO result = resumeService.processResume(file);
        return ResponseEntity.ok(result);
    }
}