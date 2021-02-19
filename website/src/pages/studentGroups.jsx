import React, { Component } from 'react';
import NavBar from '../components/navbar'
import TextField from '@material-ui/core/TextField';
import GroupCard from '../components/groupCard'
import GroupDetailsCard from '../components/groupDetailsCard'
import '../css/studentgroups.css'
import '../css/home.css'
import bgImage from '../images/bg4.jpg'

class StudentGroups extends Component {
    state = {  
        groupNames :[],
        searchWord:null,
        numberList:[],
        searcStudent:null
    }

    componentDidMount(){
        this.setState({searchWord:''});
        this.setState({searchStudent:''});
        this.setState({groupNames:['E16','E17','E15','E18']});
        this.setState({numberList:['E/16/242','E/16/268','E/15/366','E/17/226']});
    }

    onSerchValueChanged = e => {
        this.setState({searchWord : e.target.value});
    }

    onSerchStudentChanged = e => {
        this.setState({searcStudent : e.target.value});
        //console.log(e.target.value);
    }

    render() { 
        return ( 
            <React.Fragment>
                 <NavBar pageName="Student Groups" />
                 <img src={bgImage} className="homeloginImg"></img>
                 <div className="stdgps-search">
                    <div className="stdgps-search-outer">
                        <TextField id="outlined-search" label="Group Name" type="search" variant="outlined" color="secondary"
                        onChange={e => this.onSerchValueChanged(e)}/>
                    </div>
                </div>

                <div className="stdgps-outer">
                    <div className="stdgps-extra">
                        <div className="stdgps-groupList">
                            {this.state.groupNames.map( groupName => <GroupCard key={groupName} groupName={groupName}  sw={this.state.searchWord}></GroupCard>)}
                        </div>
                    </div>

                    <div className="stdgps-extra">
                        <div className="stdgps-groupdetailscard">
                            <GroupDetailsCard 
                                groupNames={this.state.groupNames} 
                                numberList={this.state.numberList}
                                searchWord={this.state.searcStudent}
                                changeStudent={this.onSerchStudentChanged}
                            ></GroupDetailsCard>
                        </div>
                    </div>
                </div>

            </React.Fragment>

           
         );
    }
}
 
export default StudentGroups;