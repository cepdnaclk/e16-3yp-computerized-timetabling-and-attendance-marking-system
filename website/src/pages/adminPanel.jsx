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
                <div style={{display:"flex",justifyContent:"center"}}>
                    <div className="buttonPanel">

                        <AdminButton t1="CREATE" t2="STUDENT ACCOUNTS" val="1" img={b1}></AdminButton>
                        <AdminButton t1="CREATE" t2="LECTURER ACCOUNTS" val="2" img={b2}></AdminButton>
                        <AdminButton t1="CREATE" t2="ADMIN ACCOUNTS" val="3" img={b3}></AdminButton>
                        <AdminButton t1="DELETE" t2="ACCOUNTS" val="4" img={b4}></AdminButton>
                        <AdminButton t1="UPDATE" t2="STUDENT ACCOUNTS" val="5" img={b5}></AdminButton>
                        <AdminButton t1="MODIFY" t2="TIMETABELS" val="6" img={b6}></AdminButton>

                    </div>
                </div>
            
            </React.Fragment>
         );
    }
}
 
export default AdminPanel;