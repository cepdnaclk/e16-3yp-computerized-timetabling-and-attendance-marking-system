import React, { Component } from 'react';
import editIcon from "../images/edit.svg";
import "../css/userCard.css";

class InputField extends Component {
    state = {  }

    editCheck = () => {
        if(this.props.nameTag !== "E Number:") return (
            <a className="editIcon" href="test"><img src={editIcon}></img></a>
            );
        
    }

    checkValueField = () => {

        var finalString = "";
        if(this.props.nameTag !== "Password:") finalString += this.props.value;
        else {
            var i;
            for(i=0 ; i< this.props.value.length;i++) finalString += "*";
        }
        return <h5 className="value">{finalString}</h5>;

    }

  

    render() { 
        return (  
            <div className="inputField">
                <h5 className="nameTag">{this.props.nameTag}</h5>
                {this.editCheck()}
                {this.checkValueField()}
            </div>
        );
    }
}

 
export default InputField;