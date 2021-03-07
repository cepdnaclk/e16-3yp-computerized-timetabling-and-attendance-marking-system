import React from "react";



export default function SingleStuEvent(props) {
  const [anchorEl, setAnchorEl] = React.useState(null);
  
  return (
    <li
      className="single-event"
      data-start={props.start}
      data-end={props.end}
      data-content={props.content}
      data-event={props.eventType}
    >
      <a>
        <em className="event-name" style={{ fontSize: 15 }}>
          {props.eventName}
        </em>
        <em className style={{ fontSize: 14, color: "white" }}>
          {props.roomNo}
        </em>
      </a>

      
    </li>
  );
}
