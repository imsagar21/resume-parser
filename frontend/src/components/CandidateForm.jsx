import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { mapParsedData } from "../utils/mapParsedData";
import { candidateSchema } from "../candidateSchema";

function CandidateForm({ parsedData }) {
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors, isSubmitting },
  } = useForm({
    resolver: zodResolver(candidateSchema),
    defaultValues: mapParsedData(parsedData),
    mode: "onBlur",
  });

  const onSubmit = async (data) => {
    await fetch("http://localhost:5000/api/resume/save", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        ...data,
        skills: data.skills.split(",").map((s) => s.trim()),
      }),
    });

    alert("Candidate saved");
  };

  const refillFromResume = () => {
    reset(mapParsedData(parsedData));
  };

  return (
    <div className="min-h-screen bg-gray-50 flex justify-center items-start">
      <form
        onSubmit={handleSubmit(onSubmit)}
        className="w-full max-w-2xl bg-white rounded-xl shadow-md space-y-6"
      >
        <h3 className="text-xl font-semibold text-gray-800">
          Candidate Details
        </h3>

        {/* First & Last Name */}
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
          <div>
            <input
              {...register("firstName")}
              placeholder="First Name"
              className="w-full border rounded-lg px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
            {errors.firstName && (
              <p className="text-red-500 text-sm mt-1">
                {errors.firstName.message}
              </p>
            )}
          </div>

          <div>
            <input
              {...register("lastName")}
              placeholder="Last Name"
              className="w-full border rounded-lg px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
            {errors.lastName && (
              <p className="text-red-500 text-sm mt-1">
                {errors.lastName.message}
              </p>
            )}
          </div>
        </div>

        {/* Gender & DOB */}
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
          <select
            {...register("gender")}
            className="w-full border rounded-lg px-3 py-2 bg-white focus:outline-none focus:ring-2 focus:ring-blue-500"
          >
            <option value="">Select Gender</option>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
            <option value="Other">Other</option>
          </select>

          <input
            type="date"
            {...register("dob")}
            className="w-full border rounded-lg px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
        </div>

        {/* Email */}
        <div>
          <input
            {...register("email")}
            placeholder="Email Address"
            className="w-full border rounded-lg px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
          {errors.email && (
            <p className="text-red-500 text-sm mt-1">{errors.email.message}</p>
          )}
        </div>

        {/* Expected CTC */}
        <div>
          <input
            {...register("expectedCTC")}
            placeholder="Expected CTC"
            className="w-full border rounded-lg px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
          {errors.expectedCTC && (
            <p className="text-red-500 text-sm mt-1">
              {errors.expectedCTC.message}
            </p>
          )}
        </div>

        {/* Skills */}
        <div>
          <input
            {...register("skills")}
            placeholder="Skills (comma separated)"
            className="w-full border rounded-lg px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
          {errors.skills && (
            <p className="text-red-500 text-sm mt-1">{errors.skills.message}</p>
          )}
        </div>

        {/* Actions */}
        <div className="flex flex-wrap gap-3 pt-4">
          <button
            type="submit"
            disabled={isSubmitting}
            className="bg-blue-600 text-white px-6 py-2 rounded-lg hover:bg-blue-700 transition disabled:opacity-50"
          >
            {isSubmitting ? "Saving..." : "Save"}
          </button>

          <button
            type="button"
            onClick={refillFromResume}
            className="border border-gray-300 px-6 py-2 rounded-lg hover:bg-gray-100 transition"
          >
            Re-fill from Resume
          </button>
        </div>
      </form>
    </div>
  );
}

export default CandidateForm;
