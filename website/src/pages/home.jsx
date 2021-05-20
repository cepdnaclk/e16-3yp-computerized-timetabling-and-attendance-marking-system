import React, { Component } from "react";
import NavBar from "../components/navbar";
import CourseList from "../components/courseList";
import UserCard from "../components/userCard";
import axios from "axios";
import "../css/home.css";
import bgImage from "../images/bg4.jpg";
import { Redirect } from "react-router";
import LoadingComponent from "../components/loadingComponent"
import Footer from "../components/footer";
const STUDENT_HOME_PAGE_URI = "/student";
const GET_COURSES_BY_SID_URL = "/courses/findcoursesbystudentid/";
const FIND_STU_SCHEDULE_URL = "/schedule/findscheduledetailsbystudent/";

class Home extends Component {
  state = {
    courses: [],
    details: [],
    searchWord: null,
    page : 'dailyattendance',
    loading1:true,
    loading2:true
  };

  componentDidMount() {
    const auth = "Bearer " + localStorage.getItem("token");
    let course_url = GET_COURSES_BY_SID_URL + localStorage.getItem("sid");
    axios
      .get(course_url, {
        headers: {
          Authorization: auth,
        },
      })
      .then((response) => {
        console.log(response);
        this.setState({ courses: response.data }, () => {
          this.setState({ loading1: false });
        });
      })
      .catch((error) => {
        console.log("error =", error);
      });
    this.setState({ searchWord: "" });


    let timetable_url = FIND_STU_SCHEDULE_URL + localStorage.getItem("sid");  
    
    axios
      .get(timetable_url, {
        headers: {
          Authorization: auth,
        },
      })
      .then((response) => {
        console.log('timetable response data = ',response.data);
        localStorage.setItem("studentTimeTable",JSON.stringify(response.data));
        this.setState({loading2:false});
      })
      .catch((error) => {
        console.log("error =", error);
      });
  }

  constructor(props) {
    super(props);
  }


  render() {

    if(this.state.loading1 === true || this.state.loading2 === true) return <LoadingComponent></LoadingComponent>

    return (
      <>
      <div className="home-outer">
        <NavBar pageName="Home" />
        <img src={bgImage} className="homeloginImg"></img>
        <h2 className="hm-title">My Attendance records</h2>
        <CourseList page={this.state.page} courses={this.state.courses} sw={this.state.searchWord} />
		<UserCard data={[localStorage.getItem("sfn"), localStorage.getItem("sen")]} />
     </div>
     <Footer/>
     </>
    );
  }
}

export default Home;
