import React, { Component } from "react";
import Popover from "@material-ui/core/Popover";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import { withStyles } from "@material-ui/core/styles";
import axios from "axios";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import InputLabel from "@material-ui/core/InputLabel";
import PopOver from "../components/popOver";
import FormHelperText from "@material-ui/core/FormHelperText";

let DELETE_SCHEDULE_BY_ID_URL = "/schedule/delete/";
let FIND_LEC_SCHEDULE_URL = "/schedule/findscheduledetailsbylecturer/";
const UPDATE_SCHEDULE_URL = "/schedule/updatescheduledetails";

const useStyles = (theme) => ({
  typography: {
    padding: theme.spacing(2),
  },
  root: {
    "& > *": {
      margin: theme.spacing(1),
      width: "25ch",
    },
  },
  helperText: {
    color: "red",
    fontSize: ".8em",
  },
});

class SingleEvent extends Component {
  constructor(props) {
    super(props);
    this.handleClick = this.handleClick.bind(this);
    this.handleClose = this.handleClose.bind(this);
    this.sendReq = this.sendReq.bind(this);
    this.deleteReq = this.deleteReq.bind(this);
    this.updateField = this.updateField.bind(this);
  }

  state = {
    anchorEl: null,
    isDeleted: false,
    courseId: "",
    courseIdError: "",
    roomId: "",
    roomIdError: "",
    labOrLecture: "",
    labOrLectureError: "",
    workDone: false,
  };

  updateField = (event) => {
    console.log("new_event = ", event.target);
    console.log("new_current = ", this.state.courseId);
    let error = event.target.name + "Error";
    if (event.target.value === "") {
      this.setState({ [error]: "This field is required." });
    } else {
      this.setState({ [error]: "" });
    }
    this.setState({ [event.target.name]: event.target.value });
  };

  handleClick = (event) => {
    this.setState({ anchorEl: event.currentTarget });
  };

  handleClose = () => {
    this.setState({ anchorEl: null });
  };

  validate = () => {
    let isError = false;
    const errors = {};
    if (this.state.courseId === "") {
      isError = true;
      errors.courseIdError = "This field is required.";
    }
    if (this.state.roomId === "") {
      isError = true;
      errors.roomIdError = "This field is required.";
    }
    if (this.state.labOrLecture === "") {
      isError = true;
      errors.labOrLectureError = "This field is required.";
    }
    if (isError) {
      this.setState({
        ...this.state,
        ...errors,
      });
    }
    return isError;
  };

  sendReq = () => {
    console.log("sendreq");
    //close the popped form
    //this.handleClose();
    //collect request data
    let data = {
      scheduleId: this.props.scheduleId,
      courseId: this.state.courseId,
      roomId: this.state.roomId,
      labOrLecture: this.state.labOrLecture,
    };
    console.log("sendreq data = ", data);
    //check for errors
    const error = this.validate();
    if (!error) {
      //collect request data
      let data = {
        scheduleId: this.props.scheduleId,
        courseId: this.state.courseId,
        roomId: this.state.roomId,
        labOrLecture: this.state.labOrLecture,
      };

      //changes in current page
      /*this.props.updateSingleEvent({
      result: data,
      scheduleId: this.props.scheduleId,
      dayIndex: this.props.dayIndex,
    });*/

      //put request to backend server
      const auth = "Bearer " + localStorage.getItem("token");
      console.log("update url = ", UPDATE_SCHEDULE_URL);
      axios
        .put(UPDATE_SCHEDULE_URL, data, {
          headers: {
            Authorization: auth,
          },
        })
        .then((response) => {
          console.log("send data = ", data);
          console.log("update response data = ", response.data);
          this.displayUpdates();
        })
        .catch((error) => {
          console.log("error =", error);
        });
    }
  };

