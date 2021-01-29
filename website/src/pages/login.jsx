import React, { Component } from 'react';
import efacImg from "../images/efac.jpg"
import "../css/login.css"
import LoginCard from "../components/loginCard"
import PeraLogo from "../images/pera.jpg"
import axios from 'axios'
//import localStorage from 'local-storage'

const LOGIN_REST_API_URL = 'http://localhost:8080/login';
const STUDENT_HOME_PAGE_URI =  'http://localhost:8080/student';

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
        axios.post(LOGIN_REST_API_URL, data)
          .then(function (response) {
              if(response.data.token==null || response.data.role==null){
                console.log("error")
              }
              localStorage.setItem('token', JSON.stringify( response.data.token));
              if(response.data.role=="ROLE_USER"){
                 const auth = "Bearer "+ response.data.token
                 axios.get(STUDENT_HOME_PAGE_URI, {
                   headers: {
                     'Authorization': auth
                   }
                 }).then(function (response){

                    console.log(auth)
                 })

              }
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