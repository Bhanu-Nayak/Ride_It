import CustomerService from "../../../service/CustomerService";
import { Button } from "../ui/button";
import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";

export default function BookingSummary() {
  //   const bookingDetails = {
  //     bike: "Honda Activa",
  //     image: "../assets/activa.png",
  //     tripStart: "10 Feb 2025, 12:00 PM",
  //     tripEnd: "10 Feb 2025, 6:00 PM",
  //     location: "Katraj",
  //     rent: 240,
  //     deposit: 0,
  //   };
  const location = useLocation();
  const navigate = useNavigate();
  const [termsAccepted, setTermsAccepted] = useState(false);
  const [booking, setBooking] = useState(location.state);
  const [bookingDetails, setBookingDetails] = useState();
  console.log(location.state);
  const startDate = new Date(booking.startDate);
  const endDate = new Date(booking.endDate);
  const diffInMs = endDate - startDate;
  const diffInHours = diffInMs / (1000 * 60 * 60); // Convert ms to hours
  const days = Math.floor(diffInHours / 24);
  const hours = diffInHours % 24;
  console.log(`Difference: ${days} days and ${hours} hours`);
  const totalFee = days * booking.rentDaily + hours * booking.rentHourly;
  const handleClick = () => {
    if (localStorage.getItem("user") != null) {
      console.log(bookingDetails);

      CustomerService.bookABike(bookingDetails)
        .then((res) => {
          navigate("/success", { state: bookingDetails });
          console.log(res);
        })
        .catch((err) => {
          navigate("/rejected", { state: err });
        });
    } else {
      navigate("/login");
    }
  };
  const customer = JSON.parse(localStorage.getItem("user"));
  useEffect(() => {
    setBookingDetails({
      bikeId: booking.bikeId,
      custId: customer.custId,
      startTime: booking.startDate,
      endTime: booking.endDate,
      totalAmount: totalFee,
      // paymentStatus: "paid",
      bookingStatus: "ACTIVE",
      // deliveryRequested: false,
      lateFee: 0,
      extensionFee: 0,
    });
  }, []);
  return (
    <div className="min-h-screen bg-gray-800 text-white p-6 flex justify-center">
      <div className="w-full max-w-5xl grid grid-cols-3 gap-6">
        {/* Booking Summary */}
        <div className="bg-gray-900 p-6 rounded-lg shadow-lg col-span-1">
          <h2 className="text-xl font-bold border-b pb-2">Booking Summary</h2>
          <img
            src={booking.bikeImage?.[0]?.filepath.substring(52, 100)}
            alt={booking.model}
            className="w-full h-40 object-contain mt-4"
          />
          <h3 className="text-lg font-bold mt-2">{booking.model}</h3>
          <p>
            <strong>Trip Start:</strong> {booking.startDate}
          </p>
          <p>
            <strong>Trip End:</strong> {booking.endDate}
          </p>
          {/* <p>
            <strong>Location:</strong> {bookingDetails.location}{" "}
            <span className="text-blue-500 cursor-pointer">Change</span>
          </p> */}
          {/* <Button className="bg-green-600 hover:bg-green-700 text-white w-full mt-4">
            📞 Need Help? Call Us
          </Button> */}
        </div>

        {/* Pick-up Instructions */}
        <div className="bg-gray-900 p-6 rounded-lg shadow-lg col-span-1">
          <h2 className="text-xl font-bold border-b pb-2">
            Pick-up Instructions
          </h2>
          <ul className="mt-4 text-sm text-white-700 space-y-2">
            <li>✅ Driving License is mandatory at the time of pickup.</li>
            <li>
              ✅ KYC process must be followed: Aadhaar, College ID, Employee ID,
              Local Address Proof.
            </li>
            <li>
              ✅ One original government ID proof must be submitted at pickup.
            </li>
            <li>✅ Fuel Charges will be borne by the rider.</li>
            <li>
              ✅ Complimentary helmet available; extra helmet at ₹50 per day.
            </li>
            <li>
              ✅ Intra-city travel only; violation penalty ₹2000 per instance.
            </li>
          </ul>
        </div>

        {/* Price Details */}
        <div className="bg-gray-900 p-6 rounded-lg shadow-lg col-span-1">
          <h2 className="text-xl font-bold border-b pb-2">Price Details</h2>
          <p className="mt-4">
            <strong>Total Rent:</strong> ₹{totalFee}
          </p>
          <p>
            <strong>Refundable Deposit:</strong> ₹0 (To be paid later)
          </p>
          <p className="text-blue-500 cursor-pointer">Have a coupon?</p>
          <p className="mt-2 text-lg font-bold">Amount Payable: ₹{totalFee}</p>
          <div className="mt-4 flex items-center">
            <input
              type="checkbox"
              id="terms"
              checked={termsAccepted}
              onChange={() => setTermsAccepted(!termsAccepted)}
              className="mr-2"
            />
            <label htmlFor="terms" className="text-sm">
              I have read{" "}
              <span className="text-blue-500 cursor-pointer">
                Terms and Conditions
              </span>
            </label>
          </div>
          <Button
            disabled={!termsAccepted}
            className="w-full bg-teal-500 hover:bg-teal-600 text-white py-2 mt-4"
            onClick={handleClick}
          >
            Proceed To Payment
          </Button>
        </div>
      </div>
    </div>
  );
}
