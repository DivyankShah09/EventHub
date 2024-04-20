import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";

const PaymentFailure = () => {
  const { id } = useParams();
  const [count, setCount] = useState(10);
  const navigate = useNavigate();
  const backend_delete_booking_url = `${process.env.REACT_APP_BACKEND_URL}api/bookings?${id}`;
  const token = localStorage.getItem("token");

  useEffect(() => {
    const deleteBooking = async () => {
      try {
        const response = await axios.delete(backend_delete_booking_url, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        console.log(response);
      } catch (error) {
        console.error("Error fetching events:", error);
      }
    };

    deleteBooking();
  });

  useEffect(() => {
    const redirectTimer = setInterval(() => {
      if (count > 0) {
        setCount(count - 1);
      } else {
        clearInterval(redirectTimer);
        navigate(-3);
      }
    }, 1000);

    return () => clearInterval(redirectTimer);
  }, [count, navigate]);

  const goBack = (event) => {
    event.preventDefault();
    navigate(-3);
  };

  return (
    <div className="flex flex-col items-center justify-center h-[80vh]">
      <h1 className="text-3xl font-bold mb-4">Payment Failed!</h1>
      <p className="text-lg mb-2">Please try again after sometime.</p>
      <p className="text-lg mb-2">
        You will be redirected to Event Details page in {count} seconds.
      </p>
      <p className="text-gray-600">
        If you are not redirected,{" "}
        <a href="#" onClick={goBack} className="text-blue-500">
          click here
        </a>
        .
      </p>
    </div>
  );
};

export default PaymentFailure;
