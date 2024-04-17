import axios from "axios";
import React, { useEffect, useState } from "react";
import BookingCard from "../components/card/BookingCard";
import { useNavigate } from "react-router-dom";

const BookingHistory = () => {
  const navigate = useNavigate();
  const token = localStorage.getItem("token");
  const [bookingList, setBookingList] = useState([]);
  if (token === null) {
    navigate("/login");
  }

  const backend_booking_history_url = `${process.env.REACT_APP_BACKEND_URL}api/bookings/user-id`;
  useEffect(() => {
    const fetchBookings = async () => {
      try {
        const response = await axios.get(backend_booking_history_url, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        console.log(response.data.data);
        setBookingList(response.data.data);
      } catch (error) {
        console.error("Error fetching users:", error);
      }
    };

    fetchBookings();
  }, []);

  return (
    <>
      <div className="mt-20">
        <h1 className="text-primary text-2xl font-bold text-center">
          Booking History
        </h1>

        <div className="grid sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-10 m-4">
          {bookingList?.map((booking) => (
            <BookingCard
              bookingId={booking.id}
              bookingDate={booking.date}
              eventName={booking.event.name}
              eventDate={booking.event.date}
              numTickets={booking.quantity}
              totalPrice={booking.totalPrice}
            />
          ))}
        </div>
      </div>
    </>
  );
};

export default BookingHistory;
