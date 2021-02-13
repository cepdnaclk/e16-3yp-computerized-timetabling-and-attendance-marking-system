import React, { Component } from 'react';
import '../css/lecturerCard.css'
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import PeopleOutlineIcon from '@material-ui/icons/PeopleOutline';
import DateRangeOutlinedIcon from '@material-ui/icons/DateRangeOutlined';

class LecturerCard extends Component {
  state = {  }

  render() { 
    
    return ( 
      <div className="userCard-outer">
      <div class="background"></div>
        <div class="profile-card">
          <div class="cover"></div>
          <div class="profile">
            <div class="pic">
            </div>
            <div class="above-fold">
              <div class="name">
                {this.props.data}
              </div>
              <div class="role">
                <TextField id="outlined-search" label="Course Code" type="search" variant="outlined" color="secondary"
                onChange={ e => this.props.oc(e)}/>
              </div>
              
              <div class="row">
                <Button variant="outlined" color="secondary"
                startIcon={<PeopleOutlineIcon></PeopleOutlineIcon>}>
                  Student Groups
                </Button>
                <Button variant="outlined" color="secondary"
                startIcon={<DateRangeOutlinedIcon></DateRangeOutlinedIcon>}>
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