import React, { Component } from 'react';
import "../css/courseCard.css"
import cal from '../images/cal.svg'

class CourseCard extends Component {
    state = {  }

    checkSearchResualts = () => {
        if(this.props.sw.length === 0 || this.props.code.includes(this.props.sw.toUpperCase())){
            return(
            <a class="cc-card">
                <div class="cc-avatar">
                    <img class="cc-image" src={cal} />
                </div>
                <div class="cc-content-container">
                    <h3 class="cc-title">{this.props.code}</h3>
                    <h4 class="cc-content">{this.props.name}</h4>
                </div>
            </a>
            );

        }
        else{
            return <div></div>;
        }
        
    }

    render() { 
            return this.checkSearchResualts();
    }
}
 
export default CourseCard;