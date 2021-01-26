import React, { Component } from 'react';
import "../css/courseCard.css"

class CourseCard extends Component {
    state = {  }
    render() { 
        return ( 
            <a className="courseCard">
                <h3>{this.props.code}</h3>
                <h5>{this.props.name}</h5>
            </a>
         );
    }
}
 
export default CourseCard;