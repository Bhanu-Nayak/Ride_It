import CustomerService from "../../../service/CustomerService";
import { Button } from "../ui/button";
import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";

export default function UpdateProfilePhoto() {
  const location = useLocation();
  const navigate = useNavigate();
  const [selectedFile, setSelectedFile] = useState(null);
  const [preview, setPreview] = useState("../assets/default-profile.jpg");
  console.log(location.state);
  const custId = location.state;
  const handleFileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setSelectedFile(file);
      setPreview(URL.createObjectURL(file));
    }
  };

  const handleUpload = () => {
    if (selectedFile) {
      console.log(selectedFile);

      CustomerService.updateProfilePhoto(custId, selectedFile).then((res) => {
        console.log(res);
        navigate("/profile");
      });
      // console.log(selectedFile);

      // console.log("Uploading: ", selectedFile.name);
      // Implement the API call to upload the image
    }
  };

  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gray-900 text-white p-6">
      <div className="w-full max-w-md bg-gray-800 p-8 rounded-lg shadow-lg flex flex-col items-center">
        <h2 className="text-3xl font-bold text-center mb-4">
          Update Profile Photo
        </h2>
        <img
          src={preview}
          alt="Profile Preview"
          className="w-32 h-32 rounded-full border-4 border-gray-700 mb-4"
        />
        <input
          type="file"
          accept="image/*"
          onChange={handleFileChange}
          className="mb-4"
        />
        <Button
          onClick={handleUpload}
          className="bg-yellow-500 hover:bg-yellow-600 text-black px-4 py-2 w-full"
        >
          Upload Photo
        </Button>
      </div>
    </div>
  );
}
