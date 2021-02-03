import React, { Component } from 'react';
import TextInput from '../components/textInput'
import '../css/deleteCard.css'


class DeleteCard extends Component {
    state = {  }
    render() { 
        return ( 
            <div className="deleteCard">
                <h5 className="dbTitle">{this.props.cardName}</h5>
                <div className="dbtextInput">
                    <TextInput tagname={this.props.f} name={this.props.name} oc={this.props.oc}></TextInput>
                    <div className="dcwrapper">
                        <button className="deleteButton" onClick={this.props.sr}>Delete</button>
                    </div>
                    
                </div>
                
            </div>
            
         );
    }
}
 
export default DeleteCard;