import React, { Component } from "react";
import NavBar from "../components/navbar";
import "../css/timetable.css";
import "../components/timetableSupport";
import SingleStuEvent from "../components/singleStuEvent";
import "../css/home.css";
import bgImage from "../images/bg4.jpg";
import axios from "axios";
import Footer from "../components/footer";

let FIND_STU_SCHEDULE_URL = "/schedule/findscheduledetailsbystudent/";

let weekDays = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday"];
let times = [
  "08:00",
  "08:30",
  "09:00",
  "09:30",
  "10:00",
  "10:30",
  "11:00",
  "11:30",
  "12:00",
  "12:30",
  "13:00",
  "13:30",
  "14:00",
  "14:30",
  "15:00",
  "15:30",
  "16:00",
  "16:30",
  "17:00",
];
class TimeTable extends React.Component {
  state = { loading: false };

  componentDidMount() {
    this.setState(
      {
        timeTable: JSON.parse(localStorage.getItem("studentTimeTable")).result,
      },
      () => {
        this.setState({ loading: true });
      }
    );

    const auth = "Bearer " + localStorage.getItem("token");
    FIND_STU_SCHEDULE_URL += localStorage.getItem("sid");  

    axios
      .get(FIND_STU_SCHEDULE_URL, {
        headers: {
          Authorization: auth,
        },
      })
      .then((response) => {
        console.log('response data = ',response.data);
        localStorage.setItem("studentTimeTable",JSON.stringify(response.data));
      })
      .catch((error) => {
        console.log("error =", error);
      });

  }

  createSchedule = (dayIndex) => {
    let tmp = this.state.timeTable[dayIndex];

    return tmp.map((schedule) => (
      <SingleStuEvent
        key={dayIndex}
        start={schedule[0]}
        end={schedule[1]}
        eventType={schedule[2]}
        eventName={schedule[3]}
        roomNo={schedule[4]}
      ></SingleStuEvent>
    ));
  };

  render() {
    return (
      <React.Fragment>
        <NavBar pageName="Time table"></NavBar>
        {this.state.loading === true ? (
          <div style={{marginBottom:"10%"}}>
            <img src={bgImage} className="homeloginImg"></img>
            <div>
              <br />

              <div className="tt-outer">
                <div className="cd-schedule loading ">
                  <div className="timeline">
                    <ul>
                      {times.map((time, index) => (
                        <li key={index}>
                          <span>{time}</span>
                        </li>
                      ))}
                    </ul>
                  </div>

                  <div className="events">
                    <ul className="wrap">
                      {weekDays.map((day, index) => (
                        <li key={index} className="events-group">
                          <div className="top-info">
                            <span>{day}</span>
                          </div>
                          <ul>{this.createSchedule(index)}</ul>
                        </li>
                      ))}
                    </ul>
                  </div>

                  <div className="cover-layer"></div>
                </div>
              </div>
            </div>
          </div>
        ) : (
          <div style={{marginBottom:"10%"}}>
            <p>Still Loading</p>
          </div>
        )}
        <Footer/>
      </React.Fragment>
    );
  }
}

export default TimeTable;
