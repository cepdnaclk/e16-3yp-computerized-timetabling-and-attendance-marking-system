import React, { useState, Component } from 'react'
import NavBar from '../components/navbar'
import TextField from '@material-ui/core/TextField';
import GroupCard from '../components/groupCard'
import GroupDetailsCard from '../components/groupDetailsCard'
import '../css/studentgroups.css'
import '../css/home.css'
import bgImage from '../images/bg4.jpg'
import axios from 'axios';
import Controls from "../components/controls/Controls";

const GET_GROUP_NAMES_URI = "admin/groups/all"


class StudentGroups extends Component {

    
    

    state = {  
        groupNames :false,
        searchWord:null,
        numberList:[],
        searcStudent:null,
        msg:'',
        sev:''
    };

    handleCallback = (childData) =>{
       this.setState({msg:childData.msg,
        sev:childData.sev})
        console.log(this.state.groupNames);
       const newList = this.state.groupNames.splice(this.state.groupNames.indexOf(childData.value), 1);
       const groupName = this.state.groupNames;
       this.setState({groupNames:groupName});
    }

    handleCreateGroup = (childData) =>{
        this.setState({msg:childData.msg,
         sev:childData.sev,})

        const groupNames=this.state.groupNames.concat(childData.groupName)
        this.setState({groupNames:groupNames});
     }


    componentDidMount(){
        
        const auth = "Bearer "+ localStorage.getItem('token');

        axios.get(GET_GROUP_NAMES_URI, {
            headers: {
                'Authorization': auth
            }
            })
            .then(
                (res)=>{
                    console.log(res)
                    this.callBack(res.data);
                }
            )
            .catch(e=>{
                console.log(e);
            })
    }

    onSerchValueChanged = e => {
        this.setState({searchWord : e.target.value});
    }

    onSerchStudentChanged = e => {
        this.setState({searcStudent : e.target.value});
        //console.log(e.target.value);
    }

    

    callBack(data){
        if(data){
           this.setState({  groupNames:data,
                            searchWord:'',
                            isLoading:false
                        }); 
        }
    }

    


    render() { 
        return this.state.groupNames && ( 
            <React.Fragment>

                 <NavBar pageName="Student Groups" />
                 <img src={bgImage} className="homeloginImg" alt="background image"></img>

                
                 <div className="stdgps-search">
                    <div className="stdgps-search-outer">
                        <TextField id="outlined-search" label="Group Name" type="search" variant="outlined" color="secondary"
                        onChange={e => this.onSerchValueChanged(e)}/>
                    </div>
                </div>

                <Controls.MsgTabGroups
                        severity={this.state.sev} 
                        text = {this.state.msg}
                />

                <div className="stdgps-outer">
                    

                    <div className="stdgps-extra">
                        <div className="stdgps-groupList">
                            {this.state.groupNames.map( groupName => <GroupCard key={groupName} groupName={groupName}  sw={this.state.searchWord} callBack={this.handleCallback} ></GroupCard>)}
                        </div>
                    </div>

                    <div className="stdgps-extra">
                        <div className="stdgps-groupdetailscard">
                            <GroupDetailsCard 
                                callBack = {this.handleCreateGroup}
                                groupNames={this.state.groupNames} 
                                numberList={this.state.numberList}
                                searchWord={this.state.searcStudent}
                                changeStudent={this.onSerchStudentChanged}
                            ></GroupDetailsCard>
                        </div>
                    </div>
                </div>

            </React.Fragment>

           
        )
    }
}
 
export default StudentGroups;