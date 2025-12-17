package com.lck.resume_autofill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;


@SpringBootApplication(

				exclude = { DataSourceAutoConfiguration.class }


)
public class ResumeAutofillApplication {
	public static void main(String[] args) {
		SpringApplication.run(ResumeAutofillApplication.class, args);
	}
}
