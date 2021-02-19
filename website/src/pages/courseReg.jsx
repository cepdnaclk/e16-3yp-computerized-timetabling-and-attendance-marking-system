import React, { Component } from "react";
import NavBar from "../components/navbar";
import axios from "axios";
import CourseRegistrationForm from "../components/courseRegistrationForm";
import CourseRegistrationConfirm from "../components/courseRegistrationConfirm";
import { Alert } from "@material-ui/lab";

let ALL_COURSES_URL = "/courses/all/";
let CONFIRM_PASSWORD_URL = "/check/password/";

class CourseReg extends Component {
  state = {
    submit: false,
  };

  componentDidMount() {
    const auth = "Bearer " + localStorage.getItem("token");
    axios
      .get(ALL_COURSES_URL, {
        headers: {
          Authorization: auth,
        },
      })
      .then((response) => {
        console.log(response.data);
        this.setState({ courses: response.data });
      })
      .catch((error) => {
        console.log("error =", error);
      });
  }

  handleSubmit = (password) => {
    password = password.trim();
    // console.log('handleSUbmit called');
    // console.log("password", password);
    if(password === ''){
      alert("Password cannot be Empty.");
      return;
    }
    let url = CONFIRM_PASSWORD_URL + password;
    // console.log(url);
    const auth = "Bearer " + localStorage.getItem("token");
    axios
      .get(url, {
        headers: {
          Authorization: auth,
        },
      })
      .then((response) => {
        // console.log(response.data);
        // console.log(response.data.result);
        if (response.data.result === "True") {
          this.setState({ submit: true });
        }
        else {
          alert("Wrong Password");
        }
      })
      .catch((error) => {
        alert("Error !");
      });
  };

  unSetSubmit = () => {
    // console.log('unSetSubmit called');
    this.setState({ submit: false } ,this.checkSubmit);
  };

  checkSubmit = () => {
    // console.log('callback submit =',this.state.submit);
  };

  render() {
    return (
      <div>
        <NavBar pageName="Course Registration" />
        <CourseRegistrationForm
          courses={this.state.courses}
          check={this.state.submit}
          afterSubmit={this.unSetSubmit}
        />
        <CourseRegistrationConfirm onSubmit={this.handleSubmit} />
      </div>
    );
  }
}

export default CourseReg;
