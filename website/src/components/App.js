import React, { Component } from 'react';
import {Route,BrowserRouter as Router,Switch} from "react-router-dom";
import Home from "../pages/home"
import Login from "../pages/login"
import StdReg from '../pages/stdReg'
import AdminPanel from '../pages/adminPanel'
import DeleteAccounts from '../pages/deleteAccounts'

class App extends Component {

    state = {}
  
    render() { 
        return ( 
            <Router>
                <Switch>
                    <Route path="/home" component={Home}></Route>
                    <Route path="/stdreg" component={StdReg}></Route>
                    <Route path="/adminpanel" component={AdminPanel}></Route>
                    <Route path="/deleteaccounts" component={DeleteAccounts}></Route>
                    <Route path="/" component={Login}></Route>
                    
                </Switch>
            </Router>
         );
    }

}
 
export default App; 