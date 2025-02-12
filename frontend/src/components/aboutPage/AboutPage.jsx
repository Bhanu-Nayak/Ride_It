import { motion } from "framer-motion";
import React from "react";

export default function AboutPage() {
  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gradient-to-br from-gray-900 to-black text-white p-6">
      <motion.div
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5 }}
        className="w-full max-w-4xl bg-gray-800 p-8 rounded-lg shadow-lg text-center"
      >
        <h1 className="text-4xl font-bold">About Our Bike Rental Platform</h1>
        <p className="mt-4 text-lg text-gray-300">
          Welcome to our premium bike rental service, where convenience meets
          affordability. We provide a seamless experience for both customers
          looking to rent a bike and owners looking to list their bikes for
          rent.
        </p>
        <p className="mt-4 text-lg text-gray-300">
          Whether you need a ride for a few hours, a day, or a month, we have a
          variety of two-wheelers to suit your needs. Our platform ensures
          verified users and a hassle-free booking process with secure payments.
        </p>
        <h2 className="text-2xl font-semibold mt-6">Why Choose Us?</h2>
        <ul className="mt-4 text-gray-300 list-disc list-inside">
          <li>Affordable and transparent pricing</li>
          <li>Verified owners and renters for safety</li>
          <li>Easy booking and secure transactions</li>
          <li>Wide range of bikes available</li>
        </ul>
      </motion.div>
    </div>
  );
}
