import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";

const PaymentSuccess = () => {
  const { id } = useParams();
  const [count, setCount] = useState(10);
  const navigate = useNavigate();

  useEffect(() => {
    const redirectTimer = setInterval(() => {
      if (count > 0) {
        setCount(count - 1);
      } else {
        clearInterval(redirectTimer);
        navigate("/history");
      }
    }, 1000);

    return () => clearInterval(redirectTimer);
  }, [count, navigate]);

  return (
    <div className="flex flex-col items-center justify-center h-screen">
      <h1 className="text-3xl font-bold mb-4">Booking Success!</h1>
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
