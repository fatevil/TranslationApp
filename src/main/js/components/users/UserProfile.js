import React from "react";
const client = require('../../client');
const UserDetailWithoutCollections = require('./UserDetailWithoutCollections');

class UserProfile extends React.Component{
	
	constructor(props) {
		super(props);
	}

	componentDidMount() {
// client({method: 'GET', path: "/api/users/" +
// this.props.match.params.number}).done(response => {
// this.setState({user: response.entity._embedded.user});
// });
	}
	
	render() {	
		const user = this.props.location.state.user;
		console.log(user);
		let i = 0;
		var translations = user.translations.map(translation => {
			i++;
			let keyVar = "translation" + i;	
			return <TranslationRow key={keyVar} translation={translation}/>
		}
		);
		
		// neloadují se translations
		return (
			<div>
				<UserDetailWithoutCollections user={user}/>
				<TranslationTable translations={translations}/>
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
		return (
			<table className="table table-hover">
		    	<thead>
					<tr>
						<th>From</th>
						<th>To</th>
						<th>Original</th>
						<th>Translated</th>
					</tr>
				</thead>
				<tbody>
					{this.props.translations}
				</tbody>
			</table>
		)
	}
}

class TranslationRow extends React.Component{
	
	constructor(props) {
		super(props);
	}

	componentDidMount() {
	}
	
	render() {
		return (
			<tr>	
				<td>{this.props.translation.textFrom}</td>
				<td>{this.props.translation.textTo}</td>
				<td>{this.props.translation.textOriginal}</td>
				<td>{this.props.translation.textTranslated}</td>
				<td>
					TODO: delete	
				</td>
			</tr>
		)
	}
}

module.exports = UserProfile;
