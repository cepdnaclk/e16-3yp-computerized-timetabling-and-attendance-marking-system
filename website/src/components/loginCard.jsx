import React, { Component } from 'react';
import "../css/login.css"

class LoginCard extends Component {
    state = {

    }
    render() { 
        return (  
            <div className="loginCard">
                <div style={{ fontSize:10,color: "red"}}> {this.props.loginError} </div>
                <h5 className="text1">LOGIN</h5>
                <div className="body">
                    <h6 className="t1">User Name :</h6>
                    <input name="userName" className="textInput" onChange={this.props.ocn}></input>
                    <div style={{ fontSize:10,color: "red"}}> {this.props.nameError} </div>
                    <h6 className="t2">Password :</h6>
                    <input name="password" className="textInput" onChange={this.props.ocp}></input>
                    <div style={{ fontSize:10,color: "red"}}> {this.props.passError} </div>
                    <br></br>
                    <button className="button" onClick={this.props.sr}>Login</button>
                </div>
                
            </div>
        );
    }
}
 
export default LoginCard;