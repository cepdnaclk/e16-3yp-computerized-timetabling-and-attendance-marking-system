import React, { Component } from 'react';
import NavBar from '../components/navbar'
import CourseList from "../components/courseList"
import UserCard from "../components/userCard"
import axios from 'axios'

const STUDENT_HOME_PAGE_URI =  'http://localhost:8080/student';

class Home extends Component {
    state = {  }

    constructor(props){
        super(props);
        const auth = "Bearer "+ localStorage.getItem('token');
        axios.get(STUDENT_HOME_PAGE_URI, {
                            headers: {
                              'Authorization': auth
                            }
                          }).then(function (response){

                             console.log(response)
                          })
    }

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