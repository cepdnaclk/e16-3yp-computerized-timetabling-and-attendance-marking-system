/*import React, { Component } from 'react';
import NavBar from '../components/navbar'
import TextInput from '../components/textInput'
import '../css/lecReg.css'
import axios from 'axios'
const REGISTRATION_REST_API_URL = '/admin/registration/admin';
class AdminReg extends Component {
    
    sendReq = () =>{
        //sent http request using state object
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
                console.log('response',response.data);
          })
    }
}
 
export default AdminReg;*/

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
    import { useForm, AdminForm } from '../components/useForm';
    import PageHeader from "../components/PageHeader";
    import PeopleOutlineTwoToneIcon from '@material-ui/icons/PeopleOutlineTwoTone';
    
    
    const initialFValues = {
        userName: '',
        email: '',
        
    }
    
    export default function EmployeeForm() {
    
        //check validations
        const validate = (fieldValues = values) => {
            let temp = { ...errors }
            if ('userName' in fieldValues)
                temp.userName = (fieldValues.userName ) ? "" : "This field is required."
    
            if ('email' in fieldValues)
                temp.email = (/^[ ]*([^@\s]+)@((?:[-a-z0-9]+\.)+[a-z]{2,})[ ]*$/i).test(fieldValues.email) ? "" : "Email is not valid."
    
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
            <>
                <AdminForm onSubmit={handleSubmit}>
                    <Grid container>
                        <Grid item align="center">
                            <Controls.Input
                                name="userName"
                                label="User Name"
                                value={values.userName}
                                onChange={handleInputChange}
                                error={errors.userName}
                            />
                            
                            <Controls.Input
                                label="Email"
                                name="email"
                                value={values.email}
                                onChange={handleInputChange}
                                error={errors.email}
                            />
                        
                            <div align = "center">
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
                </AdminForm>
            </>
        )
    }
    
    