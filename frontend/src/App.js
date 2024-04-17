import React from "react";
import "./App.css";
import Navbar from "./components/navbar/Navbar";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import EventList from "./pages/EventList";
import EventDetails from "./pages/EventDetails";
import AddEvent from "./pages/AddEvent";
import BookingHistory from "./pages/BookingHistory";
import Footer from "./components/footer/Footer";

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
          <Route path="/history" element={<BookingHistory />} />
        </Routes>
        <Footer />
      </Router>
    </>
  );
}

export default App;
