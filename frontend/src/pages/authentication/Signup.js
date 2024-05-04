import React, { useState } from "react";
import { ToastContainer, toast } from "react-toastify";
import TextInput from "../../components/input/TextInput";
import SubmitButton from "../../components/button/SubmitButton";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import SelectInput from "../../components/input/SelectInput";
const Signup = () => {
  var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  var passwordRegex = /^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,15}$/;
  var nameRegex = /^[A-Za-z]+$/;
  const [name, setName] = useState("");
  const [businessName, setBusinessName] = useState("");
  const [mobileNumber, setMobileNumber] = useState("");
  const [email, setEmail] = useState("");
  const [gender, setGender] = useState("");
  const [age, setAge] = useState("");
  const [city, setCity] = useState("");
  const [password, setPassword] = useState("");
  const [userType, setUserType] = useState("");
  const [profileImage, setProfileImage] = useState(
    "https://cdn.pixabay.com/photo/2021/10/11/00/59/upload-6699084_1280.png"
  );

  const handleProfilePictureChange = (e) => {
    setProfileImage(e.target.files[0]);
  };
  const navigate = useNavigate();

  const callSignUp = async () => {
    if (validate()) {
      const formData = new FormData();
      formData.append("image", profileImage);

      if (userType === "User") {
        formData.append("name", name);
        formData.append("mobileNumber", mobileNumber);
        formData.append("gender", gender);
        formData.append("age", age);
        formData.append("city", city);
        formData.append("email", email);
        formData.append("password", password);
        formData.append("userType", userType);
      } else {
        formData.append("name", name);
        formData.append("mobileNumber", mobileNumber);
        formData.append("businessName", businessName);
        formData.append("email", email);
        formData.append("password", password);
        formData.append("userType", userType);
      }

      const backend_signup_url = `${process.env.REACT_APP_BACKEND_URL}api/users/signup`;

      console.log("backend url: ", backend_signup_url);

      const response = await axios.post(backend_signup_url, formData);
      console.log("response: ", response);

      if (response.data.statusMessage === "User already exists") {
        toast.error("User already exists");
      } else {
        localStorage.setItem("token", response.data.data.jwtToken);
        localStorage.setItem("userId", response.data.data.id);
        localStorage.setItem("userType", response.data.data.userType);

        response.data.data.userType === "User"
          ? navigate("/event-list")
          : navigate(`/event-organizer-analytics/id=${response.data.data.id}`);
      }
    }
  };

  const validate = () => {
    if (name === "") {
      toast.error("Name is required");
      return false;
    } else if (!nameRegex.test(name)) {
      toast.error("Name can only have alphabets");
      return false;
    } else if (!email) {
      toast.error("Email is required");
      return false;
    } else if (!emailRegex.test(email)) {
      toast.error("Email is not in valid format");
      return false;
    } else if (!userType || userType === "User Type") {
      toast.error("Select User Type");
      return false;
    } else if (!password) {
      toast.error("Password is required");
      return false;
    } else if (password.length < 7) {
      toast.error("Minimum length of password should be 8 characters.");
      return false;
    } else if (!passwordRegex.test(password)) {
      toast.error(
        <div>
          <p>Password must fullfil following conditions: </p>
          <ul>
            <li>Minimum 1 uppercase alphabet.</li>
            <li>Minimum 1 lowercase alphabet.</li>
            <li>Minimum 1 number.</li>
            <li>Minimum 1 special character (!@#$%^&*).</li>
          </ul>
        </div>
      );
      return false;
    } else if (!mobileNumber) {
      toast.error("Mobile Number is required.");
      return false;
    }

    if (userType === "User") {
      if (!city) {
        toast.error("City is required.");
        return false;
      } else if (!age) {
        toast.error("Age is required.");
        return false;
      } else if (!gender) {
        toast.error("Gender is required.");
        return false;
      }
    } else {
      if (!businessName) {
        toast.error("Business Name is required.");
        return false;
      }
    }
    return true;
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
            type="number"
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

          <div className="mb-8 h-fit rounded-lg bg-white p-8 shadow-md">
            <h2 className="mb-4 text-2xl font-bold">Profile Picture</h2>
            {profileImage && (
              <div className="mt-4">
                <img
                  src={profileImage}
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
              onChange={handleProfilePictureChange}
            />
          </div>

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
