import React, { Component } from 'react';
import NavBar from '../components/navbar'
import TextInput from '../components/textInput'
import '../css/stdReg.css'
import axios from 'axios'


const REGISTRATION_REST_API_URL = 'http://localhost:8080/user/registration/student';

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
                console.log('response',response.data);
          })
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

                    </div>
                    <button onClick={this.sendReq} className="submitButton">Submit</button>
            </React.Fragment>
        );
    }
}
 
export default StdReg;