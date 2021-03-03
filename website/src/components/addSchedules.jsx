import {React,Component} from "react";
import { makeStyles } from "@material-ui/core/styles";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import InputLabel from "@material-ui/core/InputLabel";
import { withStyles } from "@material-ui/core/styles";

let weekDays = ["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"];

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

  constructor(props){
    super(props);
    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
    this.validate = this.validate.bind(this);
  }

  onChange = (e) => {

  }

  onSubmit = (e) => {

  }

  validate = () => {

  }

  render(){
    const { classes } = this.props;
    return (
      <form className={classes.root} noValidate autoComplete="off">
        <InputLabel htmlFor="startLabel">Start time</InputLabel>
        <TextField
          labelId="startLabel"
          id="outlined-basic"
          variant="outlined"
          type="time"
          defaultValue="08:00"
          color="secondary"
          name="startTime"
          onChange={(e) => this.props.oc(e)}
          key={1}
          value={this.props.startTime}
        />
        <InputLabel htmlFor="endLabel">End Time</InputLabel>
        <TextField
          labelId="endLabel"
          id="outlined-basic"
          variant="outlined"
          type="time"
          defaultValue="08:00"
          color="secondary"
          name="endTime"
          onChange={(e) => this.props.oc(e)}
          value={this.props.endTime}
        />
        <InputLabel htmlFor="selectDayLabel">Day</InputLabel>
        <Select
          labelId="selectDayLabel"
          id="outlined-basic"
          variant="outlined"
          displayEmpty
          color="secondary"
          name="dayOfWeek"
          onChange={(e) => this.props.oc(e)}
          value={this.props.dayOfWeek}
        >
          <MenuItem value="">None</MenuItem>
          {weekDays.map((day, index) => (
            <MenuItem value={day}>{day}</MenuItem>
          ))}
        </Select>
  
        <InputLabel htmlFor="selectCourseLabel">Course</InputLabel>
        <Select
          labelId="selectCourseLabel"
          id="outlined-basic"
          variant="outlined"
          displayEmpty
          color="secondary"
          name="courseId"
          onChange={(e) => this.props.oc(e)}
          value={this.props.courseId}
        >
          <MenuItem value="">None</MenuItem>
          {this.props.courses.map((c, index) => (
            <MenuItem value={c.courseId}>{c.courseName}</MenuItem>
          ))}
        </Select>
  
        <InputLabel htmlFor="selectLRLabel">Lecture Room</InputLabel>
        <Select
          labelId="selectLRLabel"
          id="outlined-basic"
          variant="outlined"
          displayEmpty
          color="secondary"
          name="roomId"
          onChange={(e) => this.props.oc(e)}
          value={this.props.roomId}
        >
          <MenuItem value="">None</MenuItem>
          {this.props.lectureRooms.map((lr, index) => (
            <MenuItem value={lr.roomId}>{lr.roomName}</MenuItem>
          ))}
        </Select>
        <InputLabel htmlFor="selectLabOrLectureLabel">Lab or Lecture</InputLabel>
        <Select
          labelId="selectLabOrLectureLabel"
          id="outlined-basic"
          variant="outlined"
          displayEmpty
          color="secondary"
          name="labOrLecture"
          onChange={(e) => this.props.oc(e)}
          value={this.props.labOrLecture}
        >
          <MenuItem value="">None</MenuItem>
          <MenuItem value="0">Lecture</MenuItem>
          <MenuItem value="1">Lab</MenuItem>
        </Select>
        <div
          style={{ display: "flex", justifyContent: "center", marginBottom: 10 }}
        >
          <Button
            variant="contained"
            color="primary"
            onClick={this.props.cns}
            style={{ paddingLeft: 70, paddingRight: 70 }}
          >
            Add
          </Button>
        </div>
      </form>
    );
  }
  
}
export default  withStyles(useStyles)(AddSchedules);