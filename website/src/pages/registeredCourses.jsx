import React, { Component } from "react";
import NavBar from "../components/navbar";
import axios from "axios";
import CourseItem from "../components/courseItem";
import ListGroup from "react-bootstrap/ListGroup";

let GET_COURSES_BY_SID_URL = "/courses/findcoursesbystudentid/";

class RegisteredCourses extends Component {
  state = {
    courses: [],
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
  }

  render() {
    return (
      <div>
        <NavBar pageName="Registered Courses" />
        <div class="container">
          <h1>Registered Courses</h1>
          <ListGroup>
            {this.state.courses.map((course) => (
              <CourseItem value={course.courseId} c={course} />
            ))}
          </ListGroup>
        </div>
      </div>
    );
  }
}

export default RegisteredCourses;
