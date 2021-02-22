import React, { useState, useEffect } from 'react'
import { Grid, } from '@material-ui/core';
import Controls from "../components/controls/Controls";
import { useForm, Form } from '../components/useForm';
import * as StudentService from "../services/StudentService";



const initialFValues = {
    userName : '',
    firstName: '',
    lastName:'',
    email: '',
    departmentId: '',
    
}

export default function EmployeeForm() {

    //check validations
    const validate = (fieldValues = values) => {
        let temp = { ...errors }
        if ('firstName' in fieldValues)
            temp.firstName = (fieldValues.firstName ) ? "" : "This field is required."

        if ('userName' in fieldValues)
        temp.userName = (fieldValues.userName ) ? "" : "This field is required."

        if ('lastName' in fieldValues)
            temp.lastName = (fieldValues.lastName) ? "" : "This field is required."
            
        if ('email' in fieldValues)
            temp.email = (/^[ ]*([^@\s]+)@((?:[-a-z0-9]+\.)+[a-z]{2,})[ ]*$/i).test(fieldValues.email) ? "" : "Email is not valid."

        if ('departmentId' in fieldValues)
            temp.departmentId = fieldValues.departmentId.length != 0 ? "" : "This field is required."

        setErrors({
            ...temp
        })

        if (fieldValues == values)
            return Object.values(temp).every(x => x == "")
    }

    const {
        values,
        setValues,
        errors,
        setErrors,
        handleInputChange,
        resetForm
    } = useForm(initialFValues, true, validate);

    const handleSubmit = e => {
        e.preventDefault()
        if (validate()){
            //call reqs
            resetForm()
        }
    }

    return (
        <Form onSubmit={handleSubmit}>
            <Grid container>
                <Grid item xs={6}>

                    <Controls.Input
                            name="userName"
                            label="User Name"
                            value={values.firstName}
                            onChange={handleInputChange}
                            error={errors.firstName}
                    />

                    <Controls.Input
                        name="firstName"
                        label="First Name"
                        value={values.firstName}
                        onChange={handleInputChange}
                        error={errors.firstName}
                    />
                    <Controls.Input
                        name="lastName"
                        label="Last Name"
                        value={values.lastName}
                        onChange={handleInputChange}
                        error={errors.lastName}
                    />
                    <Controls.Input
                        label="Email"
                        name="email"
                        value={values.email}
                        onChange={handleInputChange}
                        error={errors.email}
                    />

                    <Controls.Select
                        name="departmentId"
                        label="Department"
                        value={values.departmentId}
                        onChange={handleInputChange}
                        options={StudentService.getDepartmentCollection()}
                        error={errors.departmentId}
                    />

                    <div>
                        <Controls.Button
                            type="submit"
                            text="Submit"
                            style={{backgroundColor: "#253053"}} />
                        <Controls.Button
                            text="Reset"
                            color="default"
                            onClick={resetForm} />
                    </div>
                </Grid>
            </Grid>
        </Form>
    )
}