import React, { Component } from 'react';
import CourseCard from "./courseCard"
import "../css/courseCard.css"

class CourseList extends Component {
    state = {  courses: []}

    constructor(){

        super();

        var courses = [
            {
                code:"CO321",
                name:"Embeded Systems"
            },
            {
                code:"CO322",
                name:"Data Structures and Algorythms"
            },
            {
                code:"CO323",
                name:"Computer and Network Security"
            }
        ]

        this.state.courses = courses
    }

    render() { 
        return (  
            <div className="courseList">
                {this.state.courses.map( course => <CourseCard key={course.code} code={course.code} name={course.name}></CourseCard>)}
            </div>
            );}
    
}
 
export default CourseList;