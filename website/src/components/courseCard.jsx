import React, { Component } from "react";
import "../css/courseCard.css";
import cal from "../images/cal.svg";
import { Redirect } from "react-router";
import { withRouter } from "react-router";
class CourseCard extends Component {
  state = {};

  constructor(props) {
    super(props);
    this.setState({redirect :false})
    this.handleClick = this.handleClick.bind(this);
  }

  checkSearchResults = () => {
    if (
      this.props.sw.length === 0 ||
      this.props.code.includes(this.props.sw.toUpperCase())
    ) {
      return (
        <a className="cc-card" onClick={this.handleClick}>
          <div className="cc-avatar">
            <img className="cc-image" alt="" src={cal} />
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

  handleClick = () => {
    // console.log("clicked ", this.props.course);
    this.setState({redirect:true});
  };

  render() {
    if(this.state.redirect){
      return (
        <Redirect
          to={{
            pathname: this.props.page,
            state: {
              course: this.props.course
            }
          }}
        />
      );
    }

    return this.checkSearchResults();
  }
}

export default withRouter(CourseCard);
