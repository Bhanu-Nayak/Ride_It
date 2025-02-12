import React from "react";

const bookingHistory = [
  { id: 1, bike: "Honda Activa", date: "02 Feb 2025", status: "Completed" },
  { id: 2, bike: "TVS Jupiter", date: "10 Jan 2025", status: "Ongoing" },
  { id: 3, bike: "Yamaha FZ", date: "15 Dec 2024", status: "Cancelled" },
];

export default function BookingHistory() {
  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gray-900 text-white p-6">
      <div className="w-full max-w-2xl bg-gray-800 p-8 rounded-lg shadow-lg">
        <h2 className="text-3xl font-bold text-center">Booking History</h2>
        <div className="mt-6">
          {bookingHistory.length > 0 ? (
            <ul className="space-y-4">
              {bookingHistory.map((booking) => (
                <li
                  key={booking.id}
                  className="bg-gray-700 p-4 rounded-lg shadow-md"
                >
                  <p className="text-lg font-semibold">{booking.bike}</p>
                  <p className="text-gray-300">Date: {booking.date}</p>
                  <p
                    className={`font-bold ${
                      booking.status === "Completed"
                        ? "text-green-500"
                        : booking.status === "Ongoing"
                        ? "text-yellow-500"
                        : "text-red-500"
                    }`}
                  >
                    Status: {booking.status}
                  </p>
                </li>
              ))}
            </ul>
          ) : (
            <p className="text-gray-400 text-center">No bookings found.</p>
          )}
        </div>
      </div>
    </div>
  );
}
