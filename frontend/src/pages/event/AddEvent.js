import { useState, useEffect } from "react";
import SubmitButton from "../../components/button/SubmitButton";
import { useParams } from "react-router-dom";
import TextInput from "../../components/input/TextInput";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import axios from "axios";
import DatePickerInput from "../../components/input/DatePickerInput";
import TimePickerInput from "../../components/input/TimePickerInput";

const AddEvent = () => {
  const { id } = useParams();
  console.log(id);
  const navigate = useNavigate();
  const token = localStorage.getItem("token");
  const [name, setName] = useState();
  const [description, setDescription] = useState();
  const [date, setDate] = useState(new Date());
  const [time, setTime] = useState("12:00");
  const [location, setLocation] = useState();
  const [price, setPrice] = useState();
  const [eventImage, setEventImage] = useState(
    "https://cdn.pixabay.com/photo/2021/10/11/00/59/upload-6699084_1280.png"
  );
  const backend_event_details_url = `${process.env.REACT_APP_BACKEND_URL}api/events?${id}`;

  const handleEventPictureChange = (e) => {
    setEventImage(e.target.files[0]);
  };

  let event;
  useEffect(() => {
    const getEvent = async () => {
      try {
        const response = await axios.get(backend_event_details_url, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        console.log(response);
        event = response.data.data;
        setName(event.name);
        setDescription(event.description);
        setDate(event.date);
        setTime(event.time);
        setLocation(event.location);
        setPrice(event.price);
      } catch (error) {
        console.error("Error fetching events:", error);
      }
    };

    getEvent();
  }, [id]);

  const callAddEvent = async () => {
    if (token === null) {
      navigate("/login");
      return null;
    }
    const formData = new FormData();
    console.log("FormData: ", formData);
    if (id) {
      formData.append("id", id.split("=")[1]);
      if (typeof eventImage !== "string") {
        formData.append("image", eventImage);
      }
    } else {
      formData.append("image", eventImage);
    }
    formData.append("name", name);
    formData.append("description", description);
    formData.append("date", date);
    formData.append("time", time);
    formData.append("location", location);
    formData.append("price", price);

    const backend_add_events_url = `${process.env.REACT_APP_BACKEND_URL}api/events/save-event`;

    const response = await axios.post(backend_add_events_url, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
        Authorization: `Bearer ${token}`,
      },
    });
    console.log(response.data);
    if (response.data.statusMessage === "Event added successfully") {
      toast.success("Event added successfully.");
      navigate("/my-events");
    } else {
      toast.error("Event not added.");
    }
  };

  return (
    <>
      <ToastContainer />
      <div className="mt-20">
        <h1 className="text-primary text-2xl font-bold text-center">
          Add Event
        </h1>
        <div className="mx-auto my-2 w-3/4 p-2 text-center md:w-1/2 lg:w-1/4">
          <TextInput
            placeholderText="Event Name"
            value={name}
            onChange={(value) => setName(value)}
            type="text"
          />
          <TextInput
            placeholderText="Description"
            value={description}
            onChange={(value) => setDescription(value)}
            type="text"
          />

          <DatePickerInput value={date} onChange={(value) => setDate(value)} />

          <TimePickerInput value={time} onChange={(value) => setTime(value)} />

          <TextInput
            placeholderText="Location"
            value={location}
            onChange={(value) => setLocation(value)}
            type="text"
          />
          <TextInput
            placeholderText="Price"
            value={price}
            onChange={(value) => setPrice(value)}
            type="text"
          />
          <div className="mb-8 h-fit rounded-lg bg-white p-8 shadow-md">
            <h2 className="mb-4 text-2xl font-bold">Event Image</h2>
            {eventImage && (
              <div className="mt-4">
                <img
                  src={eventImage}
                  alt="Profile"
                  className="mx-auto object-cover"
                />
              </div>
            )}
            <input
              type="file"
              id="profile_picture"
              name="profile_picture"
              accept="image/*"
              className="mt-2 block w-full"
              onChange={handleEventPictureChange}
            />
          </div>
          <SubmitButton
            buttonName={id ? "Edit Event" : "Add Event"}
            callButtonFunction={callAddEvent}
          />
        </div>
      </div>
    </>
  );
};

export default AddEvent;
