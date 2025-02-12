import OwnerService from "../../../service/OwnerService";
import { Button } from "../ui/button";
import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";

const initialBikeData = {
  name: "Honda Activa",
  price: 499,
  status: "Available",
  image: "../assets/activa.png",
};

export default function EditBike() {
  const location = useLocation();
  const navigate = useNavigate();
  const [bikeData, setBikeData] = useState(location.state);
  // console.log(location.state);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setBikeData({ ...bikeData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // console.log("before update" + bikeData);

    OwnerService.editBike(bikeData).then((res) => {
      // console.log(res);
      navigate("/owner");
    });
    console.log("Updated Bike Data:", bikeData);
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-900 text-white p-6">
      <div className="w-full max-w-lg bg-gray-800 p-8 rounded-lg shadow-lg">
        <h2 className="text-3xl font-bold text-center">Edit Bike Details</h2>
        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            type="text"
            name="name"
            placeholder="Bike Name"
            value={bikeData.model}
            onChange={handleChange}
            className="w-full p-3 bg-gray-700 text-white rounded-lg"
            required
            // readOnly
          />
          <input
            type="number"
            name="rentDaily"
            placeholder="rentDaily"
            value={bikeData.rentDaily}
            onChange={handleChange}
            className="w-full p-3 bg-gray-700 text-white rounded-lg"
            required
          />
          <input
            type="number"
            name="rentHourly"
            placeholder="rentHourly"
            value={bikeData.rentHourly}
            onChange={handleChange}
            className="w-full p-3 bg-gray-700 text-white rounded-lg"
            required
          />
          <input
            type="text"
            name="bikeCondition"
            placeholder="bikeCondition"
            value={bikeData.bikeCondition}
            onChange={handleChange}
            className="w-full p-3 bg-gray-700 text-white rounded-lg"
            required
          />
          <select
            name="status"
            value={bikeData.status}
            onChange={handleChange}
            className="w-full p-3 bg-gray-700 text-white rounded-lg"
          >
            <option value="default">Default</option>
            <option value="Booked">Booked</option>
            <option value="Damaged">Damaged</option>
            <option value="Available">Available</option>
          </select>
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
