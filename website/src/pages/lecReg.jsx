import React, { Component } from 'react';
import NavBar from '../components/navbar'
import TextInput from '../components/textInput'
import '../css/lecReg.css'

class LecReg extends Component {
    state = {  }

    constructor(){
        super();
        this.state = {
            f1 : '',
            f2 : '',
            f3 : '',
            f4 : ''
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
                <NavBar pageName="Lecturer Registration" />
                    <div className="lrdataFields">
                        <TextInput tagname="f1" name="UserName :" oc={this.changeHandler}></TextInput>
                        <TextInput tagname="f2" name="Name with Initilas :" oc={this.changeHandler}></TextInput>
                        <TextInput tagname="f3" name="Email :" oc={this.changeHandler}></TextInput>
                        <TextInput tagname="f4" name="Password :" oc={this.changeHandler}></TextInput>
                         </div>
                    <button onClick={this.sendReq} className="lrsubmitButton">Submit</button>
               
            </React.Fragment>
        );
    }
}
 
export default LecReg;