import React, { Component } from "react";
import "../css/lecturerCard.css";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import PeopleOutlineIcon from "@material-ui/icons/PeopleOutline";
import DateRangeOutlinedIcon from "@material-ui/icons/DateRangeOutlined";

class LecturerCard extends Component {
  state = {};

  render() {
    return (
      <div className="userCard-outer">
        <div className="background"></div>
        <div className="profile-card">
          <div className="cover"></div>
          <div className="profile">
            <div className="pic"></div>
            <div className="above-fold">
              <div className="name">{this.props.data}</div>
              <div className="role">
                <TextField
                  id="outlined-search"
                  label="Course Code"
                  type="search"
                  variant="outlined"
                  color="secondary"
                  onChange={(e) => this.props.oc(e)}
                />
              </div>

              <div className="row">
                <Button
                  variant="outlined"
                  color="secondary"
                  startIcon={<PeopleOutlineIcon></PeopleOutlineIcon>}
                >
                  Student Groups
                </Button>
                <Button
                  variant="outlined"
                  color="secondary"
                  startIcon={<DateRangeOutlinedIcon></DateRangeOutlinedIcon>}
                  onClick={event =>  window.location.href='timetable'}
                >
                  My Timetables
                </Button>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default LecturerCard;
