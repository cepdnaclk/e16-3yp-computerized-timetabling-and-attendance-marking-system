import React, { Component } from 'react';
import "../css/userCard.css";
import userIcon from "../images/profile-user.svg";
import InputField from "./inputField";

class UserCard extends Component {

    state = { }



    render(){

        return(
            <div className="userCard">
                <img src={userIcon} alt="userIcon" className="userIcon"/>
                <div className="cardSet">
                    {this.props.data.map( detail=> <InputField  nameTag={detail.nameTag} value={detail.value}></InputField>)}
                </div>
                <a className="logoutButton">Log out</a>
            </div>
        );



    }




}

export default UserCard;