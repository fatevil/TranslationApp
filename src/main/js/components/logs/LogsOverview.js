"strict mode";
import { BrowserRouter } from 'react-router-dom'
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('../../client');
const currentUser = require('../../currentUser');
const LoggedEventTable = require("./LoggedEventTable")
const LoggedEventRow = require("./LoggedEventRow")
const putTogether = require('../../util/arrayUtils').putTogether;

class LogsOverview extends React.Component {

	constructor(props) {
		super(props);	
	    this.state = {loggedEvents: []};
	}
	
	componentDidMount() {
		console.log("lets do it");
		currentUser().done(response => {
			client({method: 'POST', path: '/eventsWithAdmins'}).done(response => {
				if (response.status.code == 200) {
					this.state.loggedEvents = response.entity;
					this.forceUpdate();
				}
			});	
		});		
	}

	render() {
		let i = 0;
		let eventRows = this.state.loggedEvents.map(event => {
			i++;
			let keyVar = "event" + i;	
			return <LoggedEventRow key={keyVar} loggedEvent={event}/>
		});	
		return (
				<div className="">		  	
					<LoggedEventTable loggedEvents={eventRows}/>
				</div>
				);
	}
}

module.exports = LogsOverview;
