import React, { Component } from 'react';

class NewTestComp extends Component {

    constructor(props){
        super(props);
        console.log(props.location.state)
    }

    render(){

        return <p>Test</p>;

    }
    
    
}
export default NewTestComp;