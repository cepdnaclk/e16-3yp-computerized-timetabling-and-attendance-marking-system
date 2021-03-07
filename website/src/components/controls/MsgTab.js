import React from 'react'
import Alert from '@material-ui/lab/Alert';

export default function MagTab(props) {

    const {severity,text} = props;
    return (
        <Alert 
        severity={severity}
        style ={{ display:(text==null)?"none":"flex" , marginBottom:'2%'}} > 
        {text}
        
        </Alert>
    )
}