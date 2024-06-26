import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import TextInput from "../../components/input/TextInput";
import SubmitButton from "../../components/button/SubmitButton";
import { ToastContainer, toast } from "react-toastify";

const EventOrganizerProfile = () => {
  const { id } = useParams();
  const token = localStorage.getItem("token");
  const [profilePicture, setProfilePicture] = useState(
    "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png"
  );
  const [name, setName] = useState();
  const [businessName, setBusinessName] = useState();
  const [mobileNumber, setMobileNumber] = useState();
  const [email, setEmail] = useState();
  const backend_event_organizer_profile_details_url = `${process.env.REACT_APP_BACKEND_URL}api/event-organizer-profile?${id}`;
  const backend_event_organizer_profile_update_details = `${process.env.REACT_APP_BACKEND_URL}api/event-organizer-profile/update`;

  const handleProfilePictureChange = (e) => {
    setProfilePicture(e.target.files[0]);
  };

  useEffect(() => {
    const getEventOrganizersDetails = async () => {
      try {
        const response = await axios.get(
          backend_event_organizer_profile_details_url,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        console.log(response);

        setName(response.data.data.name);
        setMobileNumber(response.data.data.mobileNumber);
        setBusinessName(response.data.data.businessName);
        setEmail(response.data.data.email);
        setProfilePicture(response.data.data.profilePictureUrl);
      } catch (error) {
        console.error("Error fetching events:", error);
      }
    };

    getEventOrganizersDetails();
  }, [id]);

  const callUpdateProfile = async () => {
    const formData = new FormData();
    formData.append("id", id.split("=")[1]);
    if (typeof profilePicture !== "string") {
      formData.append("profilePicture", profilePicture);
    }
    formData.append("name", name);
    formData.append("email", email);
    formData.append("mobileNumber", mobileNumber);
    formData.append("businessName", businessName);

    const response = await axios.post(
      backend_event_organizer_profile_update_details,
      formData,
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );
    console.log("response: ", response);
    setName(response.data.data.name);
    setBusinessName(response.data.data.businessName);
    setMobileNumber(response.data.data.mobileNumber);
    setEmail(response.data.data.email);
    setProfilePicture(response.data.data.profilePictureUrl);
    toast.success("Profile Updated Successfully");
  };
  return (
    <>
      <ToastContainer />
      <div className="mt-16 flex flex-col p-8 sm:flex sm:flex-row">
        <div className="mb-8 h-fit rounded-lg bg-white p-8 shadow-md">
          <h2 className="mb-4 text-2xl font-bold">Profile Picture</h2>
          {profilePicture && (
            <div className="mt-4">
              <img
                src={profilePicture}
                onChange={(e) => setProfilePicture(e.target.value)}
                alt="Profile"
                className="mx-auto h-[200px] w-[200px] rounded-[6.5rem] object-cover"
              />
            </div>
          )}
          <input
            type="file"
            id="profile_picture"
            name="profile_picture"
            accept="image/*"
            className="mt-2 block w-full"
            onChange={handleProfilePictureChange}
          />
        </div>
        <div className="mx-auto w-full rounded-md bg-white p-8 shadow-md sm:mx-10">
          <h2 className="mb-4 text-2xl font-bold">Update your profile</h2>

          <div className="grid grid-cols-1 gap-x-8 gap-y-4 md:grid-cols-2">
            <div className="flex flex-col">
              <label htmlFor="name" className="block font-medium">
                Name
              </label>
              <TextInput
                placeholderText="Name"
                value={name}
                onChange={(value) => setName(value)}
                type="text"
              />
            </div>
            <div className="flex flex-col">
              <label htmlFor="businessName" className="block font-medium">
                Business Name
              </label>
              <TextInput
                placeholderText="Name"
                value={businessName}
                onChange={(value) => setBusinessName(value)}
                type="text"
              />
            </div>
            <div className="flex flex-col">
              <label htmlFor="email" className="block font-medium">
                Email
              </label>
              <TextInput
                placeholderText="Email"
                value={email}
                onChange={(value) => setEmail(value)}
                type="email"
                disabled={true}
              />
            </div>
            <div className="flex flex-col">
              <label htmlFor="mobileNumber" className="block font-medium">
                Mobile Number
              </label>
              <TextInput
                placeholderText="Mobile Number"
                value={mobileNumber}
                onChange={(value) => setMobileNumber(value)}
                type="text"
              />
            </div>

            <SubmitButton
              buttonName="Save"
              callButtonFunction={callUpdateProfile}
            />
          </div>
        </div>
      </div>
    </>
  );
};

export default EventOrganizerProfile;
