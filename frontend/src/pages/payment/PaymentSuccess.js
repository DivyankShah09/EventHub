import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";

const PaymentSuccess = () => {
  const { id } = useParams();
  const [count, setCount] = useState(10);
  const navigate = useNavigate();
  const token = localStorage.getItem("token");
  const backend_save_payment_url = `${process.env.REACT_APP_BACKEND_URL}api/payments/save-payment?booking-id=${id.split("=")[1]}`;

  const savePayment = async () => {
    try {
      const response = await axios.post(
        backend_save_payment_url,
        {},
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      console.log(response);
    } catch (error) {
      console.error("Error fetching events:", error);
    }
  };

  useEffect(() => {
    const redirectTimer = setInterval(() => {
      if (count > 0) {
        setCount(count - 1);
      } else {
        savePayment();
        clearInterval(redirectTimer);
        navigate("/history");
      }
    }, 1000);

    return () => clearInterval(redirectTimer);
  }, [count, navigate]);

  return (
    <div className="flex flex-col items-center justify-center h-[80vh]">
      <h1 className="text-3xl font-bold mb-4">Payment successful!</h1>
      <p className="text-lg mb-2">Your booking id is {id.split("=")[1]}</p>
      <p className="text-lg mb-2">
        You will be redirected to booking history in {count} seconds.
      </p>
      <p className="text-gray-600">
        If you are not redirected,{" "}
        <a href="/booking-history" className="text-blue-500">
          click here
        </a>
        .
      </p>
    </div>
  );
};

export default PaymentSuccess;