  deleteReq = () => {
    console.log("deletereq");
    this.setState({ isDeleted: true });
    //close the popped form
    this.handleClose();
    this.props.deleteSingleEvent({
      scheduleId: this.props.scheduleId,
      dayIndex: this.props.dayIndex,
    });

    //delete request to backend server
    const auth = "Bearer " + localStorage.getItem("token");
    DELETE_SCHEDULE_BY_ID_URL += this.props.scheduleId;
    console.log("delete url = ", DELETE_SCHEDULE_BY_ID_URL);
    axios
      .delete(DELETE_SCHEDULE_BY_ID_URL, {
        headers: {
          Authorization: auth,
        },
      })
      .then((response) => {
        console.log("response data = ", response.data);
        this.displayUpdates();
      })
      .catch((error) => {
        console.log("error =", error);
      });
  };

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
        console.log("response data = ", response.data);
        localStorage.setItem("timeTable", JSON.stringify(response.data));
        console.log("work done");
        this.setState({ workDone: true });
      })
      .catch((error) => {
        console.log("error =", error);
      });
  };

  displayPopOver = (info) => {
    if (this.state.workDone === true) {
      return (
        <div>
          <PopOver info={info}></PopOver>
        </div>
      );
    }
  };

  render() {
    const open = Boolean(this.state.anchorEl);
    const { classes } = this.props;
    const id = open ? "simple-popover" : undefined;
    if (this.state.isDeleted) {
      return <div></div>;
    }
    return (
      <li
        className="single-event"
        data-start={this.props.start}
        data-end={this.props.end}
        data-content={this.props.content}
        data-event={this.props.eventType}
      >
        <a href="#" onClick={this.handleClick}>
          <em className="event-name" style={{ fontSize: 15 }}>
            {this.props.eventName}
          </em>
          <em className style={{ fontSize: 14, color: "white" }}>
            {this.props.roomNo}
          </em>
        </a>

        <Popover
          id={id}
          open={open}
          anchorEl={this.state.anchorEl}
          onClose={this.handleClose}
          anchorOrigin={{
            vertical: "bottom",
            horizontal: "center",
          }}
          transformOrigin={{
            vertical: "top",
            horizontal: "center",
          }}
        >
          <form className={classes.root} error>
            <InputLabel htmlFor="selectCourseLabel">Course</InputLabel>
            <Select
              labelId="selectCourseLabel"
              id="outlined-basic"
              variant="outlined"
              displayEmpty
              color="secondary"
              name="courseId"
              onChange={(e) => this.updateField(e)}
              value={this.state.courseId}
            >
              <MenuItem value="">None</MenuItem>
              {this.props.courses.map((c, index) => (
                <MenuItem value={c.courseId}>{c.courseName}</MenuItem>
              ))}
            </Select>
            <FormHelperText error>{this.state.courseIdError}</FormHelperText>

            <InputLabel htmlFor="selectLRLabel">Lecture Room</InputLabel>
            <Select
              labelId="selectLRLabel"
              id="outlined-basic"
              variant="outlined"
              displayEmpty
              color="secondary"
              name="roomId"
              onChange={(e) => this.updateField(e)}
              value={this.state.roomId}
            >
              <MenuItem value="">None</MenuItem>
              {this.props.lectureRooms.map((lr, index) => (
                <MenuItem value={lr.roomId}>{lr.roomName}</MenuItem>
              ))}
            </Select>
            <FormHelperText error>{this.state.roomIdError}</FormHelperText>

            <InputLabel htmlFor="selectLabOrLectureLabel">
              Lab or Lecture
            </InputLabel>
            <Select
              labelId="selectLabOrLectureLabel"
              id="outlined-basic"
              variant="outlined"
              displayEmpty
              color="secondary"
              name="labOrLecture"
              onChange={(e) => this.updateField(e)}
              value={this.state.labOrLecture}
            >
              <MenuItem value="">None</MenuItem>
              <MenuItem value="0">Lecture</MenuItem>
              <MenuItem value="1">Lab</MenuItem>
            </Select>
            <FormHelperText error>
              {this.state.labOrLectureError}
            </FormHelperText>

            <div style={{ display: "flex", justifyContent: "center", gap: 8 }}>
              <Button
                onClick={this.sendReq}
                variant="contained"
                color="primary"
              >
                Submit
              </Button>
              <Button
                onClick={this.deleteReq}
                variant="contained"
                color="secondary"
              >
                Delete
              </Button>
              <br></br>
            </div>
          </form>
          <div style={{ display: "flex", justifyContent: "center", gap: 8 }}>
            {this.displayPopOver("Update Succeeded!")}
          </div>
        </Popover>
      </li>
    );
  }
}

export default withStyles(useStyles)(SingleEvent);
