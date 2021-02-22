/*import React, { Component } from 'react';
import NavBar from '../components/navbar'
import TextInput from '../components/textInput'
import '../css/stdReg.css'
import axios from 'axios'


    const REGISTRATION_REST_API_URL = '/admin/registration/student';

    class StdReg extends Component {
        state = {  }

        constructor(){
            super();
            this.state = {
                "regNumber" : '',
                "firstName" : '',
                "lastName" : '',
                "year" : '',
                "semester" : '',
                "email" : '',
                "department" : ''
            }
        }

        changeHandler = (event) => {

            let name = event.target.name;
            let value = event.target.value;
            this.setState({[name]:value});

        }

        sendReq = () =>{

            console.log(this.state);
            let data = this.state;
            const auth = "Bearer "+ localStorage.getItem('token');
            axios.post(REGISTRATION_REST_API_URL, data,{
                headers: {
                  'Authorization': auth
                }
              })
              .then( response => {
                              if(response.data == null){
                                  console.log("error");
                              }
                  console.log(response.status);
                  alert("Successfully Registered");
                }).catch( error => {
                    if (error.response.status!=200){
                          alert("Something Went Wrong");
                          console.log(error.response)
                    }
                });
        }


        render() {
            return (
                <React.Fragment>
                    <NavBar pageName="Student Registration" />
                        <div className="dataFields">
                            <TextInput tagname="regNumber" name="Registration Number :" oc={this.changeHandler}></TextInput>
                            <TextInput tagname="firstName" name="FirstName :" oc={this.changeHandler}></TextInput>
                            <TextInput tagname="lastName" name="LastName :" oc={this.changeHandler}></TextInput>
                            <TextInput tagname="year" name="Year :" oc={this.changeHandler}></TextInput>
                            <TextInput tagname="semester" name="Semester :" oc={this.changeHandler}></TextInput>
                            <TextInput tagname="department" name="Department :" oc={this.changeHandler}></TextInput>
                            <TextInput tagname="email" name="Email :" oc={this.changeHandler}></TextInput>
                        </div>

                        <button style={{ marginTop:150,align:"center"}} onClick={this.sendReq} className="submitButton">Submit</button>
                </React.Fragment>
            );
        }
    }

    export default StdReg;*/

import React, { useState, useEffect } from 'react'
import { Grid, } from '@material-ui/core';
import Controls from "../components/controls/Controls";
import { useForm, Form } from '../components/useForm';
import * as StudentService from "../services/StudentService";


const initialFValues = {
    sem:1,
    firstName: '',
    lastName:'',
    email: '',
    departmentId: '',
    regNumber:'',
    admissionDate: new Date(),
    
}

export default function EmployeeForm() {

    //check validations
    const validate = (fieldValues = values) => {
        let temp = { ...errors }
        if ('firstName' in fieldValues)
            temp.firstName = (fieldValues.firstName ) ? "" : "This field is required."

        if ('lastName' in fieldValues)
            temp.lastName = (fieldValues.lastName) ? "" : "This field is required."
            
        if ('email' in fieldValues)
            temp.email = (/^[ ]*([^@\s]+)@((?:[-a-z0-9]+\.)+[a-z]{2,})[ ]*$/i).test(fieldValues.email) ? "" : "Email is not valid."

        if ('sem' in fieldValues)
            temp.sem = (fieldValues.sem > 8 || fieldValues.sem < 1) ? "Invalid semester" : ""
            
        if ('regNumber' in fieldValues)
            temp.regNumber =("\\bE\\\\d{2}\\\\d{3}\\b").test(fieldValues.regNumber) ? "" : "Registration number is not valid."

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
                
                    <Controls.Input
                        label="Semester"
                        name="sem"
                        type="number"
                        value={values.sem}
                        onChange={handleInputChange}
                        error = {errors.sem}
                    />

                </Grid>
                <Grid item xs={6}>

                    <Controls.Input
                        label="Registration Number"
                        name="regNumber"
                        value={values.regNumber}
                        onChange={handleInputChange}
                        error = {errors.regNumber}
                    />

                    <Controls.Select
                        name="departmentId"
                        label="Department"
                        value={values.departmentId}
                        onChange={handleInputChange}
                        options={StudentService.getDepartmentCollection()}
                        error={errors.departmentId}
                    />
                    <Controls.DatePicker
                        name="admissionDate"
                        label="Date Of Admission"
                        value={values.hireDate}
                        onChange={handleInputChange}
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

