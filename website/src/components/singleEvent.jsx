import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Popover from '@material-ui/core/Popover';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';

const useStyles = makeStyles((theme) => ({
  typography: {
    padding: theme.spacing(2),
  },
  root: {
    '& > *': {
      margin: theme.spacing(1),
      width: '25ch',
    },
  },
}));

export default function SingleEvent(props) {

  const classes = useStyles();
  const [anchorEl, setAnchorEl] = React.useState(null);

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const sendReq = ()=> {
      console.log("hi");
  }

  const open = Boolean(anchorEl);
  const id = open ? 'simple-popover' : undefined;
    
    
        return ( 
            <li className="single-event" data-start={props.start} data-end={props.end} data-content={props.content} data-event={props.eventType}>
                <a href="#" onClick={handleClick}>
                    <em className="event-name" style={{fontSize:17}}>{props.eventName}</em>
                    <em className style={{fontSize:17,color:"white"}}>{props.roomNo}</em>
                </a>

                <Popover
                    id={id}
                    open={open}
                    anchorEl={anchorEl}
                    onClose={handleClose}
                    anchorOrigin={{
                    vertical: 'bottom',
                    horizontal: 'center',
                    }}
                    transformOrigin={{
                    vertical: 'top',
                    horizontal: 'center',
                    }}

                >
                <form className={classes.root} noValidate autoComplete="off" >
                
                    <TextField id="outlined-basic" label="Start time" variant="outlined" color="secondary"/><br></br>
                    <TextField id="outlined-basic" label="End time" variant="outlined" color="secondary"/><br></br>
                    <TextField id="outlined-basic" label="Day" variant="outlined" color="secondary"/><br></br>
                    <TextField id="outlined-basic" label="Lecture Room" variant="outlined" color="secondary"/><br></br>
                    <div style={{display:"flex",justifyContent:"center",gap:8}}>
                      <Button onClick={sendReq} variant="contained" color="secondary" >Submit</Button>
                      <Button variant="contained" color="secondary" >Delete</Button>
                      </div>
                </form>
                    
                </Popover>
            </li>
);
    
}