import React, { Component } from "react";
import NavBar from "../components/navbar";
import CourseList from "../components/courseList";
import LecturerCard from "../components/lecturerCard";
import bgImage from "../images/bg4.jpg";
import axios from "axios";
import "../css/home.css";
import "../css/lecturerDashboard.css";

const LECT_ALL_COURSES_URL = "/lec/find/allcourses";
const FIND_LEC_SCHEDULE_URL = "/schedule/findscheduledetailsbylecturer/";

class LecturerDashboard extends Component {
  state = {
    courses: [],
    details: [],
    searchWord: null,
    page: "attendance",
    timeTable: [],
    loading1: false,
    loading2: false,
  };


  componentDidMount() {
    const auth = "Bearer " + localStorage.getItem("token");

    axios
      .get(LECT_ALL_COURSES_URL, {
        headers: {
          Authorization: auth,
        },
      })
      .then((response) => {
        // console.log(response);
        localStorage.setItem(
          "lecCourses",
          JSON.stringify({ courses: response.data })
        );
        this.setState({ courses: response.data }, () => {
          this.setState({ loading1: true });
        });
      })
      .catch((error) => {
        console.log("error =", error);
      });

    this.setState({ searchWord: "" });

    let lec_tt_url = FIND_LEC_SCHEDULE_URL + localStorage.getItem("lid");
    axios
      .get(lec_tt_url, {
        headers: {
          Authorization: auth,
        },
      })
      .then((response) => {
        // console.log("timetable response data = ", response);
        localStorage.setItem("timeTable", JSON.stringify(response.data));
        this.setState({ timeTable: response.data }, () => {
          this.setState({ loading2: true });
        });
      })
      .catch((error) => {
        console.log("error =", error);
      });
  }

  onSerchValueChanged = (e) => {
    this.setState({ searchWord: e.target.value });
  };
  render() {
    if (this.state.loading1 && this.state.loading2) {
      return (
        <div>
          <NavBar pageName="Lecturer Dashboard" />
          <img src={bgImage} className="homeloginImg"></img>
          <h2 className="hm-title lc-title">Student Attendance</h2>
          <CourseList
            page={this.state.page}
            courses={this.state.courses}
            sw={this.state.searchWord}
          />
          <LecturerCard
            data={
              localStorage.getItem("lfn") + " " + localStorage.getItem("lln")
            }
            courses={this.state.courses}
            oc={this.onSerchValueChanged}
          />
        </div>
      );
    }
    return <div></div>;
  }
}

export default LecturerDashboard;
