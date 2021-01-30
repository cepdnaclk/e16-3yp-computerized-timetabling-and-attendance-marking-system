import React, { Component } from 'react';
import NavBar from '../components/navbar'
import AdminButton from '../components/adminButton'
import '../css/adminPanel.css'
import b1 from '../images/studentbt.svg'
import b2 from '../images/lecbt.svg'
import b3 from '../images/adminbt.svg'
import b4 from '../images/deletebt.svg'
import b5 from '../images/upadatebt.svg'
import b6 from '../images/ttbt.svg'

class AdminPanel extends Component {
    state = {  }
    render() { 
        return ( 
            <React.Fragment>
                <NavBar pageName="" />
                <div className="buttonPanel">
                    <AdminButton t1="CREATE" t2="STUDENT ACCOUNTS" img={b1} url="/stdreg"></AdminButton>
                    <AdminButton t1="CREATE" t2="LECTURER ACCOUNTS" img={b2} url="/lecreg"></AdminButton>
                    <AdminButton t1="CREATE" t2="ADMIN ACCOUNTS" img={b3} url="/adminreg"></AdminButton>
                    <AdminButton t1="DELETE" t2="ACCOUNTS" img={b4} url="/deleteaccounts"></AdminButton>
                    <AdminButton t1="UPDATE" t2="STUDENT ACCOUNTS" img={b5}></AdminButton>
                    <AdminButton t1="MODIFY" t2="TIMETABELS" img={b6}></AdminButton>
                </div>
            
            </React.Fragment>
         );
    }
}
 
export default AdminPanel;