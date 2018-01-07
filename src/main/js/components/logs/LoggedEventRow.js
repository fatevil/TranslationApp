import { BrowserRouter } from 'react-router-dom'
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('../../client');
import { FormGroup, FormControl, ControlLabel, Button, Panel } from "react-bootstrap/lib"
const FormatedDatetime = require('../../util/FormatedDatetime');


class LoggedEventRow extends React.Component{
	
	constructor(props) {
		super(props);		
	}
	
	render() {		
		let event = this.props.loggedEvent;
		if (!event){
			return null;
		}
		return (
			<tr>	
				<td>{event.admin}</td>
				<td><FormatedDatetime date={event.creationDate}/></td>
				<td>{event.description}</td>
				
			</tr>
		)
	}
}
module.exports = LoggedEventRow;
