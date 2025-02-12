import OwnerService from "../../../service/OwnerService";
import { Button } from "../ui/button";
import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";

const initialOwnerData = {
  name: "John Doe",
  contact: "9876543210",
  email: "johndoe@example.com",
  aadharNumber: "1234-5678-9012",
  deliveryAvailable: true,
  profilePhoto: "../assets/profile.jpg",
};

export default function EditOwner() {
  const location = useLocation();
  const navigate = useNavigate();
  const [ownerData, setOwnerData] = useState(location.state);

  const handleChange = (e) => {
    const { name, value } = e.target;
    // setOwnerData({
    //   ...ownerData,
    //   [name]: type == "checkbox" ? checked : value,
    // });
    setOwnerData({ ...ownerData, [name]: value });
    // setBikeData({ ...bikeData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    OwnerService.editOwner(ownerData).then((res) => {
      console.log(res);
      navigate("/owner", { state: ownerData });
    });
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-900 text-white p-6">
      <div className="w-full max-w-lg bg-gray-800 p-8 rounded-lg shadow-lg">
        <h2 className="text-3xl font-bold text-center">Edit Owner Profile</h2>
        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            type="text"
            name="name"
            placeholder="Name"
            value={ownerData.name}
            onChange={handleChange}
            className="w-full p-3 bg-gray-700 text-white rounded-lg"
            required
          />
          <input
            type="number"
            name="contactNum"
            placeholder="Contact Number"
            value={ownerData.contactNum}
            onChange={handleChange}
            className="w-full p-3 bg-gray-700 text-white rounded-lg"
            required
          />
          <input
            type="email"
            name="email"
            placeholder="Email"
            value={ownerData.email}
            onChange={handleChange}
            className="w-full p-3 bg-gray-700 text-white rounded-lg"
            required
          />
          <input
            type="number"
            name="aadhaarNumber"
            placeholder="Aadhar Number"
            value={ownerData.aadhaarNumber}
            onChange={handleChange}
            className="w-full p-3 bg-gray-700 text-white rounded-lg"
            required
          />
          <label className="flex items-center space-x-2">
            <input
              type="checkbox"
              name="deliveryAvailability"
              checked={ownerData.deliveryAvailability}
              onChange={handleChange}
              className="w-5 h-5"
            />
            <span>Available for Delivery</span>
          </label>
          <Button
            type="submit"
            className="w-full bg-yellow-500 hover:bg-yellow-600 text-black py-3"
          >
            Save Changes
          </Button>
        </form>
      </div>
    </div>
  );
}
