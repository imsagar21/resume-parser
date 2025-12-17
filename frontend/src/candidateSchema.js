import { z } from "zod";

export const candidateSchema = z.object({
  firstName: z.string().min(1, "First name is required"),

  lastName: z.string().min(1, "Last name is required"),

  gender: z.enum(["Male", "Female", "Other"], {
    errorMap: () => ({ message: "Gender is required" }),
  }),

  dob: z.string().min(1, "Date of birth is required"),

  email: z.string().min(1, "Email is required").email("Invalid email address"),

  expectedCTC: z.string().min(1, "Expected CTC is required"),

  skills: z.string().min(1, "At least one skill is required"),
});
