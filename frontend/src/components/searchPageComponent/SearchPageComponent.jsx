import { Button } from "../ui/button";
import axios from "axios";
import { motion } from "framer-motion";
import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";

export default function SearchPageComponent() {
  const navigate = useNavigate();
  const location = useLocation();
  // console.log(location.state);

  // const [selectedLocation, setSelectedLocation] = useState("");
  const [selectedModel, setSelectedModel] = useState("");
  const [imagearr, setImageArr] = useState([]);
  const [bikes, setBikes] = useState([
    // {
    //   bikeId: 111,
    //   model: "Honda Activa",
    //   rentDaily: 499,
    //   mileage: 7,
    //   image: "../assets/activa.png",
    // },
    // {
    //   bikeId: 222,
    //   model: "TVS Jupiter",
    //   rentDaily: 499,
    //   mileage: 2,
    //   image: "../assets/jupiter.png",
    // },
    // {
    //   bikeId: 333,
    //   model: "Honda Aviator",
    //   rentDaily: 499,
    //   mileage: 1,
    // },
  ]);
  const [filteredBike, setFilteredBike] = useState([]);
  const time = location.state;
  const getAllBikes = () => {
    axios.get("http://localhost:8080/customer/searchbike").then((resp) => {
      setBikes([...bikes, ...resp.data]);
      // console.log("is it bike: " + resp.data[0].bikeImage[0].filepath);
    });
  };
  useEffect(() => {
    getAllBikes();
  }, []);
  const handleClick = (bike) => {
    if (localStorage.getItem("user") != null) {
      navigate("/summary", { state: { ...bike, ...time } });
    } else {
      navigate("/login");
    }
  };
  return (
    <div className="min-h-screen bg-gray-900 text-white p-6 flex">
      {/* Sidebar Filters */}
      <aside className="w-1/4 bg-gray-800 p-4 rounded-lg shadow-lg">
        <h2 className="text-xl font-bold">Search by Model</h2>
        <div className="mt-2">
          <input
            type="checkbox"
            id="activa"
            value="Activa"
            onChange={(e) => setSelectedModel(e.target.value)}
          />
          <label htmlFor="activa" className="ml-2">
            Activa
          </label>
        </div>
        <h2 className="text-xl font-bold mt-4">Search by Location</h2>
        <select
          className="w-full p-2 bg-gray-700 rounded-lg"
          onChange={(e) => setSelectedLocation(e.target.value)}
        >
          <option value="">Select Location</option>
          <option value="Pune">Pune</option>
          {/* <option value="Mumbai">Mumbai</option> */}
        </select>
      </aside>

      {/* Bike Listings */}
      <div className="w-3/4 ml-6">
        <h2 className="text-2xl font-bold">Models Found: {bikes.length}</h2>
        <div className="grid grid-cols-3 gap-6 mt-4">
          {bikes.map((bike) => (
            <motion.div
              key={bike.bikeId}
              className="bg-gray-800 p-4 rounded-lg shadow-lg"
              whileHover={{ scale: 1.05 }}
            >
              <img
                src={bike.bikeImage?.[0]?.name}
                alt={bike.name}
                className="w-full h-40 object-contain"
              />
              <h3 className="text-xl font-semibold mt-2">{bike.model}</h3>
              <p className="text-yellow-500 text-lg font-bold">
                ₹{bike.rentDaily}
              </p>
              <p className="text-red-500">{bike.mileage} left</p>
              {/* <select className="w-full p-2 mt-2 bg-gray-700 rounded-lg">
                <option>Select nearest location</option>
              </select> */}
              <Button
                className="w-full bg-red-600 hover:bg-red-700 text-white py-2 mt-2"
                onClick={() => handleClick(bike)}
              >
                Book Now
              </Button>
            </motion.div>
          ))}
        </div>
      </div>
    </div>
  );
}
