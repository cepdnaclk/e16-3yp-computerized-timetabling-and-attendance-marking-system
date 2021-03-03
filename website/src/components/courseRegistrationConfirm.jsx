import React, { Component } from "react";
import FormControl from "@material-ui/core/FormControl";
import { InputLabel } from "@material-ui/core";
import { Input } from "@material-ui/core";
import Button from "@material-ui/core/Button";
import { Grid } from "@material-ui/core";
import { TextField } from "@material-ui/core";
import { withStyles } from '@material-ui/styles';
import PropTypes from 'prop-types';



class CourseRegistrationConfirm extends Component {
  state = {};

  constructor(props) {
    super(props);
    this.state = { value: "" };
    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(event) {
    this.setState({ value: event.target.value });
    this.props.changeValue({value : event.target.value})
    // console.log(this.state.value);
  }

  render() {
    return (
      <Grid 
      item
      alignItems="center"
      justifyContent="center"
      >
        <form>
        
          <TextField
          error={!!this.props.passwordError}
          required
            variant="outlined"
            label="Password"
            id="password"
            onChange={this.handleChange}
            helperText={this.props.passwordError && this.props.passwordError}
          />
          <br/><br/>
          <div style={{display:"flex",justifyContent:"center"}}>
          <Button
            onClick={(e) => this.props.onSubmit(this.state.value)}
            variant="contained"
            color="primary"
          >
            Register
          </Button>
          </div>
        </form>
      </Grid>
    );
  }
}


export default CourseRegistrationConfirm;
