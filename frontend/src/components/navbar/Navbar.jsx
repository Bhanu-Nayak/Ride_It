// import logo from "logo.png";
import React from "react";
import { Link } from "react-router-dom";

export default function Navbar({ user, role }) {
  return (
    <nav className="text-white bg-gray-900 flex justify-between items-center shadow-lg">
      <div className="flex items-center mx-8">
        <Link to="/">
          <img src="/logo3.png" alt="Logo" className="h-10" />
        </Link>
      </div>
      <div className="space-x-6 p-5">
        <Link to="/" className="hover:text-orange-500">
          Home
        </Link>
        <Link to="/about" className="hover:text-orange-500">
          About Us
        </Link>
        {role == "OWNER" ? (
          ""
        ) : (
          <Link to="/ownerreg" className="hover:text-orange-500">
            Rent With Us
          </Link>
        )}

        {user ? (
          role == "CUSTOMER" ? (
            <Link to="/profile" className="hover:text-orange-500">
              {user}
            </Link>
          ) : (
            <Link to="/owner" className="hover:text-orange-500">
              {user}
            </Link>
          )
        ) : (
          <Link to="/login" className="hover:text-orange-500">
            Login
          </Link>
        )}
      </div>
    </nav>
  );
}
