import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemAvatar from '@material-ui/core/ListItemAvatar';
import ListItemSecondaryAction from '@material-ui/core/ListItemSecondaryAction';
import ListItemText from '@material-ui/core/ListItemText';
import Avatar from '@material-ui/core/Avatar';
import IconButton from '@material-ui/core/IconButton';
import Typography from '@material-ui/core/Typography';
import EventIcon from '@material-ui/icons/Event';
import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';
import Button from '@material-ui/core/Button';

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
    maxWidth: 900,
  },
  demo: {
    backgroundColor: "none"
  },
  title: {
    margin: theme.spacing(4, 0, 2),
  },
}));

const makeSchedule = (s)=>{

  let tmp = ""
  tmp += "[ Start Time : "+s.startTime+" , End Time : "+s.endTime+" , Day : "+s.dayOfWeek+" ]"
  return tmp


}

function displaySchedules(schedules,ds,es){

    if(schedules.length === 0) return (
      <div style={{display:"flex",justifyContent:"center"}}>
        <Typography variant="p">
            No new schedules
        </Typography>
      </div>
    )
     return (
      schedules.map( (s,index) => 

        
            <ListItem >
                <ListItemAvatar >
                <Avatar>
                    <EventIcon />
                </Avatar>
                </ListItemAvatar>
                <ListItemText
                primary={makeSchedule(s)}
                secondary={null}
                style={{marginRight:35}}
                />
                <ListItemSecondaryAction>
                <IconButton edge="end" aria-label="delete" id={index} onClick={ () => ds(index)}>
                    <DeleteIcon />
                </IconButton>
                <IconButton edge="end" aria-label="edit" id={index} onClick={ () => es(s,index)}>
                    <EditIcon />
                </IconButton>
                </ListItemSecondaryAction>
            </ListItem>
            
    )
     )
     

}

function displayButton(schedules,subs,buttonState){

  if(schedules.length !== 0){
    
    return <Button variant="contained" color="secondary" disabled={buttonState} onClick={subs}>Submit</Button>
                   
  }

}

export default function SubmitSchedules(props){

    const classes = useStyles();
    const [dense] = React.useState(false);
    const [secondary] = React.useState(false);
     
        return (  

            <div className={classes.root}>

            <div style={{display:"flex",justifyContent:"center"}}>
                    <Typography variant="h6" className={classes.title}>
                     <em>Schedules</em>
                    </Typography>
            </div>
                    <div className={classes.demo}>
                    <List>
                        {displaySchedules(props.schedules,props.ds,props.es)}
                    </List>
                    <div style={{display:"flex",justifyContent:"center",marginBottom:10}}>
                    {displayButton(props.schedules,props.subs,props.buttonState)}
                    
                    </div>
                     </div>
                
            
            
            
            </div>
            
        );
    
}
 
