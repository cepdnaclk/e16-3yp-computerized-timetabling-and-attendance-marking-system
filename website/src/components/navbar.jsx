import React, { Component } from 'react';
import homeIcon from '../images/home.svg'
import logoutIcon from '../images/logout.svg'
import '../css/navbar.css'
import { Route , withRouter} from 'react-router-dom';
import Login from "../pages/login";
import Home from "../pages/home";
import AdminPanel from '../pages/adminPanel';
import LecturerDashboard from '../pages/lecturerDashboard';

class NavBar extends Component {

    constructor(props){
        super(props);
        this.handleLogout = this.handleLogout.bind(this);
    }

    handleLogout = () => {
        localStorage.clear();
        this.props.history.push("/Login");
    };

    handleHome = () => {
        if(localStorage.getItem("sid")){
            this.props.history.push("/Home");
        }
        else if(localStorage.getItem("lid")){
            this.props.history.push("/LecturerDashboard");
        }
        else{
            this.props.history.push("/AdminPanel");
        }
    }

    render() { 

        
        return (  
            <nav className ="navbar">
                <span className="pageName">{this.props.pageName}</span>
               <a href="#" onClick={this.handleHome} className="home"><img src={homeIcon} alt="homeIcon" className="homeIcon"></img></a>
               <a href="#" onClick={this.handleLogout} className="logout"><img src={logoutIcon} alt="logoutIcon" className="logoutIcon"></img></a>
            </nav>
        );
    }
}
 
export default  withRouter (NavBar);