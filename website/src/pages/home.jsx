import React, { Component } from 'react';
import NavBar from '../components/navbar'
import CourseList from "../components/courseList"
import UserCard from "../components/userCard"
import axios from 'axios'

const STUDENT_HOME_PAGE_URI =  'http://localhost:8080/student';

class Home extends Component {
    state = { courses: [] ,
              details: []
    }

    constructor(props){
        super(props);
        const auth = "Bearer "+ localStorage.getItem('token');
        axios.get(STUDENT_HOME_PAGE_URI, {
                            headers: {
                              'Authorization': auth
                            }
                          }).then( response =>{
                              if(response.status === 200){

                                this.setState({courses:response.data.courses})
                                this.setState({details:[
                                    {   
                                        id:1,
                                        nameTag:"Name:",
                                        value: response.data.student.userName
                        
                                    },
                                    {   
                                        id:2,
                                        nameTag:"E Number:",
                                        value:response.data.student.eNo
                        
                                    },
                                    {   
                                        id:3,
                                        nameTag:"Password:",
                                        value:"samplePassword"
                        
                                    }
                                ]}

                                )
                                console.log(response);

                              }
                            
                          })
    }

    render() { 
        return (  
            <React.Fragment>
                <NavBar pageName="Home" />
                <h3 className="title">My Attendance records</h3>
                <CourseList courses={this.state.courses} />
                <UserCard data={this.state.details} />
            </React.Fragment>

        );
    }
}
 
export default Home;