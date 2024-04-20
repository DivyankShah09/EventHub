import React, { useState, useEffect } from "react";
import background from "../../assets/background.jpg";
import { useNavigate } from "react-router-dom";
import EventCard from "../../components/card/EventCard";
import axios from "axios";

const Home = () => {
  const navigate = useNavigate();
  const [event, setEvents] = useState([]);
  const backend_all_events_url = `${process.env.REACT_APP_BACKEND_URL}api/events/`;

  const currentDate = new Date();
  // Get year, month, and day
  const year = currentDate.getFullYear();
  const month = String(currentDate.getMonth() + 1).padStart(2, "0"); // Month starts from 0, so add 1
  const day = String(currentDate.getDate()).padStart(2, "0");

  const todayDate = `${year}-${month}-${day}`;

  const callButtonFunction = () => {
    navigate("/event-list");
  };

  useEffect(() => {
    const fetchEvents = async () => {
      try {
        const response = await axios.get(backend_all_events_url);
        console.log(response.data.data);

        const filteredEvents = response.data.data.filter(
          (event) => new Date(event.date) >= new Date(todayDate)
        );

        setEvents(filteredEvents);
      } catch (error) {
        console.error("Error fetching users:", error);
      }
    };

    fetchEvents();
  }, []);

  return (
    <>
      <section
        className="relative h-[80vh] bg-cover bg-center flex justify-center items-center text-center mt-16"
        style={{
          backgroundImage: `linear-gradient(rgba(0, 0, 0, 0.2), rgba(0, 0, 0, 0.8)), url(${background})`,
        }}
      >
        <div class="mx-auto max-w-7xl items-center px-8 py-12 md:px-12 lg:px-16 lg:py-24">
          <div class="max-auto w-full justify-center text-center lg:p-10">
            <span class="mt-8 text-5xl font-medium tracking-tighter text-white">
              <b>EventHub</b>
            </span>
            <br />
            <br />
            <span class="mx-auto mt-2 max-w-xl pt-20 text-3xl tracking-tight text-white">
              Where Every Event Finds Its Perfect Moment
            </span>
            <br />
            <button
              className=" my-2 text-1xl z-5  rounded-lg bg-[#006d77] px-8 py-2 text-white shadow-md hover:bg-[#fafafa] hover:text-black disabled:bg-blue-400"
              onClick={callButtonFunction}
            >
              Find Events
            </button>
          </div>
        </div>
      </section>
      <div className="m-10">
        <h1 className="text-primary text-2xl font-bold text-center">
          New Events
        </h1>
        <div className="grid sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-10 m-4">
          {event?.slice(0, 8).map((event) => (
            <div>
              <EventCard
                id={event.id}
                imageUrl={event.imageUrl}
                name={event.name}
                date={event.date}
                time={event.time}
                location={event.location}
                price={event.price}
                link={`/event-details/id=${event.id}`}
              />
            </div>
          ))}
        </div>
      </div>
    </>
  );
};

export default Home;
