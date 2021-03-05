import React, { Component } from 'react';
import IconButton from '@material-ui/core/IconButton';
import DeleteIcon from '@material-ui/icons/Delete';
import '../css/studentCard.css'
import groupIcon from '../images/profile-user.png'
import axios from 'axios'


const GROUP_STUDENT_DELETE_URI = "/groups/remove/students"
class StudentCard extends Component {
    state = { 
        eNbr:this.props.eNbr,
        groupName : this.props.grpName
     }

    onClickDelete=()=>{

        this.setState({eNbr:false})
    
        const auth = "Bearer "+ localStorage.getItem('token');
        const data = {
            groupName : this.state.groupName,
            idList :[this.state.eNbr]
        }

        axios.post(GROUP_STUDENT_DELETE_URI,data, {
            headers: {
                'Authorization': auth
            }
            })
            .then(
                (res)=>{
                
                    if(res.status===200){

                        this.props.handleBack(
                            {msg:'Student is Removed Successfully',
                            Severity:'success',
                            value : this.props.eNbr
                            }
                        )

                    }
                
                }
            )
            .catch(e=>{
                console.log(e);
                this.setState({eNbr:this.props.eNbr})
                this.props.handleBack(
                    {msg:'Something went Wrong, Deleting Group Try again!',
                    Severity:'error'
                }
                )
            })
    
    }

    render() { 
        return this.state.eNbr && (  
            <>
                <a className="gc-outer gc-card">
                        
                            <div class="gc-avatar">
                                <img class="gc-image" src={groupIcon} />
                            </div>
                        
                            
                            <p className="gc-para">{this.props.eNbr}</p>
                            <div className="gc-buttons">
                                <IconButton aria-label="delete" disableRipple={true}>
                                    <DeleteIcon onClick={this.onClickDelete} />
                                </IconButton>
                            </div>
                            
                    </a>
            </>
        );
    }
}
 
export default StudentCard;