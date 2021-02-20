import React, { Component } from 'react';
import IconButton from '@material-ui/core/IconButton';
import DeleteIcon from '@material-ui/icons/Delete';
import '../css/studentCard.css'
import groupIcon from '../images/profile-user.png'

class StudentCard extends Component {
    state = {  }
    render() { 
        return (  
            <a className="gc-outer gc-card">
                    
                        <div class="gc-avatar">
                            <img class="gc-image" src={groupIcon} />
                        </div>
                    
                        
                        <p className="gc-para">{this.props.eNbr}</p>
                        <div className="gc-buttons">
                            <IconButton aria-label="delete">
                                <DeleteIcon />
                            </IconButton>
                        </div>
                        
                </a>
        );
    }
}
 
export default StudentCard;