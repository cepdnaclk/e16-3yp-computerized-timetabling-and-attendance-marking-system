import React, { Component } from 'react';
import NavBar from '../components/navbar'
import TextField from '@material-ui/core/TextField';
import AddStudent from '../components/addStudent'
import StudentCard from '../components/studentCard'
import bgImage from '../images/bg4.jpg'
import '../css/home.css'
import '../css/editGroups.css'
import axios from 'axios'
import { withRouter } from 'react-router';
import Controls from "../components/controls/Controls";
import Navbar from 'react-bootstrap/Navbar'
import Nav from 'react-bootstrap/Nav'

const GET_GROUP_NAMES_URI = "admin/groups/all/students"

class EditGroups extends Component {

    constructor(props){
        super(props);

    }

    
    
    state = {  
        searchWord:null,
        numberList:[],
        eNumbers:false,
        msg:'',
        sev:''
        
    }

   /* componentDidMount(){
        this.setState({searchWord:''});
        this.setState({numberList:['E/16/242','E/16/268','E/15/366','E/17/226']});
        this.setState({eNumbers:['E/16/243','E/16/267','E/16/367','E/16/225','E/16/229']});
    }*/

    onSearchValueChanged = e => {
        this.setState({searchWord : e.target.value});

        
    }

    componentDidMount(){
        
        const auth = "Bearer "+ localStorage.getItem('token');
        const data = {groupName : this.props.location.state.group_id}

        axios.post(GET_GROUP_NAMES_URI,data, {
            headers: {
                'Authorization': auth
            }
            })
            .then(
                (res)=>{
                    console.log(res)
                    this.setState({eNumbers:res.data.students})
                }
            )
            .catch(e=>{
                console.log(e);
            })
    }


    handleCallback = (childData) =>{
        this.setState({msg:childData.msg,
         sev:childData.sev});
    }




    render() { 
        return this.state.eNumbers && (  
            <React.Fragment>

                <NavBar pageName={this.props.location.state.group_id.toUpperCase()} />
                <img src={bgImage} className="homeloginImg"></img>




                            

                <div className="edtgps-search" style={{marginBottom:"5%"}}>
                    <div className="edtgps-search-outer">
                        <TextField id="outlined-search"
                        label="Student Id" 
                        type="search" 
                        variant="outlined" 
                        color="secondary"
                        onChange={e => this.onSearchValueChanged(e)}/>
                        <AddStudent numberList={this.state.numberList} searchWord={this.state.searchWord}></AddStudent>
                    </div>
                </div>

                <Controls.MsgTabGroups
                        severity={this.state.sev} 
                        text ={this.state.msg}
                />
              

                <div className="edtgps-studentList-outer" style={{marginTop:"0px"}}>
                    <div className="edtgps-studentList">
                        {this.state.eNumbers.map(eNumber => <StudentCard eNbr={eNumber} grpName={this.props.location.state.group_id} handleBack={this.handleCallback}></StudentCard>)}
                    </div>
                </div>
            </React.Fragment>
        );
    }
}
 
export default EditGroups; 