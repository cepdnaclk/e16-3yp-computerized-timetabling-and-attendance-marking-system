import React, { Component } from 'react';
import '../css/adminButton.css'

class AdminButton extends Component {
    state = {  }
    render() { 
        return ( 
            <a className="adminButton" href={this.props.url}>
                <h5 className="admint1">{this.props.t1}</h5>
                <img className="buttonImage" src={this.props.img}></img>
                <h5 className="admint2">{this.props.t2}</h5>
            </a>
         );
    }
}
 
export default AdminButton;