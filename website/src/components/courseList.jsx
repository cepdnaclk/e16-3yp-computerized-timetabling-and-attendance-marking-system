import React, { Component } from "react";
import CourseCard from "./courseCard";
import "../css/courseCard.css";

class CourseList extends Component {
  state = {};

  render() {
    return (
      <div className="home-courseList">
        {this.props.courses.map((course) => (
          <CourseCard
            key={course.courseNum}
            code={course.courseNum}
            name={course.courseName}
            sw={this.props.sw}
          ></CourseCard>
        ))}
      </div>
    );
  }
}

export default CourseList;
