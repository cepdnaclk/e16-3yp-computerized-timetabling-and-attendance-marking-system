import React, { Component } from 'react';
import IconButton from '@material-ui/core/IconButton';
import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';
import groupIcon from '../images/group.png'
import '../css/groupCard.css'
import axios from 'axios';
import Dialog from '@material-ui/core/Dialog';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import Button from '@material-ui/core/Button';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContentText from '@material-ui/core/DialogContentText';
import { Redirect } from 'react-router';
import { useHistory } from "react-router-dom";

const GROUP_NAMES_DELETE_URI = "admin/groups/delete"

class GroupCard extends Component {
    state = { 
        groupName:this.props.groupName,
        open:false,
        isRedirect:false
    }

    onCloseDialog=()=>{

        this.setState({open:false})

    }

    onOpenDialog=()=>{

        this.setState({open:true})

    }

    onClickRedirect=()=>{
        this.setState({isRedirect:true})
    }

    onClickDelete=()=>{
        this.setState({open:false})
        const data ={groupName:this.state.groupName}
        this.setState({groupName:false})
        console.log(data);
        const auth = "Bearer "+ localStorage.getItem('token');

        axios.post(GROUP_NAMES_DELETE_URI,data, {
            headers: {
                'Authorization': auth
            }
            })
            .then(
                (res)=>{
                    if(res.status===200){

                        this.props.callBack(
                            {msg:'Group Successfully Deleted',
                            Severity:'success',
                            value : this.props.groupName
                        }
                        )

                    }
                   
                }
                  
            )
            .catch(e=>{
                console.log(e);
                this.setState({groupName:this.props.groupName})
                this.props.callBack(
                    {msg:'Something went Wrong, Deleting Group Try again!',
                    Severity:'error'
                }
                )
            })
    }

    checkSearchResualts = () => {
        if(this.props.sw.length === 0 || this.props.groupName.includes(this.props.sw.toUpperCase())){
            return this.state.groupName && (
                <div>
                    <div>
                        
                        <Dialog
                            open={this.state.open}
                            onClose={this.onCloseDialog}
                            aria-labelledby="alert-dialog-title"
                            aria-describedby="alert-dialog-description"
                        >
                        <DialogTitle id="alert-dialog-title">{"Are you Sure You want Delete this?"}</DialogTitle>
                        <DialogContent>
                        <DialogContentText>
                            This Action Can't be undone
                        </DialogContentText>
                        </DialogContent>
                        <DialogActions>
                        <Button onClick={this.onCloseDialog} color="primary">
                            Cancel
                        </Button>
                        <Button onClick={this.onClickDelete} color="secondary" autoFocus>
                            Ok
                        </Button>
                        </DialogActions>
                        </Dialog>
                    </div>

                    <a className="gc-outer gc-card" >
                        
                            <div class="gc-avatar">
                                <img class="gc-image" alt="" src={groupIcon} />
                            </div>
                        
                            
                            <Button className="gc-para" disableFocusRipple={true} style={{ backgroundColor: 'transparent' }} onClick={this.onClickRedirect}>{this.state.groupName}</Button>
                            <div className="gc-buttons">
                                <IconButton aria-label="delete">
                                    <DeleteIcon onClick={this.onOpenDialog}/>
                                </IconButton>
                            </div>
                            <div className="gc-buttons">
                                <IconButton aria-label="edit">
                                    <EditIcon />
                                </IconButton>
                            
                            </div>
                    </a>
                </div>
            
            );

        }
        else{
            return <div></div>;
        }
                
        
    }

    render() { 
        return this.state.isRedirect ? <Redirect to={{ pathname: "editgroups" , state: { group_id: this.state.groupName } }} />: this.checkSearchResualts();
    }
}
 
export default GroupCard;