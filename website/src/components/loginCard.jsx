import React, { Component } from 'react';
import "../css/login.css"

class LoginCard extends Component {
    state = {  }
    render() { 
        return (  
            <div className="loginCard">
                <h5 className="text1">Sign In</h5>
                <div className="body">
                    <h6 className="t1">Username :</h6>
                    <input name="userName" type="text" className="textInput" onChange={this.props.oc}  placeholder="Your username"></input>
                    <h6 className="t2">Password :</h6>
                    <input type="password" name="password" className="textInput" onChange={this.props.oc}   placeholder="Your password"></input>
                    <br></br>
                    <div className="buttonCover">
                        <button className="button" onClick={this.props.sr}>submit</button>
                    </div>
                </div>
            </div>
        );
    }
}
 
export default LoginCard;