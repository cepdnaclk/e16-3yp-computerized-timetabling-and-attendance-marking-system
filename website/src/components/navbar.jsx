import React, { Component } from 'react';
import homeIcon from '../images/home.svg'
import logoutIcon from '../images/logout.svg'
import '../css/navbar.css'

class NavBar extends Component {
     
    render() { 
        return (  
            <nav className ="navbar">
                <span className="pageName">{this.props.pageName}</span>
               <a href="/home" className="home"><img src={homeIcon} alt="homeIcon" className="homeIcon"></img></a>
               <a href="#" className="logout"><img src={logoutIcon} alt="logoutIcon" className="logoutIcon"></img></a>
            </nav>
        );
    }
}
 
export default NavBar;