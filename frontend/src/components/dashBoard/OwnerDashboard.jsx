import OwnerService from "../../../service/OwnerService";
import { Button } from "../ui/button";
import { motion } from "framer-motion";
import { Bike } from "lucide-react";
import React, { useEffect, useState } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";

export default function OwnerDashboard() {
  const navigate = useNavigate();
  const location = useLocation();
  // const currUser = JSON.parse(localStorage.getItem("user"));
  const [owner, setOwner] = useState(null);
  const [filePath, setFilePath] = useState("");
  const [bikes, setBikes] = useState();
  const [toggle, setToggle] = useState(false);
  const ownerPP = localStorage.getItem("userpp");
  useEffect(() => {
    if (location.state != null) {
      setOwner(location.state);
    }
    if (ownerPP != null) {
      // console.log("check");
      const { name } = JSON.parse(ownerPP);
      setFilePath(name);
    }
    if (
      localStorage.getItem("user") == null ||
      JSON.parse(localStorage.getItem("user")).role == "CUSTOMER"
    ) {
      navigate("/unauthorized");
    } else {
      console.log("else");

      setOwner(JSON.parse(localStorage.getItem("user")));
    }
  }, []);
  const handleClick = () => {
    setToggle(!toggle);
  };
  const editOwner = (owner) => {
    navigate("edit", { state: owner });
  };
  const handleBikeEdit = (bike) => {
    navigate("editbike", { state: bike });
  };
  const handleDelete = () => {
    if (confirm("are you sure?")) {
      OwnerService.deleteOwner(owner.ownerId).then((res) => {
        console.log(res);
        localStorage.removeItem("user");
        navigate("/");
      });
    } else {
    }
  };
  const handleLogout = () => {
    navigate("/logout");
    console.log("Logout clicked");
  };
  const handleBikeDelete = (id) => {
    OwnerService.deleteBike(id).then((res) => {
      console.log(res);
      window.location.reload();
    });
  };
  useEffect(() => {
    if (owner != null) {
      OwnerService.getOwnerBikes(owner.ownerId)
        .then((res) => {
          setBikes(res.data);
          console.log(res.data);
        })
        .then(() => {
          setToggle(true);
        });
    }
  }, [owner]);

  // const owner = JSON.parse(localStorage.getItem("user"));
  // console.log(ownerPP);

  return (
    <div className="min-h-screen bg-gray-900 text-white p-6">
      {/* If owner is null, show loading or redirect */}
      {!owner ? (
        <p className="text-center text-lg">Loading owner details...</p>
      ) : (
        <>
          {/* Owner Details */}
          <div className="bg-gray-800 p-6 rounded-lg shadow-lg mb-6 flex flex-col items-center">
            {filePath && (
              <img
                src={filePath}
                alt="Profile"
                className="w-24 h-24 rounded-full mb-4 border-4 border-gray-700"
              />
            )}
            <h2 className="text-3xl font-bold text-center">Owner Dashboard</h2>
            <p className="mt-4 text-lg">
              <strong>Name:</strong> {owner.name}
            </p>
            <p className="text-lg">
              <strong>Contact:</strong> {owner.contactNum}
            </p>
            <p className="text-lg">
              <strong>Email:</strong> {owner.email}
            </p>
            <p className="text-lg">
              <strong>Aadhar Number:</strong> {owner.aadhaarNumber}
            </p>
            <p className="text-lg">
              <strong>Delivery Available:</strong>{" "}
              {owner.deliveryAvailability ? "Yes" : "No"}
            </p>

            <div className="flex space-x-4 mt-4">
              <Link to="addbike">
                <Button className="w-full bg-blue-600 hover:bg-blue-700 text-white py-2 mt-2">
                  Add Bike
                </Button>
              </Link>
              <Button
                className="w-full bg-blue-600 hover:bg-blue-700 text-white py-2 mt-2"
                onClick={() => editOwner(owner)}
              >
                Edit Profile
              </Button>
              <Button
                className="bg-red-600 hover:bg-red-700 text-white px-4 py-2"
                onClick={handleDelete}
              >
                Delete Account
              </Button>
              <Button
                onClick={handleLogout}
                className="w-full bg-gray-600 hover:bg-gray-700 text-white py-3 mt-2"
              >
                Logout
              </Button>
            </div>
          </div>

          {/* Owner's Bikes */}
          <h2 className="text-2xl font-bold">Your Bikes</h2>
          {toggle && bikes ? (
            <div className="grid grid-cols-2 gap-6 mt-4">
              {bikes.map((bike) => (
                <motion.div
                  key={bike.bikeId}
                  className="bg-gray-800 p-4 rounded-lg shadow-lg"
                  whileHover={{ scale: 1.05 }}
                >
                  {console.log(bike.bikeImage?.[0]?.name)}
                  <img
                    src={bike.bikeImage?.[0]?.name}
                    alt={bike.model}
                    className="w-full h-40 object-contain"
                  />
                  <h3 className="text-xl font-semibold mt-2">{bike.model}</h3>
                  <p className="text-yellow-500 text-lg font-bold">
                    ₹{bike.rentDaily}
                  </p>
                  <p className="text-gray-300">Status: {bike.status}</p>
                  <Button
                    className="w-full bg-blue-600 hover:bg-blue-700 text-white py-2 mt-2"
                    onClick={() => handleBikeEdit(bike)}
                  >
                    Edit Bike
                  </Button>
                  <Button
                    className="w-full bg-red-600 hover:bg-red-700 text-white py-2 mt-2"
                    onClick={() => handleBikeDelete(bike.bikeId)}
                  >
                    Delete Bike
                  </Button>
                </motion.div>
              ))}
            </div>
          ) : (
            ""
          )}
          <button type="button" onClick={handleClick}>
            {toggle ? "Hide Bikes" : "Show Bikes"}
          </button>
        </>
      )}
    </div>
  );
}
