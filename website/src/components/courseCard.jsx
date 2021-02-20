import React, { Component } from "react";
import "../css/courseCard.css";
import cal from "../images/cal.svg";

class CourseCard extends Component {
  state = {};

  checkSearchResualts = () => {
    if (
      this.props.sw.length === 0 ||
      this.props.code.includes(this.props.sw.toUpperCase())
    ) {
      return (
        <a className="cc-card">
          <div className="cc-avatar">
            <img className="cc-image" src={cal} />
          </div>
          <div className="cc-content-container">
            <h3 className="cc-title">{this.props.code}</h3>
            <h4 className="cc-content">{this.props.name}</h4>
          </div>
        </a>
      );
    } else {
      return <div></div>;
    }
  };

  render() {
    return this.checkSearchResualts();
  }
}

export default CourseCard;
