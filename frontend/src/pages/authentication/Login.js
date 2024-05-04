import React, { useState } from "react";
import { ToastContainer, toast } from "react-toastify";
import TextInput from "../../components/input/TextInput";
import SubmitButton from "../../components/button/SubmitButton";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import SelectInput from "../../components/input/SelectInput";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [userType, setUserType] = useState("");
  const navigate = useNavigate();

  const callLogin = async () => {
    const backend_login_url = `api/users/login`;
    const userData = {
      email: email,
      password: password,
      userType: userType,
    };

    console.log("url: ", backend_login_url);

    const response = await axios.post(
      `${process.env.REACT_APP_BACKEND_URL}api/users/login`,
      userData
    );

    if (response.data.statusMessage === "Invalid user credentials") {
      toast.error("Invalid user credentials");
    } else {
      localStorage.setItem("token", response.data.data.jwtToken);
      localStorage.setItem("userId", response.data.data.id);
      localStorage.setItem("userType", response.data.data.userType);

      response.data.data.userType === "User"
        ? navigate("/event-list")
        : navigate(`/event-organizer-analytics/id=${response.data.data.id}`);
    }
  };

  const validate = () => {
    if (!email) {
      toast.error("Email is required");
      return false;
    } else if (!password) {
      toast.error("Email is required");
      return false;
    } else if (!userType || userType === "User Type") {
      toast.error("Select User Type");
      return false;
    }

    return true;
  };

  return (
    <>
      <ToastContainer />
      <div className="mt-20 text-center">
        <h1 className="text-primary text-2xl font-bold">Login</h1>
        <div className="mx-auto my-2 w-3/4 p-2 text-center md:w-1/2 lg:w-1/4">
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
          <SubmitButton buttonName="Login" callButtonFunction={callLogin} />
          <p className="cursor-pointer text-center">
            Do not have an account?{" "}
            <Link to={"/signup"}>
              <span className="cursor-pointer underline hover:text-gray-400">
                Register Here
              </span>
            </Link>
          </p>
        </div>
      </div>
    </>
  );
};

export default Login;
