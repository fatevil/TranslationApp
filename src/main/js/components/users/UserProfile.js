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
module.exports = UserProfile;
