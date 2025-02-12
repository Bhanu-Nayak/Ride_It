import { Button } from "../ui/button";
import React from "react";
import { useLocation, useNavigate } from "react-router-dom";

export default function PaymentSuccess() {
  const navigate = useNavigate();
  const location = useLocation();

  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gray-900 text-white p-6">
      <div className="text-center bg-gray-800 p-8 rounded-lg shadow-lg">
        <h2 className="text-4xl font-bold text-green-500">
          Payment Successful 🎉
        </h2>
        <p className="mt-4 text-lg text-gray-300">
          Your booking has been confirmed!
        </p>
        {/* <p className="text-gray-400">
          Check your email for confirmation details.
        </p> */}
        <Button
          onClick={() => navigate("/")}
          className="mt-6 bg-green-500 hover:bg-green-600 text-white px-6 py-2"
        >
          Go to Home
        </Button>
      </div>
    </div>
  );
}
