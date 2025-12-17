// utils/mapParsedData.js
export function mapParsedData(parsedData) {
  return {
    firstName: parsedData?.firstName || "",
    lastName: parsedData?.lastName || "",
    gender: parsedData?.gender || "",
    dob: parsedData?.dob || "",
    email: parsedData?.email || "",
    expectedCTC: parsedData?.expected_ctc || "",
    skills: parsedData?.skills?.join(", ") || "",
  };
}
