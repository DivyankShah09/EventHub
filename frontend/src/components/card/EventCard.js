import React from "react";
import SubmitButton from "../button/SubmitButton";

const EventCard = ({ imageUrl, name, date, time, location, price }) => {
  const callButtonFunction = () => {};
  return (
    <>
      <div className="bg-[#d5e8ee] max-w-sm border-2 rounded-xl overflow-hidden shadow-md">
        <img className="w-full h-52" src={imageUrl} alt={name} />
        <div className="px-6 py-4">
          <div className="font-bold text-xl mb-2">{name}</div>
          <p className="text-gray-700 text-base mb-2">Date: {date}</p>
          <p className="text-gray-700 text-base mb-2">Time: {time}</p>
          <p className="text-gray-700 text-base mb-2">Location: {location}</p>
          <p className="text-gray-700 text-base mb-2">Price: {price}</p>
          <SubmitButton
            buttonName="View Details"
            callButtonFunction={callButtonFunction}
          />
        </div>
      </div>
    </>
  );
};

export default EventCard;
