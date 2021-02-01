import React, { Component } from 'react';
import "../css/login.css"

class LoginCard extends Component {
    state = {

    }
    render() { 
        return (  
            <div className="loginCard">
                <div style={{ fontSize:10,color: "red"}}> {this.props.loginError} </div>
                <h5 className="text1">Log In</h5>
                <div className="body">
                    <h6 className="t1">User Name :</h6>
                    <input name="userName" type="text" className="textInput" onChange={this.props.ocn}  placeholder="Your username"></input>
                    <div style={{ fontSize:10,color: "red"}}> {this.props.nameError} </div>
                    <h6 className="t2">Password :</h6>
                    <input type="password" name="password" className="textInput" onChange={this.props.ocp}   placeholder="Your password"></input>
                    <div style={{ fontSize:10,color: "red"}}> {this.props.passError} </div>
                    <br></br>
                    <div className="buttonCover">
                        <button className="button" onClick={this.props.sr}>Go</button>
                    </div>
                </div>
                
             </div>
        );
    }
}
 
export default LoginCard;