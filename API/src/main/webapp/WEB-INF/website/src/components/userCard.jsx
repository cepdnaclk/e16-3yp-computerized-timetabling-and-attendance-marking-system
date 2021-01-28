import React, { Component } from 'react';
import "../css/userCard.css";
import userIcon from "../images/profile-user.svg";
import InputField from "./inputField";

class UserCard extends Component {

    state = {
        details:[]
    }

    constructor(){
        super();

        var details = [
            {   
                id:1,
                nameTag:"Name:",
                value:"sample name"

            },
            {   
                id:2,
                nameTag:"E Number:",
                value:"E/xx/xxx"

            },
            {   
                id:3,
                nameTag:"Password:",
                value:"samplePassword"

            }
        ]

        this.state.details = details;

    }


    render(){

        return(
            <div className="userCard">
                <img src={userIcon} alt="userIcon" className="userIcon"/>
                <div className="cardSet">
                    {this.state.details.map( detail=> <InputField key={detail.id} nameTag={detail.nameTag} value={detail.value}></InputField>)}
                </div>
                <a className="logoutButton">Log out</a>
            </div>
        );



    }




}

export default UserCard;