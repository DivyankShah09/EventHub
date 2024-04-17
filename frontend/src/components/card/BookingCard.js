import React from "react";

const BookingCard = ({
  bookingId,
  bookingDate,
  eventName,
  eventDate,
  numTickets,
  totalPrice,
}) => {
  return (
    <div className="bg-[#d5e8ee] shadow-md rounded-md overflow-hidden w-full max-w-xl mx-auto mb-4">
      <div className="px-6 py-4">
        <div className="flex items-center mb-2">
          <span className="text-gray-600 font-bold mr-2">Booking ID:</span>
          <span className="text-gray-800">{bookingId}</span>
        </div>
        <div className="flex items-center mb-2">
          <span className="text-gray-600 font-bold mr-2">Booking Date:</span>
          <span className="text-gray-800">{bookingDate}</span>
        </div>
        <div className="flex items-center mb-2">
          <span className="text-gray-600 font-bold mr-2">Event Name:</span>
          <span className="text-gray-800">{eventName}</span>
        </div>
        <div className="flex items-center mb-2">
          <span className="text-gray-600 font-bold mr-2">Event Date:</span>
          <span className="text-gray-800">{eventDate}</span>
        </div>
        <div className="flex items-center mb-2">
          <span className="text-gray-600 font-bold mr-2">
            Number of Tickets:
          </span>
          <span className="text-gray-800">{numTickets}</span>
        </div>
        <div className="flex items-center">
          <span className="text-gray-600 font-bold mr-2">Total Price:</span>
          <span className="text-gray-800">${totalPrice}</span>
        </div>
      </div>
    </div>
  );
};

export default BookingCard;
