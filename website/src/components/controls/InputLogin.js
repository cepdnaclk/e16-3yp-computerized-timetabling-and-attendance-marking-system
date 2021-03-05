import React from 'react'
import { TextField } from '@material-ui/core';

export default function InputLogin(props) {

    const { name,type, label, value,error=null, onChange } = props;
    return (
        <TextField
            variant="outlined"
            label={label}
            name={name}
            type={type}
            value={value}
            onChange={onChange}
            fullWidth
            {...(error && {error:true,helperText:error})}
            
        />
    )
}
