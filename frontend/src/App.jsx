import { useState } from "react";
import ResumeUpload from "./components/ResumeUpload";
import CandidateForm from "./components/CandidateForm";

function App() {
  const [parsedData, setParsedData] = useState(null);

  return (
    <div className="min-h-screen bg-gray-50">
      {/* Header */}
      <header className="bg-white shadow-sm">
        <div className="max-w-6xl mx-auto px-6 py-4">
          <h1 className="text-2xl font-semibold text-gray-800">
            Resume Parser
          </h1>
          <p className="text-sm text-gray-500">
            Auto-fill candidate details from resume
          </p>
        </div>
      </header>

      {/* Main Content */}
      <main className="max-w-6xl mx-auto px-6 py-8">
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
          {/* Left: Resume Upload */}
          <div className="bg-white rounded-xl shadow p-6">
            <h2 className="text-lg font-medium text-gray-700 mb-4">
              Upload Resume
            </h2>
            <ResumeUpload onParsed={setParsedData} />
          </div>

          {/* Right: Candidate Form */}
          <div className="bg-white rounded-xl shadow p-6">
            {/* {parsedData ? (
              <CandidateForm parsedData={parsedData} />
            ) : (
              <div className="text-sm text-gray-400 italic">
                Upload a resume to auto-fill candidate details.
              </div>
            )} */}
            <CandidateForm parsedData={parsedData} />
          </div>
        </div>
      </main>
    </div>
  );
}

export default App;
