import React, { useState } from "react";

function ResumeUpload({ onParsed }) {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleUpload = async (file) => {
    if (!file) return;

    setLoading(true);
    setError("");

    try {
      const formData = new FormData();
      formData.append("resume", file);

      const response = await fetch("http://localhost:5000/api/resume/parse", {
        method: "POST",
        body: formData,
      });

      if (!response.ok) {
        throw new Error("Resume parsing failed");
      }

      const parsedData = await response.json();
      onParsed(parsedData);
    } catch (err) {
      setError("Unable to parse resume. Please try another file.", err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-md mx-auto mt-8">
      <label
        htmlFor="resume-upload"
        className="flex flex-col items-center justify-center
                   border-2 border-dashed border-gray-300
                   rounded-lg p-6 cursor-pointer
                   hover:border-blue-500 hover:bg-blue-50
                   transition"
      >
        <span className="text-gray-600 font-medium">
          {loading ? "Parsing resume..." : "Click to upload resume"}
        </span>

        <span className="text-sm text-gray-400 mt-1">
          PDF, DOC, DOCX (Max 5MB)
        </span>

        <input
          id="resume-upload"
          type="file"
          accept=".pdf,.doc,.docx"
          className="hidden"
          onChange={(e) => handleUpload(e.target.files[0])}
          disabled={loading}
        />
      </label>

      {loading && (
        <p className="text-blue-600 text-sm mt-3 text-center">
          Extracting information from resume…
        </p>
      )}

      {error && (
        <p className="text-red-500 text-sm mt-3 text-center">{error}</p>
      )}
    </div>
  );
}

export default ResumeUpload;
