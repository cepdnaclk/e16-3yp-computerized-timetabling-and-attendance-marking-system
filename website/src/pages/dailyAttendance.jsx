import React, { Component } from "react";
import NavBar from "../components/navbar";
import DAttendanceTable from "../components/dAttedanceTable";
import "../css/home.css";
import bgImage from "../images/bg4.jpg";
import axios from "axios";
import LoadingComponent from "../components/loadingComponent"
import Footer from '../components/footer'

const GET_ATTENDANCE_URL = "/attendance/findattendancebystudentidandcourseid";

class DailyAttendance extends Component {
  state = {
    attendanceData: [],
    atteandance: [],
    courseCode: null,
    regNo: null,
    name: null,
    loading: true,
  };

  constructor(props) {
    super(props);
    // console.log(this.props.location.state.course.courseId);
    // console.log(localStorage.getItem("sid"));
  }

  componentDidMount() {
    const auth = "Bearer " + localStorage.getItem("token");
    console.log('url = ',GET_ATTENDANCE_URL);
    let attendance_url = GET_ATTENDANCE_URL +
      "?course=" +
      this.props.location.state.course.courseId +
      "&student=" +
      localStorage.getItem("sid");
    console.log('url = ',attendance_url);

    axios
      .get(attendance_url, {
        headers: {
          Authorization: auth,
        },
      })
      .then((response) => {
        console.log(response);
        this.setState({
          attendanceData: response.data.attendanceItemList,
          atteandance: response.data,
        }, () => {
          this.setState({ loading: false });
        });
      })
      .catch((error) => {
        console.log("error =", error);
      });

    this.setState({
      courseCode: this.props.location.state.course.courseNumber,
      regNo: localStorage.getItem("sen"),
      name: localStorage.getItem("sfn") + " " + localStorage.getItem("sln"),
    });
  }

  render() {
    if(this.state.loading){
      return <LoadingComponent></LoadingComponent>;
    }
    return (
      <React.Fragment>
        <NavBar pageName="Daily Attendance" />

        <img src={bgImage} className="homeloginImg"></img>

        <div className="atdtble-coursedetails-outer">
          <div>
            <div className="atdtble-coursedetails">
              <p className="atdtble-paras">Course:{this.state.courseCode}</p>
              <br></br>
              <p className="atdtble-paras">Reg Number:{this.state.regNo}</p>
              <br></br>
              <p className="atdtble-paras">Name:{this.state.name}</p>
            </div>
          </div>
        </div>

        <div className="atdtble-tableouter">
          <div className="atdtble-tableinner">
            {<DAttendanceTable
              data={this.state.attendanceData}
            ></DAttendanceTable>}
          </div>
        </div>

        <div className="atdtble-coursedetails-outer">
          <div>
            <div className="atdtble-coursedetails">
            <br></br>
            <p className="atdtble-paras">No of Lecture Days:{this.state.atteandance.countLectureDays}</p>
              <br></br>
              <p className="atdtble-paras">No of Present Lecture Days:{this.state.atteandance.presentLabDays}</p>
              <br></br>
              <p className="atdtble-paras">No of Lab Days:{this.state.atteandance.countLabDays}</p>
              <br></br>
              <p className="atdtble-paras">No of Present Lab Days:{this.state.atteandance.presentLectureDays}</p>
              <br></br>
              <p className="atdtble-paras">
                Lecture Percentage:{(Math.round(this.state.atteandance.lecturePercentage * 100) / 100).toFixed(2)+'%'}
              </p>
              <br></br>
              <p className="atdtble-paras">Lab Percentage:{(Math.round(this.state.atteandance.labPercentage * 100) / 100).toFixed(2)+'%'}</p>
              <br></br>
              
              
            </div>
          </div>
        </div>
        <Footer/>
      </React.Fragment>
    );
  }
}

export default DailyAttendance;
