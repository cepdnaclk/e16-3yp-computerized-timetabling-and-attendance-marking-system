import React, { Component } from 'react';
import '../css/adminButton.css'

class AdminButton extends Component {
    state = {  }

    render() {

        let url = 'http://localhost:3000/'; 
        if(this.props.val === '1'){
            url += 'stdreg'; 
        }
        else if(this.props.val === '4'){
            url += 'deleteaccounts';
        }
        else if(this.props.val === '2'){
            url += 'lecreg';
        }
        else if(this.props.val === '3'){
            url += 'adminreg';
        }
        else if(this.props.val === '5'){
            url += 'studentgroups';
        }
        else if(this.props.val === '6'){
            url += 'leclist';
        }
        return ( 

            <a href={url} className="adminButton">
                <h5 className="admint1">{this.props.t1}</h5>
                <img className="buttonImage" src={this.props.img} alt=""></img>
                <h5 className="admint2">{this.props.t2}</h5>
            </a>
         );
    }
}
 
export default AdminButton;