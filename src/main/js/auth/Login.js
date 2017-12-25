import React, { Component } from "react";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap"; 
	
class Login extends React.Component {
	  constructor(props) {
	    super(props);
	
	    this.state = {
	    		username: "",
	    		password: ""
	    };
	    this.handleChange=this.handleChange.bind(this);
  }

  handleChange(event){
	  
    this.setState({
      [event.target.id]: event.target.value
    });
  }

  /*handleSubmit (event) {
    event.preventDefault();
    console.log("Hi!");
  }*/

  render() {
    return (
      <div className="Login">
        <form onSubmit={this.handleSubmit}>
          <FormGroup controlId="username" bsSize="large">
            <ControlLabel>Username</ControlLabel>
            <FormControl
              autoFocus
              value={this.state.username}
              onChange={this.handleChange}
            />
          </FormGroup>
          <FormGroup controlId="password" bsSize="large">
            <ControlLabel>Password</ControlLabel>
            <FormControl
              value={this.state.password}
              onChange={this.handleChange}
              type="password"
            />
          </FormGroup>
          <Button
            block
            bsSize="large"
            type="submit"
          >
            Login
          </Button>
        </form>
      </div>
    );
  }
}
module.exports = Login;
