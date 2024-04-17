import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import SubmitButton from "../components/button/SubmitButton";
import TextInput from "../components/input/TextInput";
import { ToastContainer, toast } from "react-toastify";

const EventDetails = () => {
  const { id } = useParams();
  // const backend_event_details_url = `http://ec2-18-207-178-206.compute-1.amazonaws.com/api/events?${id}`;
  const backend_event_details_url = `http://ec2-18-207-178-206.compute-1.amazonaws.com/api/events?${id}`;
  const token = localStorage.getItem("token");
  const userType = localStorage.getItem("userType");
  const [event, setEvent] = useState();
  const [noOfTickets, setNoOfTickets] = useState();

  useEffect(() => {
    const getUser = async () => {
      try {
        const response = await axios.get(backend_event_details_url, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setEvent(response.data.data);
      } catch (error) {
        console.error("Error fetching events:", error);
      }
    };

    getUser();
  }, [id]);

  const callBookEvent = async () => {
    const currentDate = new Date();

    // Get year, month, and day
    const year = currentDate.getFullYear();
    const month = String(currentDate.getMonth() + 1).padStart(2, "0"); // Month starts from 0, so add 1
    const day = String(currentDate.getDate()).padStart(2, "0");

    // Create a payment intent on the backend
    const response = await axios.post(
      `http://ec2-18-207-178-206.compute-1.amazonaws.com/api/bookings/save-booking`,
      {
        eventId: id.split("=")[1],
        amount: noOfTickets * event.price,
        date: `${year}-${month}-${day}`,
        quantity: noOfTickets,
      },
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );
    console.log(response.data.statusMessage);
    if (response.data.statusMessage === "Event booked successfully") {
      toast.success("Event Registration successful.");
      setNoOfTickets("");
    }
  };

  return (
    <>
      <ToastContainer />
      <div className="my-20 w-full px-10">
        <div className="w-full lg:w-1/2 border drop-shadow-xl mx-auto bg-[#d5e8ee] p-8 rounded-lg">
          <img
            className="h-80 w-full mb-6 mx-auto"
            src={event?.imageUrl}
            alt={event?.name}
          />

          <h1 className="text-3xl font-bold mb-4 text-center">{event?.name}</h1>
          <div>
            <p>{event?.description}</p>
          </div>

          <div className="grid grid-cols-2 gap-4 mb-4 mx-auto w-full">
            <div>
              <p>
                <b>Date: </b>
                {event?.date}
              </p>
            </div>
            <div>
              <p>
                <b>Time: </b>
                {event?.time}
              </p>
            </div>
            <div>
              <p>
                <b>Location: </b>
                {event?.location}
              </p>
            </div>
            <div>
              <p>
                <b>Price: </b>
                {event?.price}
              </p>
            </div>
          </div>
          {userType === "Event Organizer" ? (
            ""
          ) : (
            <div className="grid grid-cols-2 gap-4 mx-auto w-full">
              <TextInput
                placeholderText="Number of tickets"
                value={noOfTickets}
                onChange={(value) => setNoOfTickets(value)}
                type="number"
              />
              <SubmitButton
                className="h-12"
                buttonName="Register for Event"
                callButtonFunction={callBookEvent}
              />
            </div>
          )}
        </div>
      </div>
    </>
  );
};

export default EventDetails;
