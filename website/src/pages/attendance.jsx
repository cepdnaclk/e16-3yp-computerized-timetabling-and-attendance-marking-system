import React, { Component } from "react";
import NavBar from "../components/navbar";
import AttendanceTable from "../components/attendanceTable";
import "../css/attendance.css";
import "../css/home.css";
import bgImage from "../images/bg4.jpg";
import axios from "axios";
import LoadingComponent from "../components/loadingComponent"

const ATTENDANCE_OF_STUDENTS_URL = "/courses/findattendancesbycourseid/";

class Attendance extends Component {
  state = {
    attendanceData: [],
    courseName: null,
    studentCount: null,
    courseNumber: null,
    loading: true
  };

  componentDidMount() {
    const auth = "Bearer " + localStorage.getItem("token");
    let attendance_url = ATTENDANCE_OF_STUDENTS_URL + this.props.location.state.course.courseId;
    console.log(attendance_url);

    axios
      .get(attendance_url, {
        headers: {
          Authorization: auth,
        },
      })
      .then((response) => {
        console.log(response);
        this.setState({
          attendanceData: response.data,
          studentCount: response.data.length,
          courseName : this.props.location.state.course.courseName,
          courseNumber : this.props.location.state.course.courseNumber
        }, () => {
          this.setState({ loading: false });
        });
      })
      .catch((error) => {
        console.log("error =", error);
      });

    // this.setState({
    //   attendanceData: [
    //     ["E/16/286", "Nuwan Piyarathne", "90", "100"],
    //     ["E/16/286", "Saubhagya Munasinghe", "85", "100"],
    //     ["E/16/399", "Erandana Wijerathne", "100", "100"],
    //   ]
    // });
  }

  render() {
    if(this.state.loading){
      return <LoadingComponent></LoadingComponent>;
    }
    return (
      <React.Fragment>
        <NavBar pageName="Attendance" />

        <img src={bgImage} className="homeloginImg"></img>

        <div className="atdtble-coursedetails-outer">
          <div>
            <div className="atdtble-coursedetails">
              <p className="atdtble-paras">Course Name:{this.state.courseName}</p>
              <br></br>
              <p className="atdtble-paras">Course Number:{this.state.courseNumber}</p>
              <br></br>
              <p className="atdtble-paras">
                No of Students:{this.state.studentCount}
              </p>
            </div>
          </div>
        </div>

        <div className="atdtble-tableouter">
          <div className="atdtble-tableinner">
            <AttendanceTable data={this.state.attendanceData}></AttendanceTable>
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default Attendance;
