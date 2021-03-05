import React, { Component } from 'react';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import { makeStyles } from '@material-ui/core/styles';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import AddStudent from './addStudent'
import axios from 'axios';
import { CircularProgress } from '@material-ui/core';

const CREATE_GROUP_URI = "/groups/create"

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

    const [values, setValues] = React.useState({
        groupName: '',
        isLoading:false
    });

   
    const createGroups=()=>{

        setValues({
            ...values,
            isLoading: true
        })

       const auth = "Bearer "+ localStorage.getItem('token');

        axios.post(CREATE_GROUP_URI,values, {
            headers: {
                'Authorization': auth
            }
            })
            .then(
                (res)=>{
                    if(res.status===200){

                        setValues({
                            ...values,
                            isLoading: false
                        })
                        props.callBack(
                            {msg:'Group is successfully created !',
                            Severity:'error',
                            groupName:values.groupName
                        }
                        )

                    }
                    
                   
                }
            )
            .catch(e=>{
                console.log(e);
                setValues({
                    ...values,
                    isLoading: false
                })
                props.callBack(
                    {msg:'Something went Wrong, Deleting Group Try again!',
                    Severity:'error'
                }
                )
            })
            
    }

    const selectionValueChanged = (event) => {
        console.log(event.target.value);
    }


    const handleInputChange = e => {
        const { name, value } = e.target
        
        setValues({
            ...values,
            [name]: value
        })

    }


    return (

        <div className="gdclass">
            <TextField
            
                variant="outlined"
                color="secondary"
                margin="normal"
                required
                onChange={handleInputChange}
                id="groupName"
                label="Group Name"
                name="groupName"
                
        
            />
            <Button
                
                disableFocusRipple={true}
                variant="contained"
                color="secondary"
                style = {{width: 150}}
                onClick={createGroups}
                disabled={values.isLoading}
                
            >
                {values.isLoading ? <CircularProgress style={{'color': 'pink'}} size={24}  />:"Create Group"}
            </Button>

        </div>

    );

}
 