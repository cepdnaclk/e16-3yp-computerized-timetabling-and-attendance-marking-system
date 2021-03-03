import React, { Component } from "react";
import NavBar from "../components/navbar";
import axios from "axios";
import CourseRegistrationForm from "../components/courseRegistrationForm";
import CourseRegistrationConfirm from "../components/courseRegistrationConfirm";
import { Grid } from "@material-ui/core";
import PageHeader from "../components/PageHeader";
import PeopleOutlineTwoToneIcon from "@material-ui/icons/PeopleOutlineTwoTone";
import { Paper, makeStyles } from "@material-ui/core";

let ALL_COURSES_URL = "/courses/all/";
let CONFIRM_PASSWORD_URL = "/check/password/";

class CourseReg extends Component {
  state = {
    submit: false,
    loading: false,
    password: "",
    passwordError:""
  };

  componentDidMount() {
    const auth = "Bearer " + localStorage.getItem("token");
    axios
      .get(ALL_COURSES_URL, {
        headers: {
          Authorization: auth,
        },
      })
      .then((response) => {
        console.log(response.data);
        this.setState({ courses: response.data }, () => {
          this.setState({ loading: true });
        });
      })
      .catch((error) => {
        console.log("error =", error);
      });
  }

  changeValue = (input) => {
    console.log('input = ',input);
    if(input.value === ''){
      this.setState({passwordError: 'Password must not be empty'});
    }
    else{
      this.setState({passwordError: ''});
    }
    this.setState({password: input.value})
    
  }

  handleSubmit = (password) => {
    password = password.trim();
    // console.log('handleSUbmit called');
    // console.log("password", password);
    if (password === "") {
      this.setState({passwordError: 'Password must not be empty'});
      return;
    }
    let url = CONFIRM_PASSWORD_URL + password;
    // console.log(url);
    const auth = "Bearer " + localStorage.getItem("token");
    axios
      .get(url, {
        headers: {
          Authorization: auth,
        },
      })
      .then((response) => {
        // console.log(response.data);
        // console.log(response.data.result);
        if (response.data.result1 === "True") {
          this.setState({ submit: true });
        } else {
          this.setState({passwordError: 'Wrong Password'});
        }
      })
      .catch((error) => {
        alert("Error !");
      });
  };

  unSetSubmit = () => {
    // console.log('unSetSubmit called');
    this.setState({ submit: false }, this.checkSubmit);
  };

  checkSubmit = () => {
    // console.log('callback submit =',this.state.submit);
  };

  render() {
    if (!this.state.loading) {
      return <div></div>;
    }
    return (
      <div>
        <NavBar pageName="" />
        <PageHeader
          title="Course Registration"
          icon={<PeopleOutlineTwoToneIcon fontSize="large" />}
        />

        <Grid
          container
          spacing={0}
          direction="column"
          alignItems="center"
          justify="center"
        >
          <Grid item xs={6}>
            <CourseRegistrationForm
              courses={this.state.courses}
              check={this.state.submit}
              afterSubmit={this.unSetSubmit}
            />
          </Grid>

          <Grid item xs={6}>
            <CourseRegistrationConfirm onSubmit={this.handleSubmit} changeValue={this.changeValue} passwordError={this.state.passwordError}/>
          </Grid>
        </Grid>
      </div>
    );
  }
}

export default CourseReg;
