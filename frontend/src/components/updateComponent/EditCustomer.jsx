import CustomerService from "../../../service/CustomerService";
import { Button } from "../ui/button";
import React, { useState } from "react";
import { Navigate, useLocation, useNavigate } from "react-router-dom";

const initialUserData = {
  name: "Jane Doe",
  contact: "9876543210",
  email: "janedoe@example.com",
  profilePhoto: "../assets/profile.jpg",
};

export default function EditCustomer() {
  const location = useLocation();
  const navigate = useNavigate();
  const [userData, setUserData] = useState(location.state);
  // console.log(userData.custId);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUserData({ ...userData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    CustomerService.updateCustomer(userData).then((res) => {
      console.log(res);
      navigate("/profile");
    });
    console.log("Updated User Data:", userData);
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-900 text-white p-6">
      <div className="w-full max-w-lg bg-gray-800 p-8 rounded-lg shadow-lg">
        <h2 className="text-3xl font-bold text-center">Edit Profile</h2>
        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            type="text"
            name="name"
            placeholder="Name"
            value={userData.name}
            onChange={handleChange}
            className="w-full p-3 bg-gray-700 text-white rounded-lg"
            required
          />
          <input
            type="text"
            name="contactNum"
            placeholder="Contact Number"
            value={userData.contactNum}
            onChange={handleChange}
            className="w-full p-3 bg-gray-700 text-white rounded-lg"
            required
          />
          <input
            type="email"
            name="email"
            placeholder="Email"
            value={userData.email}
            onChange={handleChange}
            className="w-full p-3 bg-gray-700 text-white rounded-lg"
            required
          />
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
