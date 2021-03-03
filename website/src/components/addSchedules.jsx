import { React, Component } from "react";
import { makeStyles } from "@material-ui/core/styles";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import InputLabel from "@material-ui/core/InputLabel";
import { withStyles } from "@material-ui/core/styles";
import FormHelperText from "@material-ui/core/FormHelperText";

let weekDays = ["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"];
const timeError = 'start time must less than end time.';
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
});

class AddSchedules extends Component {
  state = {
    startTime: "08:00",
    startTimeError: "",
    endTime: "08:00",
    endTimeError: "",
    dayOfWeek: "",
    dayOfWeekError: "",
    courseId: "",
    courseIdError: "",
    roomId: "",
    roomIdError: "",
    labOrLecture: "",
    labOrLectureError: "",
  };

  constructor(props) {
    super(props);
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.validate = this.validate.bind(this);
  }

  handleChange = (event) => {
    console.log('name = ',event.target.name);
    console.log('current value = ',this.state[event.target.name]);
    console.log('change value = ',event.target.value);
    console.log('start = '+this.state.startTime+' ,end = '+this.state.endTime);
    
    this.props.oc(event);
    this.setState({ [event.target.name]: event.target.value });
    if (event.target.name.localeCompare("startTime") === 0) {
      console.log('test1');
      if (event.target.value.localeCompare("08:00") < 0 || event.target.value.localeCompare("17:00") > 0) {
        console.log('test2');
        this.setState({
          startTimeError: "Time must between 08:00AM - 05:00PM.",
        });
      } 
      else if (event.target.value.localeCompare(this.state.endTime) >= 0) {
        console.log('test3');
        this.setState({
          startTimeError: timeError,
          endTimeError: timeError,
        });
      } 
      else if((this.state.startTimeError.localeCompare(timeError) === 0) && (event.target.value.localeCompare(this.state.endTime) < 0)){
        console.log('test4');
        this.setState({
          startTimeError: "",
          endTimeError: "",
        });
      }
      else {
        console.log(this.state.startTimeError.localeCompare(timeError));
        console.log('test5');
        this.setState({ startTimeError: "" });
      }
    } 
    else if (event.target.name.localeCompare("endTime") === 0) {
      console.log('test6');
      if (event.target.value.localeCompare("08:00") < 0 || event.target.value.localeCompare("17:00") > 0) {
        console.log('test7');
        this.setState({ endTimeError: "Time must between 08:00AM - 05:00PM." });
      } 
      else if (this.state.startTime.localeCompare(event.target.value) >= 0) {
        console.log('test8');
        this.setState({
          startTimeError: timeError,
          endTimeError: timeError,
        });
      }
      else if((this.state.endTimeError.localeCompare(timeError) === 0) && (this.state.startTime.localeCompare(event.target.value) < 0)){
        console.log('test9');
        this.setState({
          startTimeError: "",
          endTimeError: "",
        });
      } 
      else {
        console.log(this.state.endTimeError.localeCompare(timeError));
        console.log('test10');
        this.setState({ endTimeError: "" });
      }
    } 
    else {
      console.log('test11');
      let error = event.target.name + "Error";
      if (event.target.value === "") {
        console.log('test12');
        this.setState({ [error]: "This field is required." });
      }
      else {
        console.log('test13');
        this.setState({ [error]: "" });
      }
      
    }
  
  };

  handleSubmit = () => {
    const error = this.validate();
    if (!error) {
      this.props.cns();
    }
  };

  validate = () => {
    let isError = false;
    const errors = {};
    if (
      this.state.startTime.localeCompare("08:00") < 0 ||
      this.state.startTime.localeCompare("17:00") > 0
    ) {
      isError = true;
      errors.startTimeError = "Time must between 08:00AM - 05:00PM.";
    }
    if (
      this.state.endTime.localeCompare("08:00") < 0 ||
      this.state.endTime.localeCompare("17:00") > 0
    ) {
      isError = true;
      errors.endTimeError = "Time must between 08:00AM - 05:00PM.";
    }
    if (this.state.startTime.localeCompare(this.state.endTime) >= 0) {
      isError = true;
      errors.startTimeError = timeError;
      errors.endTimeError = timeError;
    }
    if (this.state.dayOfWeek === "") {
      isError = true;
      errors.dayOfWeekError = "This field is required.";
    }
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

  render() {
    const { classes } = this.props;
    return (
      <form className={classes.root} noValidate autoComplete="off">
        <InputLabel htmlFor="startLabel">Start time</InputLabel>
        <TextField
          error={!!this.state.startTimeError}
          required
          labelId="startLabel"
          id="outlined-basic"
          variant="outlined"
          type="time"
          defaultValue="08:00"
          color="secondary"
          name="startTime"
          onChange={(e) => this.handleChange(e)}
          key={1}
          value={this.props.startTime}
          helperText={this.state.startTimeError && this.state.startTimeError}
        />
        <InputLabel htmlFor="endLabel">End Time</InputLabel>
        <TextField
          error={!!this.state.endTimeError}
          required
          labelId="endLabel"
          id="outlined-basic"
          variant="outlined"
          type="time"
          defaultValue="08:00"
          color="secondary"
          name="endTime"
          onChange={(e) => this.handleChange(e)}
          value={this.props.endTime}
          helperText={this.state.endTimeError && this.state.endTimeError}
        />
        <InputLabel htmlFor="selectDayLabel">Day</InputLabel>
        <Select
          labelId="selectDayLabel"
          id="outlined-basic"
          variant="outlined"
          displayEmpty
          color="secondary"
          name="dayOfWeek"
          onChange={(e) => this.handleChange(e)}
          value={this.props.dayOfWeek}
        >
          <MenuItem value="">None</MenuItem>
          {weekDays.map((day, index) => (
            <MenuItem value={day}>{day}</MenuItem>
          ))}
        </Select>
        <FormHelperText error>{this.state.dayOfWeekError}</FormHelperText>

        <InputLabel htmlFor="selectCourseLabel">Course</InputLabel>
        <Select
          labelId="selectCourseLabel"
          id="outlined-basic"
          variant="outlined"
          displayEmpty
          color="secondary"
          name="courseId"
          onChange={(e) => this.handleChange(e)}
          value={this.props.courseId}
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
          onChange={(e) => this.handleChange(e)}
          value={this.props.roomId}
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
          onChange={(e) => this.handleChange(e)}
          value={this.props.labOrLecture}
        >
          <MenuItem value="">None</MenuItem>
          <MenuItem value="0">Lecture</MenuItem>
          <MenuItem value="1">Lab</MenuItem>
        </Select>
        <FormHelperText error>{this.state.labOrLectureError}</FormHelperText>
        <div
          style={{
            display: "flex",
            justifyContent: "center",
            marginBottom: 10,
          }}
        >
          <Button
            variant="contained"
            color="primary"
            onClick={this.handleSubmit}
            style={{ paddingLeft: 70, paddingRight: 70 }}
          >
            Add
          </Button>
        </div>
      </form>
    );
  }
}
export default withStyles(useStyles)(AddSchedules);
