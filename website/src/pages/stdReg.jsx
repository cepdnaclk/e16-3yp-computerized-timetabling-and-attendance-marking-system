import React, { Component } from 'react';
import NavBar from '../components/navbar'
import TextInput from '../components/textInput'
import '../css/stdReg.css'
import axios from 'axios'


/*const REGISTRATION_REST_API_URL = 'http://localhost:8080/admin/registration/student';

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
            "department" : '',
            "regNumErr": '',
            "FnameErr":' ',
            "LnameErr":'',
            "yearErr":'',
            "semErr":' ',
            "depErr":'',
            "mailErr":' '
        }
         this.validateField = this.validateField.bind(this);
         this.changeHandlerRegNumber = this.changeHandlerRegNumber.bind(this);
         this.changeHandlerFname = this.changeHandlerFname.bind(this);
         this.changeHandlerLName = this.changeHandlerLName.bind(this);
         this.changeHandlerYear = this.changeHandlerYear.bind(this);
         this.changeHandlerDep = this.changeHandlerDep.bind(this);
         this.changeHandlerMail = this.changeHandlerMail.bind(this);
         this.changeHandlerSem = this.changeHandlerSem.bind(this);

    }

    validateField(field){

           if(field.length == 0){
                 return true
           }
            else{
               return false
            }

    }
    
    changeHandlerRegNumber(event){

       this.setState({ regNumber : event.target.value });
       if(this.validateField(this.state.regNumber)){
            this.setState({regNumErr:"Registration Number Can not be Empty"});
       }
       else{
            this.setState({regNumErr:""});
       }

    }

     changeHandlerFname(event){

       this.setState({ firstName : event.target.value });
       if(this.validateField(this.state.firstName)){
            this.setState({fNameErr:"First Name Can not be Empty"});
       }
       else{
            this.setState({fNameErr:""});
       }
     }

      changeHandlerLName(event){

        this.setState({ lastName : event.target.value });
        if(this.validateField(this.state.lastName)){
             this.setState({LnameErr:"LastName Can not be Empty"});
        }
        else{
             this.setState({LnameErr:""});
        }

      }

      changeHandlerYear(event){

        this.setState({ year : event.target.value });
        if(this.validateField(this.state.year)){
             this.setState({yearErr:"Year Can not be Empty"});
        }
        else{
             this.setState({yearErr:""});
        }
      }

      changeHandlerSem(event){

        this.setState({ semester : event.target.value });
        if(this.validateField(this.state.semester)){
             this.setState({semErr:"Semester Can not be Empty"});
        }
        else{
             this.setState({semErr:""});
        }

      }

      changeHandlerDep(event){

         this.setState({ department : event.target.value });
         if(this.validateField(this.state.department)){
              this.setState({depErr:"Registration Number Can not be Empty"});
         }
         else{
              this.setState({depErr:""});
         }

      }

      changeHandlerMail(event){

         this.setState({ email : event.target.value });
         if(this.validateField(this.state.email)){
              this.setState({mailErr:"Email Can not be Empty"});
         }
         else{
              this.setState({mailErr:""});
         }

      }

    sendReq = () =>{

        let data = this.state;
        const auth = "Bearer "+ localStorage.getItem('token');

        const postData = {
            "semester":data.semester ,
            "firstName":data.firstName,
            "lastName":data.lastName,
            "department":data.department,
            "regNumber":data.regNumber,
            "year":data.year,
            "email":data.email
        }

       axios.post(REGISTRATION_REST_API_URL, postData,{
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

    }/*
    

    render() { 
        return (  
            <React.Fragment>
                <NavBar pageName="Student Registration" />
                    <div className="dataFields">
                       <div>
                            <TextInput tagname="regNumber" name="Registration Number :" oc={this.changeHandlerRegNumber}></TextInput>
                            <div style={{ marginLeft:10,fontSize:10,color: "red"}}> { this.state.regNumErr} </div>
                       </div>
                       <div>
                           <TextInput tagname="firstName" name="FirstName :" oc={this.changeHandlerFname}></TextInput>
                           <div style={{ marginLeft:10,fontSize:10,color: "red"}}> { this.state.fNameErr} </div>
                       </div>
                       <div>
                          <TextInput tagname="lastName" name="LastName :" oc={this.changeHandlerLName}></TextInput>
                          <div style={{ marginLeft:10,fontSize:10,color: "red"}}> { this.state.lastNameErr} </div>
                       </div>
                       <div>
                           <TextInput tagname="year" name="Year :" oc={this.changeHandlerYear}></TextInput>
                           <div style={{ marginLeft:10,fontSize:10,color: "red"}}> { this.state.yearErr}</div>
                       </div>
                       <div>
                            <TextInput tagname="semester" name="Semester :" oc={this.changeHandlerSem}></TextInput>
                            <div style={{ marginLeft:10,fontSize:10,color: "red"}}> { this.state.semErr} </div>
                       </div>
                       <div>
                            <TextInput tagname="department" name="Department :" oc={this.changeHandlerDep}></TextInput>
                            <div style={{ marginLeft:10,fontSize:10,color: "red"}}> { this.state.depErr} </div>
                       </div>
                        <div>
                            <TextInput tagname="email" name="Email :" oc={this.changeHandlerMail}></TextInput>
                            <div style={{ marginLeft:10,fontSize:10,color: "red"}}> { this.state.mailErr} </div>
                        </div>
                    </div>

                    <button style={{ marginTop:150,align:"center"}} onClick={this.sendReq} className="submitButton">Submit</button>
            </React.Fragment>
        );
    }*/

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

    export default StdReg;
