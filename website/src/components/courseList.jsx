import React, { Component } from "react";
import CourseCard from "./courseCard";
import "../css/courseCard.css";

class CourseList extends Component {
  state = {};

  constructor(props){
    super(props);
  }

  

  render() {
    return (
      <div className="home-courseList">
        {this.props.courses.map((course) => (
          <CourseCard
            key={course.courseNumber}
            code={course.courseNumber}
            name={course.courseName.toUpperCase()}
            course={course}
            page={this.props.page}
            sw={this.props.sw}
          ></CourseCard>
        ))}
      </div>
    );
  }
}

export default CourseList;
