package com.lck.resume_autofill.controller;

import com.lck.resume_autofill.dto.EmployeeFormDTO;
import com.lck.resume_autofill.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@CrossOrigin(origins = "http://localhost:5173")
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