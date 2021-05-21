import React, { Component } from 'react';
import groupIcon from '../images/schedule.png'
import Button from '@material-ui/core/Button';
import "../css/lecListCard.css"

class LecListCard extends Component {
    state = {  }
    render() { 
        return ( 
            <a className="llc-outer llc-card" href="/admintimetable">
                        
                <div class="llc-avatar">
                    <img class="llc-image" alt="" src={groupIcon} />
                </div>
            
                
                <Button className="llc-para" disableFocusRipple={true} style={{ backgroundColor: 'transparent' ,width:200}} >{this.props.groupName}</Button>
                
            </a>
         );
    }
}
 
export default LecListCard;