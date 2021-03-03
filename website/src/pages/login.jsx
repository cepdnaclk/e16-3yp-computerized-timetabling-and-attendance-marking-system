import React, { Component } from 'react';
import efacImg from "../images/efac.jpg"
import "../css/login.css"
import LoginCard from "../components/loginCard"
import PeraLogo from "../images/pera3.png"
import axios from 'axios'
import {withRouter} from 'react-router-dom'
import { Redirect } from 'react-router';

const LOGIN_REST_API_URL = '/login';
const ID_FROM_SESSION_URL = "/student/getdetailsfromsession";


export default function Login() {
    return ( 
        <div>
            <img src={efacImg} className="loginImg"></img>
            <img src={PeraLogo} className="logo"></img>
            <h3 style={{textAlign:'center',marginTop:"250px",zIndex:3}}><strong>UNIVERSITY OF PERADENIYA</strong></h3>
            <h3 style={{textAlign:'center',zIndex:3}}><strong>ATTENDANCE MARKING SYSTEM</strong></h3>
            <div className="lgnloginCard">
                <LoginCard/>
            </div>
        </div>
      );
}


