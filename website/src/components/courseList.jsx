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
            key={course.courseNumber}
            code={course.courseNumber}
            name={course.courseName.toUpperCase()}
            course={course}
            sw={this.props.sw}
          ></CourseCard>
        ))}
      </div>
    );
  }
}

export default CourseList;
