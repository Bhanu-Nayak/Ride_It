import axios from "axios";
import { useState } from "react";

const ImageUpload = () => {
  const [image, setImage] = useState(null);
  const [preview, setPreview] = useState(null);
  const [imageSrc, setImageSrc] = useState(null);

  const handleImageChange = (event) => {
    const file = event.target.files[0];
    setImage(file);

    // Generate image preview
    const reader = new FileReader();
    reader.onloadend = () => {
      setPreview(reader.result);
    };
    reader.readAsDataURL(file);
  };

  const handleUpload = async () => {
    if (!image) {
      alert("Please select an image first!");
      return;
    }

    const formData = new FormData();
    formData.append("id", 1);
    formData.append("file", image);

    try {
      const response = await axios.post(
        "http://localhost:8080/owner/uploadimage",
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );
      alert("Image uploaded successfully!");
      console.log(response.data);
    } catch (error) {
      console.error("Upload failed:", error);
      alert("Upload failed!");
    }
  };
  const getImage = () => {
    axios
      .get("http://localhost:8080/owner/getimage/1")
      // .then((res) => console.log(res.data.image));
      // .then((res) => res.data.image.arrayBuffer()) // Get raw byte array
      .then((res) => res.data.json())
      .then((data) => console.log(data));
  };

  return (
    <div className="flex flex-col items-center space-y-4 p-4 bg-gray-100 rounded-lg shadow-md">
      <input
        type="file"
        accept="image/*"
        onChange={handleImageChange}
        className="p-2 border rounded"
      />
      {preview && (
        <img
          src={preview}
          alt="Preview"
          className="w-32 h-32 object-cover rounded-lg"
        />
      )}
      <button
        onClick={handleUpload}
        className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-700"
      >
        Upload Image
      </button>
      <div className="p-4">
        {imageSrc ? (
          <img
            src={imageSrc}
            alt="Bike"
            className="w-64 h-64 object-cover rounded-lg shadow-md"
          />
        ) : (
          <p>Loading image...</p>
        )}
      </div>
      <button
        onClick={getImage}
        className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-700"
      >
        Get Image
      </button>
    </div>
  );
};

export default ImageUpload;
