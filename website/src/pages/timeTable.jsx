import React, { Component } from "react";
import NavBar from "../components/navbar";
import "../css/timetable.css";
import "../components/timetableSupport";
import SingleEvent from "../components/singleEvent";
import "../css/home.css";
import bgImage from "../images/bg4.jpg";
import AddSchedules from "../components/addSchedules";
import PopOver from '../components/popOver'
import SubmitSchedules from "../components/submitSchedules";
import axios from "axios";
import Footer from '../components/footer'


const GET_LECTUREROOMS_URL = "/lecturerooms/all";
const SUBMIT_ALL_SCHEDULES_URL = "/schedule/add/all";
let FIND_LEC_SCHEDULE_URL = "/schedule/findscheduledetailsbylecturer/";
window.$schArray = [];
let weekDays = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday"];
let capitalWeekDays = ["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"];
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
    workDone:false,
  };

  componentWillMount() {
    const auth = "Bearer " + localStorage.getItem("token");
    this.setState(
      {
        timeTable: JSON.parse(localStorage.getItem("timeTable")).result,
        courses: JSON.parse(localStorage.getItem("lecCourses")).courses,
      },
      () => {
        // console.log('timetable data = ',this.state.timeTable);
        // console.log('lecCourses data = ',this.state.courses);
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

  displayUpdates = ()=>{

    console.log("work started")

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
      localStorage.setItem("timeTable",JSON.stringify(response.data));
      this.setState({workDone:true})
     
      
    })
    .catch((error) => {
      console.log("error =", error);
    });


  }

  deleteSingleEvent = (input) => {
    // console.log('delete single Event, input = ',input);
    let tempTimeTable = this.state.timeTable;
    let dayItemsArray = this.state.timeTable[input.dayIndex];
    let tmpItem = dayItemsArray.filter((s, idx) => s[5] !== input.scheduleId);
    tempTimeTable[input.dayIndex] = tmpItem;
    localStorage.setItem("timeTable",JSON.stringify({"result":tempTimeTable}));
    this.setState({timeTable : tempTimeTable});
  }

  updateSingleEvent = (input) => {
    // console.log('update single Event, input = ',input);
    let tempTimeTable = this.state.timeTable;
    let dayItemsArray = this.state.timeTable[input.dayIndex];
    let tmpItem = dayItemsArray.filter((s, idx) => s[5] !== input.scheduleId);
    let target;
    for(let i in dayItemsArray){
      if(dayItemsArray[i][5] === input.scheduleId){
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
        target[2] = 'event-'+(input.result.labOrLecture+1);
        target[3] = courseNumber+((input.result.labOrLecture === 1)?' labs':'');
        target[4] = roomName;
        tmpItem.push(target);
      }
    }
    tempTimeTable[input.dayIndex] = tmpItem;
    // localStorage.setItem("timeTable",JSON.stringify({"result":tempTimeTable}));
    this.setState({timeTable : tempTimeTable});
    // console.log('tempTimeTable = ',tempTimeTable);
  }


  createSchedule = (dayIndex) => {
    let tmp = this.state.timeTable[dayIndex];

    if (tmp.length !== 0) {
      return tmp.map((schedule,index) => (
        <SingleEvent
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

        ></SingleEvent>
      ));
    } else {
      return <div></div>;
    }
  };

  deleteSchedule = (index) => {
    let tmp = this.state.newSchedules.filter((s, idx) => idx !== index);
    window.$schArray = [...tmp];
    this.setState({ newSchedules: tmp });
  };

  editSchedule = (sch, index) => {
    let tmp = this.state.newSchedules.filter((s, idx) => idx !== index);
    window.$schArray = [...tmp];
    this.setState({
      newSchedules: tmp,
      startTime: sch.startTime,
      endTime: sch.endTime,
      dayOfWeek: sch.dayOfWeek,
      courseId: sch.courseId,
      labOrLecture: sch.labOrLecture,
      lecturerId: sch.lecturerId,
      roomId: sch.roomId,
    });
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
    this.setState({ [event.target.name]: event.target.value });
  };

  submitSchedules = () => {
    let tmpArray = [...this.state.newSchedules];
    // console.log(window.$schArray);
    //send req to the backend
    let data = window.$schArray;
    const auth = "Bearer " + localStorage.getItem("token");
    axios.post(SUBMIT_ALL_SCHEDULES_URL, data,{
      headers: {
          'Authorization': auth
      }
      }).then((response) => {
        console.log('submit response = ',response);
        this.displayUpdates()
      }).catch(e =>{
        console.log('error = ',e);
      })
    //update timetable in the page itself
    console.log("timetable... = ", this.state.timeTable);
    let tempTimeTable = [...this.state.timeTable];
    for (let i in window.$schArray) {
      let target = window.$schArray[i];
      console.log("Index = " + i + "   ,element = " + JSON.stringify(target));
      let courseNumber;
      let roomName;
      for (let i in this.state.courses) {
        if (this.state.courses[i].courseId === target.courseId) {
          courseNumber = this.state.courses[i].courseNumber;
        }
      }
      for (let i in this.state.lectureRooms) {
        if (this.state.lectureRooms[i].roomId === target.roomId) {
          roomName = this.state.lectureRooms[i].roomName;
        }
      }
      let myArr = [];
      myArr[0] = target.startTime;
      myArr[1] = target.endTime;
      myArr[2] = "event-" + (target.labOrLecture + 1);
      myArr[3] = courseNumber;
      myArr[4] = roomName;
      let index = capitalWeekDays.indexOf(target.dayOfWeek);
      console.log('myArr = ',myArr);
      console.log('index = ',index);
      console.log('tempTimeTable = ',tempTimeTable);
      tempTimeTable[index].push(myArr);
    }
    //this.setState({ timeTable: tempTimeTable });
    //clear schedules
    window.$schArray = [];

  };

  displayPopOver = ()=> {

    if(this.state.workDone === true){

      return <PopOver info="TimeTable sucsesfully updated — Refresh the page!"></PopOver>
    }

  }

  render() {

    

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

          <div className="tt-align-outer">
            <div className="tt-addschedule">
              <AddSchedules
                lectureRooms={this.state.lectureRooms}
                courses={this.state.courses}
                st={this.state.startTime}
                oc={this.updateField}
                cns={this.createNewSchedule}
                startTime={this.state.startTime}
                endTime={this.state.endTime}
                dayOfWeek={this.state.dayOfWeek}
                lecturerId={this.state.lecturerId}
                courseId={this.state.courseId}
                roomId={this.state.roomId}
                labOrLecture={this.state.labOrLecture}
              ></AddSchedules>
            </div>
            
            <div className="tt-submitschedules">
              <SubmitSchedules
                schedules={this.state.newSchedules}
                ds={this.deleteSchedule}
                es={this.editSchedule}
                subs={this.submitSchedules}
              ></SubmitSchedules>
              <div className="tt-popOver">
                {this.displayPopOver()}
              </div>
              
            </div>
          </div>
        </div>
        <Footer/>
      </React.Fragment>
    );
  }
}

export default TimeTable;