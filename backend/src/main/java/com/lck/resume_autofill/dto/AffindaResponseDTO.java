package com.lck.resume_autofill.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AffindaResponseDTO {

    private Name name;

    @JsonProperty("emails")
    private List<String> emailAddress;

    @JsonProperty("phoneNumbers")
    private List<String> mobileNumbers;

    private List<Skill> skills;

    @JsonProperty("gender")
    private List<String> gender;

    @JsonProperty("expectedCtc")
    private List<String> expectedCtc;

    @JsonProperty("dateOfBirth")
    private List<String> dateOfBirth;

    @Data
    public static class Name {
        private String raw;
        private String first;
        private String last;
    }

    @Data
    public static class Skill {
        private String name;
    }
}
