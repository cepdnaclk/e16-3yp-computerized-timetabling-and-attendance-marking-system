import React, { Component } from "react";
import NavBar from "../components/navbar";
import CourseList from "../components/courseList";
import LecturerCard from "../components/lecturerCard";
import bgImage from "../images/bg4.jpg";
import axios from "axios";
import "../css/home.css";
import "../css/lecturerDashboard.css";

const LECT_ALL_COURSES_URL = '/lec/find/allcourses';


class LecturerDashboard extends Component {
  state = {
    courses: [],
    details: [],
    searchWord: null,
    page : "attendance"
  };

  componentDidMount() {
    let name = localStorage.getItem('lfn')+' '+localStorage.getItem('lln');
    this.setState({lec_name : name});
    const auth = "Bearer " + localStorage.getItem("token");

    axios
      .get(LECT_ALL_COURSES_URL, {
        headers: {
          Authorization: auth,
        },
      })
      .then((response) => {
        console.log(response);
        this.setState({ courses: response.data });
      })
      .catch((error) => {
        console.log("error =", error);
      });

    this.setState({ searchWord: "" });
  }

  onSerchValueChanged = (e) => {
    this.setState({ searchWord: e.target.value });
  };
  render() {
    return (
      <div>
        <NavBar pageName="Lecturer Dashboard" />
        <img src={bgImage} className="homeloginImg"></img>
        <h2 className="hm-title lc-title">Student Attendance</h2>
        <CourseList page={this.state.page} courses={this.state.courses} sw={this.state.searchWord} />
        <LecturerCard data={this.state.lec_name} oc={this.onSerchValueChanged} />
      </div>
    );
  }
}

export default LecturerDashboard;
