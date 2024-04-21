import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import BarChart from "../../components/chart/BarChart";

const EventOrganizerAnalytics = () => {
  const { id } = useParams();
  const token = localStorage.getItem("token");
  const [totalRevenue, setTotalRevenue] = useState();
  const [totalTickets, setTotalTickets] = useState();
  const [totalEvents, setTotalEvents] = useState();
  const [eventNames, setEventNames] = useState([]);
  const [eventTickets, setEventTickets] = useState([]);
  const [eventRevenue, setEventRevenue] = useState([]);
  const backend_overall_data_url = `${process.env.REACT_APP_BACKEND_URL}api/analytics?event-organizer-id=${id.split("=")[1]}`;
  const backend_event_data_url = `${process.env.REACT_APP_BACKEND_URL}api/analytics/summaries?event-organizer-id=${id.split("=")[1]}`;

  useEffect(() => {
    const getOverallAnalyticsData = async () => {
      try {
        const response = await axios.get(backend_overall_data_url, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        console.log(response);
        setTotalEvents(response.data.data.totalEvents);
        setTotalTickets(response.data.data.totalTickets);
        setTotalRevenue(response.data.data.totalRevenue);
      } catch (error) {
        console.error("Error fetching events:", error);
      }
    };
    getOverallAnalyticsData();

    console.log("event data........................");
    const getEventAnalyticsData = async () => {
      try {
        const response = await axios.get(backend_event_data_url, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        const eventData = response.data.data;
        const names = eventData.map((event) => event.eventName);
        const tickets = eventData.map((event) => event.eventTotalTickets);
        const revenue = eventData.map((event) => event.eventTotalRevenue);
        setEventNames(names);
        setEventTickets(tickets);
        setEventRevenue(revenue);
      } catch (error) {
        console.error("Error fetching events:", error);
      }
    };
    getEventAnalyticsData();
  }, [token]);

  return (
    <>
      <div className="mt-20">
        <h1 className="text-primary text-2xl font-bold text-center">
          Dashboard
        </h1>
        <div className="flex flex-col sm:flex-row gap-5 p-5">
          <div className="w-full bg-white rounded-md p-8 shadow-md text-center text-xl">
            <div className="text-gray-600">Total Events</div>
            <div className="text-5xl mt-3">{totalEvents}</div>
          </div>
          <div className="w-full bg-white rounded-md p-8 shadow-md text-center text-xl">
            <div className="text-gray-600">Total Tickets</div>
            <div className="text-5xl mt-3">{totalTickets}</div>
          </div>
          <div className="w-full bg-white rounded-md p-8 shadow-md text-center text-xl">
            <div className="text-gray-600">Total Revenue</div>
            <div className="text-5xl mt-3">{totalRevenue}</div>
          </div>
        </div>

        <div className="flex flex-col sm:flex-row gap-5 p-5">
          <div className="w-full md:w-1/2 bg-white rounded-md p-8 shadow-md text-center text-xl">
            <BarChart
              dataValues={eventTickets}
              labels={eventNames}
              title="Tickets vs Events"
            />
          </div>
          <div className="w-full md:w-1/2 bg-white rounded-md p-8 shadow-md text-center text-xl">
            <BarChart
              dataValues={eventRevenue}
              labels={eventNames}
              title="Revenue vs Events"
            />
          </div>
        </div>
      </div>
    </>
  );
};

export default EventOrganizerAnalytics;
