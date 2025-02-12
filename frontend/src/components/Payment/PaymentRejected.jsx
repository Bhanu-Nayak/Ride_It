import { Button } from "../ui/button";
import React from "react";
import { useNavigate } from "react-router-dom";

export default function PaymentRejected() {
  const navigate = useNavigate();

  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gray-900 text-white p-6">
      <div className="text-center bg-gray-800 p-8 rounded-lg shadow-lg">
        <h2 className="text-4xl font-bold text-red-500">Payment Failed ❌</h2>
        <p className="mt-4 text-lg text-gray-300">
          Your payment was not successful.
        </p>
        <p className="text-gray-400">Please try again or contact support.</p>
        <Button
          onClick={() => navigate("/payment-retry")}
          className="mt-6 bg-red-500 hover:bg-red-600 text-white px-6 py-2"
        >
          Retry Payment
        </Button>
      </div>
    </div>
  );
}
