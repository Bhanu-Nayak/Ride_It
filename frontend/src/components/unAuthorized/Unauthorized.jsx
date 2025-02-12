import { Button } from "../ui/button";
import React from "react";
import { useNavigate } from "react-router-dom";

export default function Unauthorized() {
  const navigate = useNavigate();

  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gray-900 text-white p-6">
      <div className="text-center bg-gray-800 p-8 rounded-lg shadow-lg">
        <h2 className="text-4xl font-bold text-red-500">403 - Unauthorized</h2>
        <p className="mt-4 text-lg text-gray-300">
          You do not have permission to access this page.
        </p>
        <Button
          onClick={() => navigate("/")}
          className="mt-6 bg-yellow-500 hover:bg-yellow-600 text-black px-6 py-2"
        >
          Go to Home
        </Button>
      </div>
    </div>
  );
}
