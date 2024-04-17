import { useState } from "react";
import SubmitButton from "../components/button/SubmitButton";
import TextInput from "../components/input/TextInput";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import axios from "axios";

const AddEvent = () => {
  const navigate = useNavigate();
  const token = localStorage.getItem("token");
  const [name, setName] = useState();
  const [description, setDescription] = useState();
  const [date, setDate] = useState();
  const [time, setTime] = useState();
  const [location, setLocation] = useState();
  const [price, setPrice] = useState();
  const [eventImage, setEventImage] = useState(
    "https://cdn.pixabay.com/photo/2021/10/11/00/59/upload-6699084_1280.png"
  );

  const handleEventPictureChange = (e) => {
    setEventImage(e.target.files[0]);
  };

  const callAddEvent = async () => {
    if (token === null) {
      navigate("/login");
      return null;
    }
    const formData = new FormData();
    console.log("FormData: ", formData);
    formData.append("image", eventImage);
    formData.append("name", name);
    formData.append("description", description);
    formData.append("date", date);
    formData.append("time", time);
    formData.append("location", location);
    formData.append("price", price);

    // const backend_add_events_url = `http://ec2-18-207-178-206.compute-1.amazonaws.com/api/events/save-event`;
    const backend_add_events_url =
      "http://ec2-18-207-178-206.compute-1.amazonaws.com/api/events/save-event";

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
          <TextInput
            placeholderText="Date (in \'YYYY-MM-DD\' format)"
            value={date}
            onChange={(value) => setDate(value)}
            type="text"
          />
          <TextInput
            placeholderText="Time (in \'HH:MM:SS\' format)"
            value={time}
            onChange={(value) => setTime(value)}
            type="text"
          />
          <TextInput
            placeholderText="Location "
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
            buttonName="Add Event"
            callButtonFunction={callAddEvent}
          />
        </div>
      </div>
    </>
  );
};

export default AddEvent;
