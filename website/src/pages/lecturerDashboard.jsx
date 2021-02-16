import React, { Component } from 'react';
import NavBar from '../components/navbar'
import CourseList from "../components/courseList"
import LecturerCard from "../components/lecturerCard"
import bgImage from '../images/bg4.jpg'
import '../css/home.css'
import '../css/lecturerDashboard.css'

const STUDENT_HOME_PAGE_URI =  '/lecturer'; 

class LecturerDashboard extends Component {
 
state = {
    courses:[],
    details:[],
    searchWord:null
}

componentDidMount(){
  this.setState({courses:[
      {courseNum:"CO321",courseName:"EMBEDED SYSTEMS"},
      {courseNum:"CO322",courseName:"DATA STRUCTURES AND ALGORYTHMS"},
      {courseNum:"CO323",courseName:"COMPUTER COMINUCATION NETWORKS II"},

  ]});
  this.setState({details:[
      {   
          id:1,
          nameTag:"Name:",
          value: "Saubhagya"

      },
      {   
          id:2,
          nameTag:"E Number:",
          value:"E/16/242"

      },
      {   
          id:3,
          nameTag:"Password:",
          value:"samplePassword"

      }
  ]});

  this.setState({searchWord:''})

}

onSerchValueChanged = e => {

    this.setState({searchWord : e.target.value});

}
    render() { 
        return (  
            <div>
            <NavBar pageName="Lecturer Dashboard" />
            <img src={bgImage} className="homeloginImg"></img>
            <h2 className="hm-title lc-title">Student Attendance</h2>
            <CourseList courses={this.state.courses} sw={this.state.searchWord}/>
            <LecturerCard data={"Lecturer Name"} oc={this.onSerchValueChanged} />
        
            </div>
        );
    }
}
 
export default LecturerDashboard;