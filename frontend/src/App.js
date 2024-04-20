import React from "react";
import "./App.css";
import Navbar from "./components/navbar/Navbar";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./pages/usermanagement/Login";
import Signup from "./pages/usermanagement/Signup";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import EventList from "./pages/event/EventList";
import EventDetails from "./pages/event/EventDetails";
import AddEvent from "./pages/event/AddEvent";
import BookingHistory from "./pages/booking/BookingHistory";
import Footer from "./components/footer/Footer";
import CustomerProfile from "./pages/usermanagement/CustomerProfile";
import EventOrganizerProfile from "./pages/usermanagement/EventOrganizerProfile";
import "react-datepicker/dist/react-datepicker.css";

function App() {
  return (
    <>
      <Router>
        <ToastContainer />
        <Navbar />

        <Routes>
          <Route exact path="/" element={<EventList />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/event-list" element={<EventList />} />
          <Route path="/my-events" element={<EventList />} />
          <Route path="/event-details/:id" element={<EventDetails />} />
          <Route path="/add-event" element={<AddEvent />} />
          <Route path="/edit-event/:id" element={<AddEvent />} />
          <Route path="/history" element={<BookingHistory />} />
          <Route path="/customer-profile/:id" element={<CustomerProfile />} />
          <Route
            path="/event-organizer-profile/:id"
            element={<EventOrganizerProfile />}
          />
        </Routes>
        <Footer />
      </Router>
    </>
  );
}

export default App;
