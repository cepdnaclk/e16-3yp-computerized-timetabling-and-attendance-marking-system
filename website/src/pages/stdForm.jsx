import React, { useState, useEffect } from 'react'
import { Grid, } from '@material-ui/core';
import Controls from "../components/controls/Controls";
import { useForm, Form } from '../components/useForm';
import * as StudentService from "../services/StudentService";
import { CircularProgress } from '@material-ui/core';
import axios from 'axios';


const REGISTRATION_REST_API_URL = '/admin/registration/student'


const initialFValues = {
    sem:1,
    firstName: '',
    lastName:'',
    email: '',
    departmentId: '',
    regNumber:'',
    year: new Date(),
    
    
}

const loading = {
    isLoading : false,
    errorMsg:null,
    successMsg:null
}

export default function EmployeeForm() {

    const [errorObj,setLoading] = useState(loading);

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
            temp.regNumber =(/^E\/\d{2}\/\d{3}$/).test(fieldValues.regNumber) ? "" : "Registration number is not valid."

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

    function callBack (data){

        if(data == null){
            console.log("error");
            setLoading( {
                errorMsg:"Something Went Wrong Try again Later!",
                isLoading:false

            });
        }

        else if(data.msg === "user Name is already exists"){
        
            setLoading( {
                errorMsg:"User name is already exists Try with different one!",
                isLoading:false

            });
            
        }
        else if(data.Student){

            setLoading( {
                successMsg:"User Registered successfully!",
                isLoading:false
            });

            resetForm();
            

        }

    }

    const handleSubmit = (e) => {
        
        e.preventDefault()

        if (validate()){
            
            setLoading({
                isLoading:true
            })

            const data = {
                'firstName' : values.firstName,
                'lastName' : values.lastName,
                'email':values.email,
                'department':values.departmentId,
                'semester' : values.sem,
                'regNumber':values.regNumber,
                'year' : values.year.getFullYear()

            }

            console.log(data)

            
           const auth = "Bearer "+ localStorage.getItem('token');

            axios.post(REGISTRATION_REST_API_URL, data,{
                headers: {
                    'Authorization': auth
                }
                }).then
                (
                    function (response){
                        //callback function
                        console.log(response.data);
                        callBack(response.data);
                    }
                ).catch(e =>{

                    if(e.response.status===500){
                        setLoading({

                            errorMsg:"Something Went Wrong Try again Later!",
                            isLoading:false
                        })
                    }
                    else if(e.response.status===401){
                        setLoading({

                            errorMsg:"Session is expired!",
                            isLoading:false
                        })
                    }

                    else if(e.response.status===400){
                        
                        setLoading({

                            errorMsg:e.response.data.errors,
                            isLoading:false
                        })
                    }
                }
            )
        }
        
    }

    return (

        <div>

            <Controls.MsgTab
                severity={(errorObj.successMsg === null) ? '':"success" }
                text =  {errorObj.successMsg}
            />

            <Controls.MsgTab
                severity={(errorObj.errorMsg === null) ? '':"error"}
                text = {errorObj.errorMsg}
            />
    
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
                            name="year"
                            label="Year Of Admission"
                            value={values.year}
                            onChange={handleInputChange}
                            error={errors.year}
                        />



                        <div>
                            <Controls.Button
                                type="submit"
                                text = {
                                    errorObj.isLoading ? 
                                    <CircularProgress size={24}  />:"Submit"                        
                                }
                                disabled = {errorObj.isLoading}
                                style={{backgroundColor: "#253053"}}/>

                            <Controls.Button
                                text="Reset"
                                color="default"
                                onClick={resetForm} />
                        </div>
                    </Grid>
                </Grid>
            </Form>
        </div>
    )
}

