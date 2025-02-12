// import bikeImage from "../assets/bike.png";
import { Button } from "../ui/button";
import { motion } from "framer-motion";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function HomeComponent() {
  // const [city, setCity] = useState("");
  const [startDate, setStartDate] = useState(
    new Date().toISOString().slice(0, -8)
  );
  const [endDate, setEndDate] = useState(
    new Date(Date.now() + 60 * 60 * 1000).toISOString().slice(0, -8)
  );
  const navigate = useNavigate();
  const handleSubmit = (e) => {
    e.preventDefault();
    navigate("/search", { state: { startDate, endDate } });
  };

  return (
    <div
      style={{ backgroundImage: `url(bgimage.png)`, backgroundSize: "100%" }}
      className="min-h-screen flex flex-col items-center  bg-gradient-to-br from-gray-900 to-black text-white p-6"
    >
      {/* Header Section */}
      {/* <div className=""> */}
      <div className="w-full max-w-4xl mb-60 rounded-lg shadow-lg p-8 text-center">
        <h1 className="text-4xl font-bold">Rent a Bike & Scooty</h1>
        <p className="mt-2 text-lg text-gray-300">
          Explore your city with ease and affordability
        </p>
      </div>

      {/* Image Section */}
      {/* <div className="mt-6">
        <img src="logo.png" alt="Bike Rental" className="w-96 mx-auto" />
      </div> */}

      {/* Booking Section */}
      {/* <div className="w-auto"> */}
      <motion.div
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5 }}
        className="w-full max-w-4xl p-6 rounded-lg shadow-lg mt-6"
      >
        <form onSubmit={handleSubmit}>
          <div className="flex flex-col space-y-4">
            {/* <select
              className="p-3 bg-gray-700 text-white rounded-lg w-full"
              onChange={(e) => setCity(e.target.value)}
            >
              <option value="">Select City</option>
              <option value="Mumbai">Mumbai</option>
              <option value="Delhi">Delhi</option>
              <option value="Bangalore">Bangalore</option>
            </select> */}
            <input
              type="datetime-local"
              className="p-3 w-full bg-stone-600 text-white rounded-lg"
              // placeholder="Select Start Date and Time"
              value={startDate}
              min={new Date().toISOString().slice(0, -8)}
              onChange={(e) => setStartDate(e.target.value)}
            />
            <input
              type="datetime-local"
              className="p-3 w-full bg-stone-600 text-white rounded-lg"
              // placeholder="Select Start Date and Time"
              min={new Date().toISOString().slice(0, -8)}
              value={endDate}
              onChange={(e) => setEndDate(e.target.value)}
            />
            <Button
              type="submit"
              className="w-full bg-orange-600 hover:bg-orange-700 text-white py-3"
            >
              Rent Now
            </Button>
          </div>
        </form>
      </motion.div>
      {/* </div> */}
      {/* </div> */}
    </div>
  );
}
