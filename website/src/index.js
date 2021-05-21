import React from "react";
import ReactDOM from "react-dom";
import "bootstrap/dist/css/bootstrap.css";
import App from './components/App'
import axios from 'axios';

axios.defaults.baseURL = 'https://efac-attendance.herokuapp.com';

ReactDOM.render(<App />, document.getElementById("root"));
