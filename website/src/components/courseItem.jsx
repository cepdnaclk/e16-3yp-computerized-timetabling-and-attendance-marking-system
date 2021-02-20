import React, { Component } from 'react'
import { Checkbox } from "@material-ui/core";
import ListGroup from 'react-bootstrap/ListGroup'
import "../css/courseRegCard.css"

class CourseItem extends Component {
    state = {  }
    render() { 
        return ( <div>
            <ListGroup.Item variant="secondary">
            {this.props.c.courseNumber}&nbsp;:&nbsp;{this.props.c.courseName}
            </ListGroup.Item>
        </div> );
    }
}
 
export default CourseItem;