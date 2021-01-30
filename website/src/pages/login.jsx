import React, { Component } from 'react';
import efacImg from "../images/efac.jpg"
import "../css/login.css"
import LoginCard from "../components/loginCard"
import PeraLogo from "../images/pera3.png"
import axios from 'axios'
import {withRouter} from 'react-router-dom'
import { Redirect } from 'react-router';

const LOGIN_REST_API_URL = 'http://localhost:8080/login';


class Login extends Component {
    state = {  }

    constructor(props){
        super(props);
        this.state = {
            userName: '',
            password: '',
            isLoggedAdmin: false,
            isLoggedStu : false
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
        axios.post(LOGIN_REST_API_URL, data)
          .then( response => {

                if(response.data.token==null && response.data.role==null){
                    console.log("error");

                }
                localStorage.setItem('token', response.data.token);
                if(response.data.role=="ROLE_STUDENT"){
                    this.setState({isLoggedStu:true});

                }
                else if(response.data.role=="ROLE_ADMIN"){
                                    this.setState({isLoggedAdmin:true});

                }
          })
    }

    render() {
        if(this.state.isLoggedStu){
            return <Redirect to = {{pathname:"home"}}/>
        }
        if(this.state.isLoggedAdmin){
                    return <Redirect to = {{pathname:"adminpanel"}}/>
        }
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