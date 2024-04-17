import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import EventCard from "../components/card/EventCard";
import TextInput from "../components/input/TextInput";

const EventList = () => {
  const navigate = useNavigate();
  const [events, setEvents] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");
  const token = localStorage.getItem("token");
  const userType = localStorage.getItem("userType");

  if (token === null) {
    navigate("/login");
  }

  const currentDate = new Date();
  // Get year, month, and day
  const year = currentDate.getFullYear();
  const month = String(currentDate.getMonth() + 1).padStart(2, "0"); // Month starts from 0, so add 1
  const day = String(currentDate.getDate()).padStart(2, "0");

  const todayDate = `${year}-${month}-${day}`;

  const backend_all_events_url =
    userType === "Event Organizer"
      ? `http://ec2-18-207-178-206.compute-1.amazonaws.com/api/events/my-events`
      : `http://ec2-18-207-178-206.compute-1.amazonaws.com/api/events/`;

  useEffect(() => {
    const fetchEvents = async () => {
      try {
        const response = await axios.get(backend_all_events_url, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        console.log(response.data.data);
        setEvents(response.data.data);
      } catch (error) {
        console.error("Error fetching users:", error);
      }
    };

    fetchEvents();
  }, []);

  let filteredEvents = events?.filter((event) =>
    event.name.toLowerCase().includes(searchQuery.toLowerCase())
  );

  userType === "User"
    ? (filteredEvents = filteredEvents.filter(
        (event) => new Date(event.date) >= new Date(todayDate)
      ))
    : (filteredEvents = filteredEvents);

  return (
    <>
      <div className="mt-20">
        {userType === "Event Organizer" ? (
          <h1 className="text-primary text-2xl font-bold text-center">
            My Events
          </h1>
        ) : (
          <h1 className="text-primary text-2xl font-bold text-center">
            Events
          </h1>
        )}
        <TextInput
          className="m-4"
          placeholderText="Search Event by Name"
          value={searchQuery}
          onChange={(value) => setSearchQuery(value)}
          type="text"
        />

        <div className="grid sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-10 m-4">
          {filteredEvents?.map((event) => (
            <Link key={event.id} to={`/event-details/id=${event.id}`}>
              {console.log(new Date(event.date) < new Date(todayDate))}
              <EventCard
                imageUrl={event.imageUrl}
                name={event.name}
                date={event.date}
                time={event.time}
                location={event.location}
                price={event.price}
              />
            </Link>
          ))}
        </div>
      </div>
    </>
  );
};

export default EventList;
