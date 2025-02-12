import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";

export default function LogoutPage() {
  const navigate = useNavigate();

  useEffect(() => {
    // Perform logout actions such as clearing session storage
    // localStorage.removeItem("authToken");
    sessionStorage.clear();

    // Redirect to login page after 2 seconds
    setTimeout(() => {
      localStorage.removeItem("userpp");
      const role = JSON.parse(localStorage.getItem("user")).role;
      localStorage.removeItem("user");
      if (role == "OWNER") {
        navigate("/ownerlogin");
      } else if (role == "CUSTOMER") {
        navigate("/login");
      } else {
        navigate("/adminlogin");
      }
    }, 2000);
  }, [navigate]);

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-900 text-white p-6">
      <div className="text-center">
        <h2 className="text-3xl font-bold">Logging Out...</h2>
        <p className="mt-2 text-lg text-gray-400">
          You will be redirected to the login page shortly.
        </p>
      </div>
    </div>
  );
}
