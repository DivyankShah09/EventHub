import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import SubmitButton from "../../components/button/SubmitButton";
import TextInput from "../../components/input/TextInput";
import { ToastContainer, toast } from "react-toastify";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faCalendar,
  faClockFour,
  faEnvelope,
  faLocationDot,
  faMoneyBill1,
  faPhone,
} from "@fortawesome/free-solid-svg-icons";

const EventDetails = () => {
  const { id } = useParams();
  const backend_event_details_url = `${process.env.REACT_APP_BACKEND_URL}api/events?${id}`;
  const token = localStorage.getItem("token");
  const userType = localStorage.getItem("userType");
  const [event, setEvent] = useState();
  const [noOfTickets, setNoOfTickets] = useState();

  useEffect(() => {
    const getEvent = async () => {
      try {
        const response = await axios.get(backend_event_details_url, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        // console.log(response);
        setEvent(response.data.data);
      } catch (error) {
        console.error("Error fetching events:", error);
      }
    };

    getEvent();
  }, [id]);

  const callBookEvent = async () => {
    const currentDate = new Date();

    // Get year, month, and day
    const year = currentDate.getFullYear();
    const month = String(currentDate.getMonth() + 1).padStart(2, "0"); // Month starts from 0, so add 1
    const day = String(currentDate.getDate()).padStart(2, "0");

    const backend_payment_url = `${process.env.REACT_APP_BACKEND_URL}api/payments/save-payment`;
    const backend_booking_url = `${process.env.REACT_APP_BACKEND_URL}api/bookings/save-booking`;

    // Create a payment intent on the backend
    const response = await axios.post(
      backend_booking_url,
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
    const bookingData = response.data.data;
    console.log("bd: ", bookingData);

    const paymentData = {
      bookingId: bookingData.id,
      user: bookingData.user,
      event: bookingData.event,
      date: bookingData.date,
      quantity: bookingData.quantity,
      totalPrice: bookingData.totalPrice,
    };
    const paymentResponse = await axios.post(backend_payment_url, paymentData, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    const paymentUrl = paymentResponse.data.data.paymentUrl;
    window.location.href = paymentUrl;

    console.log(paymentResponse);

    if (response.data.statusMessage === "Event booked successfully") {
      toast.success("Event Registration successful.");
      setNoOfTickets("");
    }
  };

  const formatDate = (dateString) => {
    const weekdays = [
      "Sunday",
      "Monday",
      "Tuesday",
      "Wednesday",
      "Thursday",
      "Friday",
      "Saturday",
    ];
    const months = [
      "January",
      "February",
      "March",
      "April",
      "May",
      "June",
      "July",
      "August",
      "September",
      "October",
      "November",
      "December",
    ];
    const date = new Date(dateString);
    const weekday = weekdays[date.getDay()];
    const month = months[date.getMonth()];
    const day = date.getDate();
    return `${weekday}, ${month} ${day}`;
  };

  const formatTime = (timeString = "00:00") => {
    const [hours, minutes, seconds] = timeString.split(":");

    // Convert hours to 12-hour format
    let formattedHours = parseInt(hours, 10);
    const amPm = formattedHours >= 12 ? "PM" : "AM";
    formattedHours = formattedHours % 12 || 12;

    // Return the formatted time string
    return `${formattedHours}:${minutes} ${amPm}`;
  };

  return (
    <>
      <ToastContainer />
      <div className="my-20 w-full px-10">
        <div className="w-full mx-auto p-8 rounded-lg">
          <img
            className="h-[60vh] mb-6 mx-auto"
            src={event?.imageUrl}
            alt={event?.name}
          />

          <div>
            <p>{formatDate(event?.date)}</p>
          </div>
          <h1 className="text-4xl font-bold mb-4">{event?.name}</h1>

          <div className="grid md:grid-cols-2">
            <div>
              <div className="mt-5">
                <h2 className="text-2xl font-bold mb-2">Date and time</h2>
                <ul>
                  <li>
                    <FontAwesomeIcon icon={faCalendar} />{" "}
                    {formatDate(event?.date) +
                      ", " +
                      new Date(event?.date).getFullYear()}
                  </li>
                  <li>
                    <FontAwesomeIcon icon={faClockFour} />{" "}
                    {formatTime(event?.time)}
                  </li>
                </ul>
              </div>

              <div className="mt-5">
                <h2 className="text-2xl font-bold mb-2">Location</h2>
                <p>
                  <FontAwesomeIcon icon={faLocationDot} /> {event?.location}
                </p>
              </div>

              <div className="mt-5">
                <h2 className="text-2xl font-bold mb-2">Price</h2>
                <p>
                  <FontAwesomeIcon icon={faMoneyBill1} /> {"$" + event?.price}
                </p>
              </div>
            </div>
            <div>
              {userType === "Event Organizer" ? (
                ""
              ) : (
                <div className="mt-5">
                  <h2 className="text-2xl font-bold mb-2">
                    Register For Event
                  </h2>
                  <div className="w-full md:w-1/2">
                    <TextInput
                      placeholderText="Number of tickets"
                      value={noOfTickets}
                      onChange={(value) => setNoOfTickets(value)}
                      type="number"
                    />
                    <SubmitButton
                      className="h-min-12"
                      buttonName="Register for Event"
                      callButtonFunction={callBookEvent}
                    />
                  </div>
                </div>
              )}
            </div>
          </div>

          <div className="mt-5">
            <h2 className="text-2xl font-bold mb-2">About this event</h2>
            <p>{event?.description}</p>
          </div>

          <div className="mt-5">
            <h2 className="text-2xl font-bold mb-2">Organized By</h2>
            <div className="bg-[#d5e8ee] w-fit flex p-3 rounded-2xl shadow-sm">
              <div className="flex-1 p-2">
                <img
                  src={event?.eventOrganizer.profilePictureUrl}
                  alt="Profile"
                  className="mx-auto h-[80px] w-[80px] rounded-[6.5rem] object-cover"
                />
              </div>
              <div className="p-2">
                <h3 className="text-xl font-bold mb-1">
                  {event?.eventOrganizer.businessName}
                </h3>
                <p>
                  <FontAwesomeIcon icon={faPhone} />{" "}
                  {event?.eventOrganizer.mobileNumber}
                </p>
                <p>
                  <FontAwesomeIcon icon={faEnvelope} />{" "}
                  {event?.eventOrganizer.email}
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default EventDetails;
