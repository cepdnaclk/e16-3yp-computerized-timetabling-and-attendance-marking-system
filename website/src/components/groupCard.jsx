import React, { Component } from 'react';
import IconButton from '@material-ui/core/IconButton';
import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';
import groupIcon from '../images/group.png'
import '../css/groupCard.css'

class GroupCard extends Component {
    state = {  }

    checkSearchResualts = () => {
        if(this.props.sw.length === 0 || this.props.groupName.includes(this.props.sw.toUpperCase())){
            return(
            
                <a className="gc-outer gc-card">
                    
                        <div class="gc-avatar">
                            <img class="gc-image" alt="" src={groupIcon} />
                        </div>
                    
                        
                        <p className="gc-para">{this.props.groupName}</p>
                        <div className="gc-buttons">
                            <IconButton aria-label="delete">
                                <DeleteIcon />
                            </IconButton>
                        </div>
                        <div className="gc-buttons">
                            <IconButton aria-label="edit">
                                <EditIcon />
                            </IconButton>
                        
                    </div>
                </a>
            
            );

        }
        else{
            return <div></div>;
        }
        
    }

    render() { 
        return  this.checkSearchResualts();
    }
}
 
export default GroupCard;