import React from 'react'
import Alert from '@material-ui/lab/Alert';

export default function MagTabGroups(props) {

    const {severity,text} = props;
    return (
        <Alert 
        severity={severity}
        style = {{
            width:"30%",
            align:"center",
            marginTop:"2%",
            marginLeft:"14%",
            opacity:0.5
        }}> <strong>{text}</strong> </Alert>
    )
}