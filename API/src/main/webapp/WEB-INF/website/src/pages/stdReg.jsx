import React, { Component } from 'react';
import NavBar from '../components/navbar'
import TextInput from '../components/textInput'
import '../css/stdReg.css'


class StdReg extends Component {
    state = {  }

    constructor(){
        super();
        this.state = {
            f1 : '',
            f2 : '',
            f3 : '',
            f4 : '',
            f5 : '',
            f6 : ''
        }
    }
    
    changeHandler = (event) => {

        let name = event.target.name;
        let value = event.target.value;
        this.setState({[name]:value});

    }

    sendReq = () =>{
        //sent http request using state object
        console.log(this.state)
    }

    render() { 
        return (  
            <React.Fragment>
                <NavBar pageName="Student Registration" />
                    <div className="dataFields">
                        <TextInput tagname="f1" name="Registration Number :" oc={this.changeHandler}></TextInput>
                        <TextInput tagname="f2" name="FirstName :" oc={this.changeHandler}></TextInput>
                        <TextInput tagname="f3" name="LastName :" oc={this.changeHandler}></TextInput>
                        <TextInput tagname="f4" name="Year :" oc={this.changeHandler}></TextInput>
                        <TextInput tagname="f5" name="Semester :" oc={this.changeHandler}></TextInput>
                        <TextInput tagname="f6" name="Department :" oc={this.changeHandler}></TextInput>
                    </div>
                    <button onClick={this.sendReq} className="submitButton">Submit</button>
               
            </React.Fragment>
        );
    }
}
 
export default StdReg;