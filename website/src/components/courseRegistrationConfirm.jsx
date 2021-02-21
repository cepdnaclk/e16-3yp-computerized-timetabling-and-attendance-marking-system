import React, { Component } from "react";
import FormControl from "@material-ui/core/FormControl";
import { InputLabel } from "@material-ui/core";
import { Input } from "@material-ui/core";
import { Container } from "@material-ui/core";
import Button from "@material-ui/core/Button";

class CourseRegistrationConfirm extends Component {
  state = {};

  constructor(props) {
    super(props);
    this.state = {value: ''};
    this.handleChange = this.handleChange.bind(this);
  }


  handleChange(event) {
    this.setState({ value: event.target.value });
    // console.log(this.state.value);
  }

  render() {
    return (
      <Container maxWidth="sm">
        <FormControl>
          <InputLabel htmlFor="my-input">Password</InputLabel>
          <Input
            type="text"
            id="password"
            placeholder=""
            onChange={this.handleChange}
          />
          <Button
            onClick={(e) => this.props.onSubmit(this.state.value)}
            variant="contained"
            color="primary"
          >
            Register
          </Button>
        </FormControl>
      </Container>
    );
  }
}

export default CourseRegistrationConfirm;
