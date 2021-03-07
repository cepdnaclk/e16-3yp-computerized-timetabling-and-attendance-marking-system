import React, { Component } from "react";
import "../css/userCard.css";
import Button from "@material-ui/core/Button";
import EditOutlinedIcon from "@material-ui/icons/EditOutlined";
import SubjectIcon from '@material-ui/icons/Subject';
import DateRangeOutlinedIcon from "@material-ui/icons/DateRangeOutlined";

class UserCard extends Component {
  state = {};

  render() {
    console.log(this.props.data[0]);
    return (
      <div className="userCard-outer">
        <div className="background"></div>
        <div className="profile-card">
          <div className="cover"></div>
          <div className="profile">
            <div className="hm-pic"></div>
            <div className="above-fold">
              <div className="name">{this.props.data[0]}</div>
              <div className="role">{this.props.data[1]}</div>

              <div className="row">
                <Button
                  variant="outlined"
                  color="secondary"
                  startIcon={<EditOutlinedIcon></EditOutlinedIcon>}
                >
                  User Name
                </Button>
                <Button
                  variant="outlined"
                  color="secondary"
                  startIcon={<EditOutlinedIcon></EditOutlinedIcon>}
                >
                  Password
                </Button>
              </div>
              <div className="row">
                <Button
                  variant="outlined"
                  color="secondary"
                  startIcon={<SubjectIcon></SubjectIcon>}
                  onClick={event =>  window.location.href='coursereg'}

                >
                  Course Registration
                </Button>
                
              </div>
              <div className="row">
                <Button
                  variant="outlined"
                  color="secondary"
                  startIcon={<DateRangeOutlinedIcon></DateRangeOutlinedIcon>}
                  onClick={event =>  window.location.href='stutimetable'}

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

export default UserCard;
