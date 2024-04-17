import React, { useState } from "react";
import { ToastContainer, toast } from "react-toastify";
import TextInput from "../components/input/TextInput";
import SubmitButton from "../components/button/SubmitButton";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import SelectInput from "../components/input/SelectInput";
const Signup = () => {
  const [name, setName] = useState("");
  const [businessName, setBusinessName] = useState("");
  const [mobileNumber, setMobileNumber] = useState("");
  const [email, setEmail] = useState("");
  const [gender, setGender] = useState("");
  const [age, setAge] = useState("");
  const [city, setCity] = useState("");
  const [password, setPassword] = useState("");
  const [userType, setUserType] = useState("");
  const navigate = useNavigate();

  const callSignUp = async () => {
    let userData;
    if (userType === "User") {
      userData = {
        name: name,
        mobileNumber: mobileNumber,
        gender: gender,
        age: age,
        city: city,
        email: email,
        password: password,
        userType: userType,
      };
    } else {
      userData = {
        name: name,
        mobileNumber: mobileNumber,
        businessName: businessName,
        email: email,
        password: password,
        userType: userType,
      };
    }

    const backend_signup_url = `http://ec2-18-207-178-206.compute-1.amazonaws.com/api/users/signup`;
    const response = await axios.post(backend_signup_url, userData);

    if (response.data.statusMessage === "User already exists") {
      toast.error("User already exists");
    } else {
      localStorage.setItem("token", response.data.data.jwtToken);
      localStorage.setItem("userId", response.data.data.id);
      localStorage.setItem("userType", response.data.data.userType);

      response.data.data.userType === "User"
        ? navigate("/event-list")
        : navigate("/my-events");
    }
  };

  return (
    <>
      <ToastContainer />
      <div className="mt-20 text-center">
        <h1 className="text-primary text-2xl font-bold">Sign Up</h1>
        <div className="mx-auto my-2 w-3/4 p-2 text-center md:w-1/2 lg:w-1/4">
          <TextInput
            placeholderText="Name"
            value={name}
            onChange={(value) => setName(value)}
            type="text"
          />
          <TextInput
            placeholderText="Email"
            value={email}
            onChange={(value) => setEmail(value)}
            type="email"
          />
          <TextInput
            placeholderText="Password"
            value={password}
            onChange={(value) => setPassword(value)}
            type="password"
          />
          <SelectInput
            value={userType}
            onChange={(value) => setUserType(value)}
          />

          <TextInput
            placeholderText="Mobile Number"
            value={mobileNumber}
            onChange={(value) => setMobileNumber(value)}
            type="text"
          />

          {userType === "Event Organizer" ? (
            <TextInput
              placeholderText="Business Name"
              value={businessName}
              onChange={(value) => setBusinessName(value)}
              type="text"
            />
          ) : (
            <>
              <TextInput
                placeholderText="Age"
                value={age}
                onChange={(value) => setAge(value)}
                type="number"
              />

              <TextInput
                placeholderText="Gender"
                value={gender}
                onChange={(value) => setGender(value)}
                type="text"
              />

              <TextInput
                placeholderText="City"
                value={city}
                onChange={(value) => setCity(value)}
                type="text"
              />
            </>
          )}

          <SubmitButton buttonName="Signup" callButtonFunction={callSignUp} />
          <p className="cursor-pointer text-center">
            Already ave an account?{" "}
            <Link to={"/login"}>
              <span className="cursor-pointer underline hover:text-gray-400">
                Login Here
              </span>
            </Link>
          </p>
        </div>
      </div>
    </>
  );
};

export default Signup;
