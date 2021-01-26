import React, { Component } from 'react';
import '../css/textInput.css'

class TextInput extends Component {
    state = {  }
    render() { 
        return (  
            <div className="contner">
                <h6 className="t1">{this.props.name}</h6>
                <input type="text" className="textInput" name={this.props.tagname} onChange={this.props.oc}></input>
            </div>
        );
    }
}
 
export default TextInput;