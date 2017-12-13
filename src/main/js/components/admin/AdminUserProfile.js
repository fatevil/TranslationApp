import React from "react";
const client = require('../../client');
const UserDetailWithoutCollections = require('../users/UserDetailWithoutCollections');
const FormatedDatetime = require('../../util/FormatedDatetime');

class AdminUserProfile extends React.Component{
	
	constructor(props) {
		super(props);
		this.state = {};
	}

	componentDidMount() {
		const eventsLink = this.props.location.state.user._links.loggedEvents;
		if (eventsLink != undefined) {
			client({method: 'GET', path: eventsLink.href}).done(response => {
				this.setState({loggedEvents: response.entity._embedded.loggedEvents});
			});
		}
	}
	
	render() {	
		const user = this.props.location.state.user;
		return (
			<div>
				<UserDetailWithoutCollections user={user}/>
				<TranslationTable translations={user.translations}/>
				{
					(this.state.loggedEvents != undefined) &&
					<LoggedEventTable events={this.state.loggedEvents}/>
				}
			</div>
		);
	}
}


class TranslationTable extends React.Component{
	
	constructor(props) {
		super(props);
	}

	componentDidMount() {
	}
	
	render() {
		var translations = this.props.translations.map(translation => {
			let keyVar = "translation" + translation.id;	
			return <TranslationRow key={keyVar} parent={this} translation={translation}/>
		}
		);
		
		return (
			<table className="table table-hover">
		    	<thead>
					<tr>
						<th>From</th>
						<th>To</th>
						<th>Original</th>
						<th>Translated</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					{translations}
				</tbody>
			</table>
		)
	}
}

class TranslationRow extends React.Component{
	
	constructor(props) {
		super(props);
		this.state = {render: true};
	}
	
	
	handleDelete(e) {
		const parent = this.props.parent;

		client({method: 'DELETE', path: "/api/dummyTranslations/" + this.props.translation.id}).done(response => {
			this.state.render = false;
			this.forceUpdate();
		});
	}
	
	render() {
		const event = this.props.event;
		
		if (this.state.render == false) {
			return null;
		}
		
		return (
			<tr>	
				<td>{this.props.translation.textFrom}</td>
				<td>{this.props.translation.textTo}</td>
				<td>{this.props.translation.textOriginal}</td>
				<td>{this.props.translation.textTranslated}</td>
				<td>
				<button type="button" className="btn btn-danger btn-sm" onClick={(e) => this.handleDelete(e)}>	
		    		Delete
		    	</button>	
				</td>
			</tr>
		)
	}
}

class LoggedEventTable extends React.Component{
	
	constructor(props) {
		super(props);
	}
		
	render() {
		var events = this.props.events.map(event => {
			let keyVar = "event" + event.id;	
			return <LoggedEventRow key={keyVar} event={event}/>
		}
		);
		return (
			<table className="table table-hover">
		    	<thead>
					<tr>
						<th>Created</th>
						<th>Description</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					{events}
				</tbody>
			</table>
		)
	}
}

class LoggedEventRow extends React.Component{
	
	constructor(props) {
		super(props);

	}
	
	render() {
		const event = this.props.event;
		return (
			<tr>	
			
				<td><FormatedDatetime date={event.creationDate} /></td>
				<td>{event.description}</td>
			</tr>
		)
	}
}

module.exports = AdminUserProfile;
