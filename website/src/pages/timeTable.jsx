import React, { Component } from "react";
import NavBar from "../components/navbar";
import "../css/timetable.css";
import "../components/timetableSupport";
import SingleStuEvent from "../components/singleStuEvent";
import "../css/home.css";
import bgImage from "../images/bg4.jpg";
import axios from "axios";
import Footer from "../components/footer";
import LoadingComponent from "../components/loadingComponent";
import { Redirect } from "react-router-dom";

const GET_LECTUREROOMS_URL = "/lecturerooms/all";
let FIND_LEC_SCHEDULE_URL = "/schedule/findscheduledetailsbylecturer/";
window.$schArray = [];
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
class TimeTable extends Component {
  state = {
    timeTable: [],
    newSchedules: [],
    startTime: "08:00",
    endTime: "08:00",
    dayOfWeek: "",
    lecturerId: null,
    roomId: "",
    courseId: "",
    labOrLecture: "",
    lectureRooms: [],
    courses: [],
    workDone: false,
    loading: false,
  };

  componentWillMount() {
    const auth = "Bearer " + localStorage.getItem("token");
    this.setState(
      {
        timeTable: JSON.parse(localStorage.getItem("timeTable")).result,
        courses: JSON.parse(localStorage.getItem("lecCourses")).courses,
      },
      () => {
        console.log("timetable data = ", this.state.timeTable);
        console.log("lecCourses data = ", this.state.courses);
        this.setState({ loading: true });
      }
    );

    axios
      .get(GET_LECTUREROOMS_URL, {
        headers: {
          Authorization: auth,
        },
      })
      .then((response) => {
        this.setState({ lectureRooms: response.data });
      })
      .catch((error) => {
        console.log("error =", error);
      });
  }

  displayUpdates = () => {
    console.log("work started");

    const auth = "Bearer " + localStorage.getItem("token");

    FIND_LEC_SCHEDULE_URL += localStorage.getItem("lid");

    axios
      .get(FIND_LEC_SCHEDULE_URL, {
        headers: {
          Authorization: auth,
        },
      })
      .then((response) => {
        // console.log('response data = ',response.data);
        localStorage.setItem("timeTable", JSON.stringify(response.data));
        this.setState({ workDone: true });
      })
      .catch((error) => {
        console.log("error =", error);
      });
  };

  deleteSingleEvent = (input) => {
    // console.log('delete single Event, input = ',input);
    let tempTimeTable = this.state.timeTable;
    let dayItemsArray = this.state.timeTable[input.dayIndex];
    let tmpItem = dayItemsArray.filter((s, idx) => s[5] !== input.scheduleId);
    tempTimeTable[input.dayIndex] = tmpItem;
    localStorage.setItem(
      "timeTable",
      JSON.stringify({ result: tempTimeTable })
    );
    this.setState({ timeTable: tempTimeTable });
  };

  updateSingleEvent = (input) => {
    // console.log('update single Event, input = ',input);
    let tempTimeTable = this.state.timeTable;
    let dayItemsArray = this.state.timeTable[input.dayIndex];
    let tmpItem = dayItemsArray.filter((s, idx) => s[5] !== input.scheduleId);
    let target;
    for (let i in dayItemsArray) {
      if (dayItemsArray[i][5] === input.scheduleId) {
        target = dayItemsArray[i];
        ///////////////
        let courseNumber;
        let roomName;
        for (let i in this.state.courses) {
          if (this.state.courses[i].courseId === input.result.courseId) {
            courseNumber = this.state.courses[i].courseNumber;
          }
        }
        for (let i in this.state.lectureRooms) {
          if (this.state.lectureRooms[i].roomId === input.result.roomId) {
            roomName = this.state.lectureRooms[i].roomName;
          }
        }
        target[2] = "event-" + (input.result.labOrLecture + 1);
        target[3] =
          courseNumber + (input.result.labOrLecture === 1 ? " labs" : "");
        target[4] = roomName;
        tmpItem.push(target);
      }
    }
    tempTimeTable[input.dayIndex] = tmpItem;
    this.setState({ timeTable: tempTimeTable });
  };

  createSchedule = (dayIndex) => {
    let tmp = this.state.timeTable[dayIndex];

    if (tmp.length !== 0) {
      return tmp.map((schedule, index) => (
        <SingleStuEvent
          key={schedule[5]}
          start={schedule[0]}
          end={schedule[1]}
          eventType={schedule[2]}
          eventName={schedule[3]}
          roomNo={schedule[4]}
          dayIndex={dayIndex}
          index={index}
          scheduleId={schedule[5]}
          deleteSingleEvent={this.deleteSingleEvent}
          updateSingleEvent={this.updateSingleEvent}
          lectureRooms={this.state.lectureRooms}
          courses={this.state.courses}
        ></SingleStuEvent>
      ));
    } else {
      return <div></div>;
    }
  };

  createNewSchedule = () => {
    let tmpArray = [...this.state.newSchedules];

    let tmp = {
      startTime: this.state.startTime,
      endTime: this.state.endTime,
      dayOfWeek: this.state.dayOfWeek,
      lecturerId: localStorage.getItem("lid"),
      courseId: this.state.courseId,
      roomId: this.state.roomId,
      labOrLecture: this.state.labOrLecture,
    };

    window.$schArray.push(tmp);

    tmpArray.push(tmp);
    this.setState({
      newSchedules: tmpArray,
      startTime: "08:00",
      endTime: "08:00",
      dayOfWeek: "",
      lecturerId: "",
      courseId: "",
      roomId: "",
      labOrLecture: "",
    });
  };

  updateField = (event) => {
    this.setState({ [event.target.name]: event.target.value }, () => {
      return <Redirect to="/lectimetable" />;
    });
  };

  render() {
    if (this.state.loading === false)
      return <LoadingComponent></LoadingComponent>;

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
        <Footer />
      </React.Fragment>
    );
  }
}

export default TimeTable;
