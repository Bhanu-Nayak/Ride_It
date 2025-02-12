import CustomerService from "../../../service/CustomerService";
import { Button } from "../ui/button";
import { Card, CardContent, CardHeader, CardTitle } from "../ui/card";
import { Input } from "../ui/input";
import { motion } from "framer-motion";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function CustomerRegisterPage() {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    //   private String name;
    // private String contactNum;
    // private String email;
    // private String role;
    // private String password;

    // private String drivingLicense;
    // private String location;
    // private String aadhaarNumber;
    name: "",
    contactNum: "",
    email: "",
    password: "",
    drivingLicense: "",
    // location: "",
    aadhaarNumber: "",
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    let obj = {
      name: formData.name,
      contactNum: formData.contactNum,
      email: formData.email,
      role: "customer",
      password: formData.password,
      drivingLicense: formData.drivingLicense,
      // location: JSON.parse(formData.location),
      aadhaarNumber: formData.aadhaarNumber,
    };
    CustomerService.customerRegister(obj).then((result) => {
      console.log(JSON.stringify(result.data));
      navigate("/login");
    });
    console.log("Form Submitted", formData);
    // TODO: Handle API call for registration
  };

  return (
    <motion.div
      initial={{ opacity: 0, y: -20 }}
      animate={{ opacity: 1, y: 0 }}
      transition={{ duration: 0.5 }}
      className="flex justify-center items-center min-h-screen bg-gray-800 text-white"
    >
      <Card className="w-full text-white max-w-md p-6 shadow-lg">
        <CardHeader>
          <CardTitle>Customer Registration</CardTitle>
        </CardHeader>
        <CardContent>
          <form onSubmit={handleSubmit} className="space-y-4">
            <Input
              name="name"
              placeholder="Full Name"
              onChange={handleChange}
              required
            />
            <Input
              name="contactNum"
              placeholder="Contact Number"
              onChange={handleChange}
              required
            />
            <Input
              type="email"
              name="email"
              placeholder="Email"
              onChange={handleChange}
              required
            />
            <Input
              type="password"
              name="password"
              placeholder="Password"
              onChange={handleChange}
              required
            />
            <Input
              name="aadhaarNumber"
              placeholder="Aadhaar Card Number"
              onChange={handleChange}
              required
            />
            <Input
              name="drivingLicense"
              placeholder="Driving License Number"
              onChange={handleChange}
              required
            />
            {/* <Input
              name="location"
              placeholder="Location"
              onChange={handleChange}
              required
            /> */}
            <Button
              type="submit"
              className="w-full bg-orange-400 hover:bg-yellow-600"
            >
              Register
            </Button>
          </form>
          <p className="text-gray-400 text-center mt-4">
            Already have an account?{" "}
            <a href="/login" className="text-orange-400 hover:underline">
              Sign in
            </a>
          </p>
        </CardContent>
      </Card>
    </motion.div>
  );
}
