import React, { Component } from 'react';
import NavBar from '../components/navbar'
import CourseList from "../components/courseList"
import UserCard from "../components/userCard"

class Home extends Component {
    state = {  }
    render() { 
        return (  
            <React.Fragment>
                <NavBar pageName="Home" />
                <h3 className="title">My Attendance records</h3>
                <CourseList/>
                <UserCard/>
            </React.Fragment>

        );
    }
}
 
export default Home;