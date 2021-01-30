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
            isLoggedStu : false,
            nameError : "",
            passError : "",
            loginError:""
        }

        this.passChangeHandler= this.passChangeHandler.bind(this);
        this.nameChangeHandler=this.nameChangeHandler.bind(this);
        this.sendReq = this.sendReq.bind(this);
    }

    passChangeHandler = (event) => {

         this.setState({ password : event.target.value });
         if(this.state.password.length == 0){
                    this.setState({passError:"Password Can not be Empty"})
         }
         else{
            this.setState({passError:""})
         }

    }

    nameChangeHandler(event){
        this.setState({ userName : event.target.value });
        if(this.state.userName.length == 0){
              this.setState({nameError:"Username Can not be Empty"})
        }else{
              this.setState({nameError:""})
        }

    }

    sendReq = () =>{
        //sent http request to login using state object
        const data = this.state;

         if(this.state.password.length == 0){
                            this.setState({passError:"Password Can not be Empty"})
                 }
         else{
            this.setState({passError:""})
         }
         if(this.state.userName.length == 0){
                       this.setState({nameError:"Username Can not be Empty"})
         }else{
               this.setState({nameError:""})
         }

        if(data.password&&data.userName){
            axios.post(LOGIN_REST_API_URL, data)
              .then( response => {

                    console.log(response.status)

                    if(response.data.token&&response.data.role){

                        localStorage.setItem('token', response.data.token);
                        if(response.data.role=="ROLE_STUDENT"){
                            this.setState({isLoggedStu:true});

                        }

                        else if(response.data.role=="ROLE_ADMIN"){
                            this.setState({isLoggedAdmin:true});

                        }
                    }

              }).catch( error => {
                 if (error.response.status===401){
                       alert("Username or Password is Incorrect");
                 }

              });

        }

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

                <LoginCard loginError = {this.state.loginError} ocn={this.nameChangeHandler} ocp={this.passChangeHandler} sr={this.sendReq} nameError ={this.state.nameError} passError= {this.state.passError}></LoginCard>
            </div>
         );
    }
}
 
export default Login;