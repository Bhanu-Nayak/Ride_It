import AdminService from "../../../service/AdminService";
import { Button } from "../ui/button";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const adminData = {
  name: "Tanmay",
  email: "Tanmay@admin.com",
  profilePhoto: "passport.jpg",
};

const customers = [
  { id: 1, name: "Jane Doe", verified: false },
  { id: 2, name: "John Smith", verified: true },
];

const owners = [
  {
    id: 1,
    name: "Mike Johnson",
    verified: false,
    bikes: ["Honda Activa", "TVS Jupiter"],
    pendingPayment: "₹2000",
  },
  {
    id: 2,
    name: "Emily Davis",
    verified: true,
    bikes: ["Yamaha FZ"],
    pendingPayment: "₹1500",
  },
];

const bookings = [
  {
    id: 1,
    customer: "Tanmay Bhaturkar",
    bike: "activa 5g",
    date: "10 Feb 2025",
    status: "Completed",
  },
  {
    id: 2,
    customer: "Tanmay Bhaturkar",
    bike: "abc",
    date: "11 feb 2025",
    status: "Ongoing",
  },
];

export default function AdminPanel() {
  const navigate = useNavigate();
  const [adminData, setAdminData] = useState();
  const [customerList, setCustomerList] = useState(null);
  const [ownerList, setOwnerList] = useState(null);

  useEffect(() => {
    if (location.state != null) {
      setAdminData(location.state);
    }
    if (
      localStorage.getItem("user") == null ||
      JSON.parse(localStorage.getItem("user")).role == "CUSTOMER" ||
      JSON.parse(localStorage.getItem("user")).role == "OWNER"
    ) {
      navigate("/unauthorized");
    } else {
      console.log("else");

      setAdminData(JSON.parse(localStorage.getItem("user")));
    }
  }, []);

  useEffect(() => {
    AdminService.getAllCustomer().then((res) => {
      setCustomerList(res.data);
      // console.log(res.data);
    });
    AdminService.getAllOwner().then((res) => {
      setOwnerList(res.data);
      console.log(res.data);
    });
  }, []);

  const handleVerify = (type, id) => {
    if (type === "customer") {
      AdminService.verifyCustomer(id).then((res) => {
        setCustomerList(
          customerList.map((c) =>
            c.custId === id ? { ...c, verifiedStatus: true } : c
          )
        );
      });
    } else {
      AdminService.verifyOwner(id).then((res) => {
        setOwnerList(
          ownerList.map((o) =>
            o.ownerId === id ? { ...o, verifiedStatus: true } : o
          )
        );
      });
    }
  };
  const handleLogout = () => {
    navigate("/logout");
    console.log("Logout clicked");
  };
  const handlePayment = (id) => {
    setOwnerList(
      ownerList.map((o) => (o.id === id ? { ...o, pendingPayment: "Paid" } : o))
    );
  };
  const editAdmin = () => {
    navigate("edit", { state: adminData });
  };

  return (
    <div className="min-h-screen bg-gray-900 text-white p-6">
      {/* Admin Details */}
      {adminData ? (
        <div className="bg-gray-800 p-6 rounded-lg shadow-lg mb-6 flex flex-col items-center">
          <img
            src={adminData?.profilePhoto}
            alt="Admin Profile"
            className="w-24 h-24 rounded-full mb-4 border-4 border-gray-700"
          />
          <h2 className="text-3xl font-bold text-center">Admin Panel</h2>
          <p className="mt-2 text-lg">
            <strong>Name:</strong> {adminData.name}
          </p>
          <p className="text-lg">
            <strong>Number:</strong> {adminData.contactNum}
          </p>
          <p className="text-lg">
            <strong>Email:</strong> {adminData.email}
          </p>
          <div className="flex space-x-4 mt-4">
            <Button
              className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2"
              onClick={editAdmin}
            >
              Edit Profile
            </Button>
            <Button
              className="bg-red-600 hover:bg-red-700 text-white px-4 py-2"
              onClick={handleLogout}
            >
              Logout
            </Button>
          </div>
        </div>
      ) : (
        <p className="text-center text-lg">Loading owner details...</p>
      )}

      {/* Insights */}
      <div className="grid grid-cols-2 gap-6">
        {customerList ? (
          <div className="bg-gray-800 p-6 rounded-lg shadow-lg">
            <h3 className="text-xl font-bold">Customer List</h3>
            {customerList.map((customer) => (
              <div
                key={customer.custId}
                className="mt-4 bg-gray-700 p-3 rounded-lg flex justify-between"
              >
                <span>
                  {customer.name} - {customer.email} -{" "}
                  {customer.verifiedStatus ? "Verified" : "Not Verified"}
                </span>
                {!customer.verifiedStatus && (
                  <Button
                    onClick={() => handleVerify("customer", customer.custId)}
                    className="bg-green-600"
                  >
                    Verify
                  </Button>
                )}
              </div>
            ))}
          </div>
        ) : (
          <p className="text-center text-lg">Loading owner details...</p>
        )}
        {ownerList ? (
          <div className="bg-gray-800 p-6 rounded-lg shadow-lg">
            <h3 className="text-xl font-bold">Owner List</h3>
            {ownerList.map((owner) => (
              <div
                key={owner.ownerId}
                className="mt-4 bg-gray-700 p-3 rounded-lg"
              >
                <span>
                  {owner.ownerName} {owner.email} -{" "}
                  {owner.verifiedStatus ? "Verified" : "Not Verified"}
                </span>

                <p className="text-sm">
                  Bikes:{" "}
                  {owner.bikes?.map((bike) => (
                    <span key={bike.bikeId} className="p-1 ">
                      {bike.model},
                    </span>
                  ))}
                </p>
                {/* <p className="text-sm font-bold">
                Pending Payment: {owner.pendingPayment}
              </p> */}
                {!owner.verifiedStatus && (
                  <Button
                    onClick={() => handleVerify("owner", owner.ownerId)}
                    className="bg-green-600 mt-2"
                  >
                    Verify
                  </Button>
                )}
                {/* {owner.pendingPayment !== "Paid" && (
                <Button
                  onClick={() => handlePayment(owner.id)}
                  className="bg-yellow-500 mt-2"
                >
                  Handle Payment
                </Button>
              )} */}
              </div>
            ))}
          </div>
        ) : (
          <p className="text-center text-lg">Loading owner details...</p>
        )}
      </div>

      {/* Booking History */}
      <div className="bg-gray-800 p-6 rounded-lg shadow-lg mt-6">
        <h3 className="text-xl font-bold">Booking History</h3>
        {bookings.map((booking) => (
          <div key={booking.id} className="mt-4 bg-gray-700 p-3 rounded-lg">
            <p>
              {booking.customer} booked {booking.bike}
            </p>
            <p className="text-sm">
              Date: {booking.date}, Status: {booking.status}
            </p>
          </div>
        ))}
      </div>
    </div>
  );
}
