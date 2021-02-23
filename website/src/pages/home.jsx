import React, { Component } from "react";
import NavBar from "../components/navbar";
import CourseList from "../components/courseList";
import UserCard from "../components/userCard";
import axios from "axios";
import "../css/home.css";
import bgImage from "../images/bg4.jpg";
import { Redirect } from "react-router";
const STUDENT_HOME_PAGE_URI = "/student";
let GET_COURSES_BY_SID_URL = "/courses/findcoursesbystudentid/";
class Home extends Component {
  state = {
    courses: [],
    details: [],
    searchWord: null,
    page : 'dailyattendance'
  };

  componentDidMount() {
    const auth = "Bearer " + localStorage.getItem("token");
    GET_COURSES_BY_SID_URL += localStorage.getItem("sid");
    axios
      .get(GET_COURSES_BY_SID_URL, {
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

  constructor(props) {
    super(props);
  }


  render() {
    return (
      <div className="home-outer">
        <NavBar pageName="Home" />
        <img src={bgImage} className="homeloginImg"></img>
        <h2 className="hm-title">My Attendance records</h2>
        <CourseList page={this.state.page} courses={this.state.courses} sw={this.state.searchWord} />
        <UserCard data={[localStorage.getItem("sfn"), localStorage.getItem("sen")]} />
        <a href="coursereg" className="btn btn-secondary">Course Registration</a><br/>  
        {/* <a href="registeredcourses" className="btn btn-success">Registered Courses</a> */}
      </div>
    );
  }
}

export default Home;
