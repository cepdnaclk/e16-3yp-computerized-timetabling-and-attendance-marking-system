import React, { Component } from "react";
import "../css/courseRegCard.css";
import { Checkbox } from "@material-ui/core";
import { Container } from "@material-ui/core";
import axios from "axios";
import { Redirect } from "react-router";
import createHistory from 'history/createBrowserHistory'

const COURSE_REGISTRATION_URL = "/courses/registration/add";

class CourseRegistrationForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      values: {},
      first: true,
      redirect: false
    };
  }

  toggleChange = (e) => {
    if (this.state.first) {
      this.setState({ first: false });
      let initValues = {};
      this.props.courses.map((c) => {
        initValues[c.courseNumber] = false;
      });
      //   console.log('initvalues =',initValues);
      this.setState({ values: initValues });
      this.setState({ first: false });
    }
    let values = this.state.values;
    values[e.target.value] = e.target.checked;
    this.setState({ values });
    // console.log(this.state.values);
  };

  generateCourseCards = () => {
    if (this.props.courses && this.props.courses.length > 0) {
      return (
        <div>
          <Container maxWidth="sm">
            <ul>
              {this.props.courses.map((course) => (
                <li>
                  <Checkbox
                    color="primary"
                    value={course.courseId}
                    defaultChecked={this.state.checked}
                    onChange={this.toggleChange}
                  />
                  {course.courseNumber}:&nbsp;{course.courseName}
                </li>
              ))}
            </ul>
          </Container>
        </div>
      );
    } else {
      return <div></div>;
    }
  };

  render() {
    if (this.props.check) {
      // console.log("Inside Checking");
      let index = 0,
        courses = [];
      for (let key in this.state.values) {
        if (this.state.values[key]) {
          courses[index++] = key;
        }
      }
      // console.log("Submit clicked");
      // console.log("courses =", courses);
      this.props.afterSubmit();
      // console.log(this.props.check);

      const data = {
        studentId: localStorage.getItem("sid"),
        courseList: courses
      };
      const auth = "Bearer " + localStorage.getItem("token");
      axios
        .post(COURSE_REGISTRATION_URL, data, {
          headers: {
            Authorization: auth,
          }
        })
        .then((response) => {
          console.log(response.data);
          console.log(response);
          console.log('Have to go to a new url');
          this.setState({redirect : true});
        })
        .catch((error) => {
          console.log('error = ',error);
        });
        console.log('Not working');
    }

    if(this.state.redirect){
      this.setState({redirect:false});
      return <Redirect to={{ pathname: "registeredcourses" }} />;
    }

    return this.generateCourseCards();
  }
}

export default CourseRegistrationForm;
