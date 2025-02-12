import OwnerService from "../../../service/OwnerService";
import { Button } from "../ui/button";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export default function AddBikeForm() {
  const [location, setLocation] = useState();
  const [address, setAddress] = useState();
  const [toggle, setToggle] = useState(false);
  function getLocation() {
    console.log("getting Location....");

    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        setLocation({
          latitude: position.coords.latitude,
          longitude: position.coords.longitude,
        });
        console.log(position.coords.latitude, position.coords.longitude);
      });
    } else {
      setToggle(true);
    }
    // console.log(location);
  }
  useEffect(() => {
    console.log(location);
  }, [location]);
  // getLocation();
  const navigate = useNavigate();
  const owner = JSON.parse(localStorage.getItem("user")).ownerId;
  const [bikeData, setBikeData] = useState({
    ownerId: owner,
    model: "",
    brand: "",
    fuel: "",
    engineCapacity: "",
    registrationNumber: "",
    rentHourly: "",
    rentDaily: "",
    deliveryOption: true,
    bikeCondition: "",
    yearOfManufacture: "",
    bikeType: "",
    mileage: "",
    image: null,
  });

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setBikeData({ ...bikeData, [name]: type === "checkbox" ? checked : value });
  };

  const handleImageChange = (e) => {
    setBikeData({ ...bikeData, image: e.target.files[0] });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("New Bike Data:", bikeData);
    OwnerService.addNewBike(bikeData).then((res) => {
      console.log(res);
      navigate("/owner");
    });
    // Implement API call to submit bike data
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-900 text-white p-6">
      <div className="w-full max-w-lg bg-gray-800 p-8 rounded-lg shadow-lg">
        <h2 className="text-3xl font-bold text-center mb-6">Add New Bike</h2>
        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            type="text"
            name="model"
            placeholder="Model"
            value={bikeData.model}
            onChange={handleChange}
            className="w-full p-3 bg-gray-700 text-white rounded-lg"
            required
          />
          <input
            type="text"
            name="brand"
            placeholder="Brand"
            value={bikeData.brand}
            onChange={handleChange}
            className="w-full p-3 bg-gray-700 text-white rounded-lg"
            required
          />
          <input
            type="text"
            name="fuel"
            placeholder="Fuel Type"
            value={bikeData.fuel}
            onChange={handleChange}
            className="w-full p-3 bg-gray-700 text-white rounded-lg"
            required
          />
          <input
            type="text"
            name="engineCapacity"
            placeholder="Engine Capacity"
            value={bikeData.engineCapacity}
            onChange={handleChange}
            className="w-full p-3 bg-gray-700 text-white rounded-lg"
            required
          />
          <input
            type="text"
            name="registrationNumber"
            placeholder="Registration Number"
            value={bikeData.registrationNumber}
            onChange={handleChange}
            className="w-full p-3 bg-gray-700 text-white rounded-lg"
            required
          />
          <input
            type="number"
            name="rentHourly"
            placeholder="Rent Hourly (₹)"
            value={bikeData.rentHourly}
            onChange={handleChange}
            className="w-full p-3 bg-gray-700 text-white rounded-lg"
            required
          />
          <input
            type="number"
            name="rentDaily"
            placeholder="Rent Daily (₹)"
            value={bikeData.rentDaily}
            onChange={handleChange}
            className="w-full p-3 bg-gray-700 text-white rounded-lg"
            required
          />
          <input
            type="text"
            name="bikeCondition"
            placeholder="Bike Condition"
            value={bikeData.bikeCondition}
            onChange={handleChange}
            className="w-full p-3 bg-gray-700 text-white rounded-lg"
            required
          />
          <input
            type="number"
            name="yearOfManufacture"
            placeholder="Year of Manufacture"
            value={bikeData.yearOfManufacture}
            onChange={handleChange}
            className="w-full p-3 bg-gray-700 text-white rounded-lg"
            required
          />
          <input
            type="text"
            name="bikeType"
            placeholder="Bike Type"
            value={bikeData.bikeType}
            onChange={handleChange}
            className="w-full p-3 bg-gray-700 text-white rounded-lg"
            required
          />
          {/* <input
            type="text"
            name="insuranceDetails"
            placeholder="Insurance Details"
            value={bikeData.insuranceDetails}
            onChange={handleChange}
            className="w-full p-3 bg-gray-700 text-white rounded-lg"
            required
          /> */}
          <input
            type="number"
            name="mileage"
            placeholder="Mileage (kmpl)"
            value={bikeData.mileage}
            onChange={handleChange}
            className="w-full p-3 bg-gray-700 text-white rounded-lg"
            required
          />
          {toggle ? (
            <input
              type="number"
              name="address"
              placeholder="Mileage (kmpl)"
              value={address}
              onChange={handleChange}
              className="w-full p-3 bg-gray-700 text-white rounded-lg"
              required
            />
          ) : (
            <Button
              type="button"
              className="bg-green-500 hover:bg-green-600 text-black py-3"
              onClick={getLocation}
            >
              getCurrentLocation
            </Button>
          )}
          <input
            type="file"
            accept="image/*"
            onChange={handleImageChange}
            className="w-full p-3 bg-gray-700 text-white rounded-lg"
          />
          <label className="flex items-center space-x-2">
            <input
              type="checkbox"
              name="deliveryOption"
              checked={bikeData.deliveryOption}
              onChange={handleChange}
              className="w-5 h-5"
            />
            <span>Delivery Option Available</span>
          </label>
          <Button
            type="submit"
            className="w-full bg-yellow-500 hover:bg-yellow-600 text-black py-3"
          >
            Add Bike
          </Button>
        </form>
      </div>
    </div>
  );
}
