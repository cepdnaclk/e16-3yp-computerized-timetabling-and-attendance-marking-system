import React, { Component } from "react";
import NavBar from "../components/navbar";
import "../css/timetable.css";
import "../components/timetableSupport";
import SingleEvent from "../components/singleEvent";
import "../css/home.css";
import bgImage from "../images/bg4.jpg";
import axios from "axios";

let FIND_LEC_SCHEDULE_URL = "/schedule/findscheduledetailsbylecturer/";
const weekdays = ["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"];

class TimeTable extends Component {
  state = { timeTable: [], schedule: [] };

  componentWillMount() {
    const auth = "Bearer " + localStorage.getItem("token");
    FIND_LEC_SCHEDULE_URL += localStorage.getItem("lid");

    axios
      .get(FIND_LEC_SCHEDULE_URL, {
        headers: {
          Authorization: auth,
        },
      })
      .then((response) => {
        this.setState({ timeTable: response.data });
      })
      .catch((error) => {
        console.log("error =", error);
      });
    this.setState({
      schedule: [
        [
          ["14:00", "15:15", "event-1", "CO322", "Room No"],
          ["08:00", "10:15", "event-1", "C0321", "Room No"],
          ["11:00", "12:30", "event-2", "CO321 Labs", "Room No"],
        ],
        [["13:00", "17:00", "event-1", "CO323", "Room No"]],
        [],
        [
          ["09:30", "10:30", "event-1", "C0323", "Room No"],
          ["15:00", "16:00", "event-1", "CO324", "Room No"],
          ["16:00", "17:00", "event-2", "CO324 Labs", "Room No"],
        ],
        [["15:00", "16:00", "event-1", "CO324", "Room No"]],
      ],
    });
  }

  createSchedule = (dayIndex) => {
    // let tmp = this.state.timeTable[dayIndex];//db array
    let tmp = this.state.schedule[dayIndex];//original array

    if (tmp && tmp.length !== 0) {
      return tmp.map((schedule) => (
        <SingleEvent
          start={schedule[0]}
          end={schedule[1]}
          eventType={schedule[2]}
          eventName={schedule[3]}
          roomNo={schedule[4]}
        ></SingleEvent>
      ));
    } else {
      return <div></div>;
    }
  };

  render() {
    if (this.state.schedule && this.state.schedule.length > 0) {
      console.log("schedule in render = ", this.state.schedule);
      
    }
    if (this.state.timeTable && this.state.timeTable.length > 0) {
      console.log("timeTable in render = ", this.state.timeTable);
    }
    return (
      <React.Fragment>
        <NavBar pageName="Time table"></NavBar>
        <div>
          <img src={bgImage} className="homeloginImg"></img>
          <div>
            <br />

            <div className="tt-outer">
              <div className="cd-schedule loading ">
                <div className="timeline">
                  <ul>
                    <li>
                      <span>08:00</span>
                    </li>
                    <li>
                      <span>08:30</span>
                    </li>
                    <li>
                      <span>09:00</span>
                    </li>
                    <li>
                      <span>09:30</span>
                    </li>
                    <li>
                      <span>10:00</span>
                    </li>
                    <li>
                      <span>10:30</span>
                    </li>
                    <li>
                      <span>11:00</span>
                    </li>
                    <li>
                      <span>11:30</span>
                    </li>
                    <li>
                      <span>12:00</span>
                    </li>
                    <li>
                      <span>12:30</span>
                    </li>
                    <li>
                      <span>13:00</span>
                    </li>
                    <li>
                      <span>13:30</span>
                    </li>
                    <li>
                      <span>14:00</span>
                    </li>
                    <li>
                      <span>14:30</span>
                    </li>
                    <li>
                      <span>15:00</span>
                    </li>
                    <li>
                      <span>15:30</span>
                    </li>
                    <li>
                      <span>16:00</span>
                    </li>
                    <li>
                      <span>16:30</span>
                    </li>
                    <li>
                      <span>17:00</span>
                    </li>
                  </ul>
                </div>

                <div className="events">
                  <ul className="wrap">
                    <li className="events-group">
                      <div className="top-info">
                        <span>Monday</span>
                      </div>
                      <ul>{this.createSchedule(0)}</ul>
                    </li>

                    <li className="events-group">
                      <div className="top-info">
                        <span>Tuesday</span>
                      </div>

                      <ul>{this.createSchedule(1)}</ul>
                    </li>

                    <li className="events-group">
                      <div className="top-info">
                        <span>Wednesday</span>
                      </div>

                      <ul>{this.createSchedule(2)}</ul>
                    </li>

                    <li className="events-group">
                      <div className="top-info">
                        <span>Thursday</span>
                      </div>

                      <ul>{this.createSchedule(3)}</ul>
                    </li>

                    <li className="events-group">
                      <div className="top-info">
                        <span>Friday</span>
                      </div>

                      <ul>{this.createSchedule(4)}</ul>
                    </li>
                  </ul>
                </div>

                <div className="cover-layer"></div>
              </div>
            </div>
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default TimeTable;
