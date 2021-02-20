import React, { Component } from 'react';
import NavBar from '../components/navbar'
import AttendanceTable from '../components/attendanceTable'
import '../css/attendance.css'
import '../css/home.css'
import bgImage from '../images/bg4.jpg'

class Attendance extends Component {
    state = {  
        attendanceData : [],
        courseCode:null,
        studentCount:null
    }

    componentDidMount(){
        this.setState({attendanceData :
            [
                ["E/16/286","Nuwan Piyarathne","90","100"],
                ["E/16/286","Saubhagya Munasinghe","85","100"],
                ["E/16/399","Erandana Wijerathne","100","100"]
            ]
            ,courseCode:"CO321",
            studentCount:60
        });

    }

    render() { 
        return (  
            <React.Fragment>
                <NavBar pageName="Attendance" />

                <img src={bgImage} className="homeloginImg"></img>

                <div className="atdtble-coursedetails-outer">
                    <div>
                        <div className="atdtble-coursedetails">
                            <p className="atdtble-paras">Course:{this.state.courseCode}</p><br></br>
                            <p className="atdtble-paras">No of Students:{this.state.studentCount}</p>
                        </div>
                    </div>
                </div>

                <div className="atdtble-tableouter">
                    <div className="atdtble-tableinner">
                        <AttendanceTable data={this.state.attendanceData}></AttendanceTable>
                    </div>
                </div>
            
            </React.Fragment>
        );
    }
}
 
export default Attendance;