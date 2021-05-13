import React, { Component } from "react";
import homeIcon from "../images/home.svg";
import logoutIcon from "../images/logout.svg";
import "../css/navbar.css";
import { Route, withRouter } from "react-router-dom";
import Login from "../pages/login";
import Home from "../pages/home";
import AdminPanel from "../pages/adminPanel";
import LecturerDashboard from "../pages/lecturerDashboard";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import PeraLogo from "../images/pera3.png";
class NavBar extends Component {
  constructor(props) {
    super(props);
    this.handleLogout = this.handleLogout.bind(this);
  }

  handleLogout = () => {
    localStorage.clear();
    this.props.history.push("/Login");
  };

  handleHome = () => {
    if (localStorage.getItem("sid")) {
      this.props.history.push("/Home");
    } else if (localStorage.getItem("lfn")) {
      this.props.history.push("/LecturerDashboard");
    } else {
      this.props.history.push("/AdminPanel");
    }
  };

  render() {
    return (
      // <nav className ="navbar">
      //     <span className="pageName">{this.props.pageName}</span>
      //    <a href="#" onClick={this.handleHome} className="home"><img src={homeIcon} alt="homeIcon" className="homeIcon"></img></a>
      //    <a href="#" onClick={this.handleLogout} className="logout"><img src={logoutIcon} alt="logoutIcon" className="logoutIcon"></img></a>
      // </nav>
      <div>
        <Navbar className="my-navbar" variant="dark" fixed="top">
          <Navbar.Brand>
            <img
              src={PeraLogo}
              width="30"
              height="30"
              className="d-inline-block align-top"
              alt="React Bootstrap logo"
            ></img>
            &nbsp;&nbsp;&nbsp;&nbsp;
            {this.props.pageName}
          </Navbar.Brand>
          <Nav className="my-nav mr-auto">
            <Nav.Link href="#" onClick={this.handleHome}>
              Home
            </Nav.Link>
          </Nav>

          <Nav.Link href="#" onClick={this.handleLogout}>
            Logout
          </Nav.Link>
        </Navbar>
      </div>
    );
  }
}

export default withRouter(NavBar);
