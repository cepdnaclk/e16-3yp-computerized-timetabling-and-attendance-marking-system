import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
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

export default function AddSchedules(props){

        const classes = useStyles();
    
        return ( 
            <form className={classes.root} noValidate autoComplete="off" >
                
                    <TextField id="outlined-basic" label="Start time" variant="outlined" color="secondary" name="start" onChange={e=> props.oc(e)} key={1} value={props.start}/><br></br>
                    <TextField id="outlined-basic" label="End time" variant="outlined" color="secondary" name="end" onChange={e=> props.oc(e)} value={props.end}/><br></br>
                    <TextField id="outlined-basic" label="Day" variant="outlined" color="secondary" name="day" onChange={e=> props.oc(e)}  value={props.day}/><br></br>
                    <TextField id="outlined-basic" label="Lecturer" variant="outlined" color="secondary" name="lecturer" onChange={e=> props.oc(e)} value={props.lecturer} /><br></br>
                    <TextField id="outlined-basic" label="Lecture Room" variant="outlined" color="secondary" name="room" onChange={e=> props.oc(e)} value={props.room} /><br></br>
                    <div style={{display:"flex",justifyContent:"center",marginBottom:10}}>
                      <Button variant="contained" color="secondary" onClick={props.cns} style={{paddingLeft:70,paddingRight:70}}>Add</Button>
                    </div>
            </form>
         );
    
}
 
;