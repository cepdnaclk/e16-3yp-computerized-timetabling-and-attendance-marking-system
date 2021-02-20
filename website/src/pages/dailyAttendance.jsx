import React, { Component } from 'react';
import NavBar from '../components/navbar'
import DAttendanceTable from '../components/dAttedanceTable'
import '../css/home.css'
import bgImage from '../images/bg4.jpg'

class DailyAttendance extends Component {
    
    state = {  
        attendanceData : [],
        courseCode:null,
        regNo:null,
        name:null
    }

    componentDidMount(){
        this.setState({attendanceData :
            [
                ["2/11","8.00am","90","100"],
                ["2/12","8.00am","85","100"],
                ["2/13","8.00am","100","100"]
            ]
            ,courseCode:"CO321",
            regNo:"E/16/286",
            name:"Nuwan Piyarathne"
        });

    }

    render() { 
        return (  
            <React.Fragment>
                <NavBar pageName="Daily Attendance" />

                <img src={bgImage} className="homeloginImg"></img>

                <div className="atdtble-coursedetails-outer">
                    <div>
                        <div className="atdtble-coursedetails">
                            <p className="atdtble-paras">Course:{this.state.courseCode}</p><br></br>
                            <p className="atdtble-paras">Reg Number:{this.state.regNo}</p><br></br>
                            <p className="atdtble-paras">Name:{this.state.name}</p>
                        </div>
                    </div>
                </div>

                <div className="atdtble-tableouter">
                    <div className="atdtble-tableinner">
                        <DAttendanceTable data={this.state.attendanceData}></DAttendanceTable>
                    </div>
                </div>
            </React.Fragment>
        );
    }
}
 
export default DailyAttendance;