import React, { Component } from 'react';
import groupIcon from '../images/schedule.png'
import Button from '@material-ui/core/Button';
import "../css/lecListCard.css"

class LecListCard extends Component {
    state = {  }
    render() { 
        return ( 
            <div className="llc-outer llc-card" onClick={()=>this.props.func(this.props.lectID)}>
                        
                <div class="llc-avatar">
                    <img class="llc-image" alt="" src={groupIcon} />
                </div>
            
                
                <Button className="llc-para" disableFocusRipple={true} style={{ backgroundColor: 'transparent' ,width:200}} onClick={this.onClickRedirect}>{this.props.groupName}</Button>
                
            </div>
         );
    }
}
 
export default LecListCard;