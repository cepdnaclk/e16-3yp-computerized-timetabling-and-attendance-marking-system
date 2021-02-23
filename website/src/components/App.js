import React, { Component } from 'react';
import {Route,BrowserRouter as Router,Switch} from "react-router-dom";
import Home from "../pages/home";
import Login from "../pages/login";
import StdReg from '../pages/stdReg';
import AdminPanel from '../pages/adminPanel';
import DeleteAccounts from '../pages/deleteAccounts'; 
import LecReg from '../pages/lecReg';
import AdminReg from '../pages/adminReg';
import LecturerDashboard from '../pages/lecturerDashboard';
import CourseReg from "../pages/courseReg";
import RegisteredCourses from '../pages/registeredCourses';
import StudentGroups from '../pages/studentGroups'
import EditGroups from '../pages/editGroups'
import Attendance from '../pages/attendance'
import DailyAttendance from '../pages/dailyAttendance'
import TimeTable from '../pages/timeTable'
import '../index.css'
import cssBaseline from '@material-ui/core'

class App extends Component {

    state = {}
  
    render() { 
        return (
            <>
            <Router>
                <Switch>
                    <Route path="/home" component={Home}></Route>
                    <Route path="/stdreg" component={StdReg}></Route>
                    <Route path="/adminpanel" component={AdminPanel}></Route>
                    <Route path="/deleteaccounts" component={DeleteAccounts}></Route>
                    <Route path="/lecreg" component={LecReg}></Route>
                    <Route path="/adminreg" component={AdminReg}></Route>
                    <Route path="/lecturerdashboard" component={LecturerDashboard}></Route>
                    <Route path="/coursereg" component={CourseReg}></Route>
                    <Route path="/registeredcourses" component={RegisteredCourses}></Route>
                    <Route path="/studentgroups" component={StudentGroups}></Route>
                    <Route path="/editgroups/:id" component={EditGroups}></Route>
                    <Route path="/attendance" component={Attendance}></Route>
                    <Route path="/dailyattendance" component={DailyAttendance}></Route>
                    <Route path="/timetable" component={TimeTable}></Route>
                    <Route path="/" component={Login}></Route>
                </Switch>
            </Router>
            <cssBaseline/>
            </>
         );
    }

}
 
export default App; 