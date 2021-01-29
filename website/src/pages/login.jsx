import React, { Component } from 'react';
import efacImg from "../images/efac.jpg"
import "../css/login.css"
import LoginCard from "../components/loginCard"
import PeraLogo from "../images/pera.jpg"
import axios from 'axios'

const LOGIN_REST_API_URL = 'http://localhost:8080/login';

class Login extends Component {
    state = {  }

    constructor(){
        super();
        this.state = {
            userName: '',
            password: ''
        }
    }

    changeHandler = (event) => {

        let name = event.target.name;
        let value = event.target.value;
        this.setState({[name]:value});

    }

    sendReq = () =>{
        //sent http request to login using state object
        const data = this.state;
        console.log(data);

        axios.post(LOGIN_REST_API_URL, data)
          .then(function (response) {
            console.log(response);
          })
    }

    render() { 
        return ( 
            <div className="login">
                <img src={efacImg} className="loginImg"></img>
                <img src={PeraLogo} className="logo"></img>
                <h3 className="title1">UNIVERSITY OF PERADENIYA</h3>
                <h3 className="title2">ATTENDANCE MARKING SYSTEM</h3>
                <LoginCard oc={this.changeHandler} sr={this.sendReq}></LoginCard>
            </div>
         );
    }
}
 
export default Login;