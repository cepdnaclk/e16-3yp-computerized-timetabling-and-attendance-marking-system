import React, { Component } from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import { makeStyles } from '@material-ui/core/styles';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import AddStudent from './addStudent'

 const useStyles = makeStyles((theme) => ({
        formControl: {
            margin: theme.spacing(1),
            minWidth: 120,
        },
        selectEmpty: {
            marginTop: theme.spacing(2),
        },
    }));



export default function GroupDetailsCard(props)  {

    const classes = useStyles();

    const selectionValueChanged = (event) => {
        console.log(event.target.value);
    }


        return (

            <div className="gdclass">
                <TextField
                
                    variant="outlined"
                    color="secondary"
                    margin="normal"
                    required
                    id="groupName"
                    label="Group Name"
                    name="groupName"
                    
            
                />
                <div className="gd-addgroups">
                    <p className="gd-para">Add Groups :</p>
                    <FormControl className={classes.formControl}>
                        <InputLabel id="demo-simple-select-label" color="secondary">Group Name</InputLabel>
                            <Select
                                labelId="demo-simple-select-label"
                                id="demo-simple-select"
                                //value={age}
                                onChange={e => selectionValueChanged(e)}
                                color="secondary"
                                style = {{width: 120}}
                                >
                                <MenuItem value="None">None</MenuItem>
                                {props.groupNames.map( groupName =><MenuItem value={groupName }>{groupName}</MenuItem>)}
                                
                            </Select>
                    </FormControl>
                </div>
                <div className="gd-addStudents-outer">
                    <p className="gd-addStudents">Add Students :</p>
                </div>
                <TextField id="outlined-search"
                    label="Student Id" 
                    type="search" 
                    variant="outlined" 
                    color="secondary"
                    onChange={e => props.changeStudent(e)}/>
                <AddStudent numberList={props.numberList} searchWord={props.searchWord}></AddStudent>

                <Button
                    
                    
                    variant="contained"
                    color="secondary"
                    style = {{width: 150}}
                    
                >
                    CREATE GROUP
                </Button>

            </div>

        );
    
}
 