import CustomerService from "../../../service/CustomerService";
import { Button } from "../ui/button";
import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";

const initialCustomerData = {
  name: "Jane Doe",
  contact: "9876543210",
  email: "janedoe@example.com",
  profilePhoto: "../assets/profile.jpg",
  bookings: [
    { id: 1, bike: "Honda Activa", date: "02 Feb 2025", status: "Completed" },
    { id: 2, bike: "TVS Jupiter", date: "10 Jan 2025", status: "Ongoing" },
  ],
};

export default function CustomerProfile() {
  const navigate = useNavigate();
  const [customerData, setCustomerData] = useState(null);
  useEffect(() => {
    if (
      localStorage.getItem("user") == null ||
      JSON.parse(localStorage.getItem("user")).role == "OWNER"
    ) {
      navigate("/unauthorized");
    } else {
      CustomerService.getCustomer(
        JSON.parse(localStorage.getItem("user")).custId
      ).then((res) => {
        setCustomerData(res.data);
      });
    }
  }, []);

  const handleEdit = () => {
    navigate("editcustomer", { state: customerData });
    console.log("Edit profile clicked");
  };

  const handleDelete = () => {
    if (confirm("are you sure?")) {
      CustomerService.deleteCustomer(customerData.custId).then((res) => {
        console.log(res);
        localStorage.removeItem("user");
        navigate("/");
      });
    } else {
    }
  };

  const handleUpdatePhoto = () => {
    navigate("profilephoto", { state: customerData.custId });
    console.log("Update profile photo clicked");
  };

  const handleBookingHistory = () => {
    console.log("View booking history clicked");
  };
  const handleLogout = () => {
    navigate("/logout");
    console.log("Logout clicked");
  };

  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gray-900 text-white p-6">
      {!customerData ? (
        <p className="text-center text-lg">Loading owner details...</p>
      ) : (
        <div className="w-full max-w-lg bg-gray-800 p-8 rounded-lg shadow-lg flex flex-col items-center">
          <img
            src={customerData?.profilePhoto?.name}
            alt="Profile"
            className="w-24 h-24 rounded-full mb-4 border-4 border-gray-700"
          />
          <h2 className="text-3xl font-bold text-center">Customer Profile</h2>
          <p className="mt-4 text-lg">
            <strong>Name:</strong> {customerData.name}
          </p>
          <p className="text-lg">
            <strong>Contact:</strong> {customerData.contactNum}
          </p>
          <p className="text-lg">
            <strong>Email:</strong> {customerData.email}
          </p>

          <div className="flex space-x-4 mt-4">
            {/* <Link to="editcustomer"> */}
            <Button
              onClick={handleEdit}
              className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2"
            >
              Edit Profile
            </Button>
            {/* </Link> */}
            <Button
              onClick={handleDelete}
              className="bg-red-600 hover:bg-red-700 text-white px-4 py-2"
            >
              Delete Account
            </Button>
          </div>
          {/* <Link to="profilephoto"> */}
          <Button
            onClick={handleUpdatePhoto}
            className="w-full bg-yellow-500 hover:bg-yellow-600 text-black py-3 mt-4"
          >
            Update Profile Photo
          </Button>
          {/* </Link> */}
          <Link to="bookinghistory">
            <Button
              onClick={handleBookingHistory}
              className="w-full bg-green-500 hover:bg-green-600 text-white py-3 mt-2"
            >
              Booking History
            </Button>
          </Link>
          <Button
            onClick={handleLogout}
            className="w-full bg-gray-600 hover:bg-gray-700 text-white py-3 mt-2"
          >
            Logout
          </Button>
        </div>
      )}
    </div>
  );
}
