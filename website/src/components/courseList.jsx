import React, { Component } from 'react';
import CourseCard from "./courseCard"
import "../css/courseCard.css"

class CourseList extends Component {
    state = { }

    render() { 
        return (  
            <div className="courseList">
                {this.props.courses.map( course => <CourseCard key={course.courseNum} code={course.courseNum} name={course.courseName}></CourseCard>)}
            </div>
            );}
    
}
 
export default CourseList;