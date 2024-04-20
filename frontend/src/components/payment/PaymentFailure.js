import React from "react";
import { useParams } from "react-router-dom";

const PaymentFailure = () => {
  const { id } = useParams();
  return (
    <>
      <p className="mt-20">Payment failed</p>
    </>
  );
};

export default PaymentFailure;
