import React from "react";
const client = require('../../client');
const UserDetailWithoutCollections = require('./UserDetailWithoutCollections');
const TranslationTable = require("./../translation/TranslationTable");
const TranslationRow = require("./../translation/TranslationRow");
const currentUser = require('../../currentUser');


class UserProfile extends React.Component{
	constructor(props) {
		super(props);
		this.state = {translations: []};
		if (this.props.location && this.props.location.state && this.props.location.state.user){ 
			this.state.user = this.props.locations.state.user;
		}
		this.loadTranslations = this.loadTranslations.bind(this);
	}

	componentDidMount() {
		let user;
		if (!this.state.user) {
			console.log("none set up");
			
			let userPromise = currentUser();
			console.log(userPromise);
			
			user = userPromise.then(response => {
				return response.entity;
			}).then(user => {
				loadTranslations(user.id);
			});		
		} else {
			user = this.props.location.state.user;
			this.loadTranslations(user.id);
		}
	}
	
	loadTranslations	(userId) {
		client({method: 'GET', path: '/api/users/' + userId + "/translations"}).done(response => {
			this.state.translations = response.entity._embedded.dummyTranslations;
			this.forceUpdate();
		});	
	}
	
	render() {
		if (!this.state.user) {
			return <div/>;
		}
		
		const user = this.props.location.state.user;
		let i = 0;
		let translationRows = [];
		if (this.state.translations) {
			translationRows = this.state.translations.map(translation => {
				i++;
				let keyVar = "translation" + i;	
				return <TranslationRow key={keyVar} translation={translation}/>
			}
			);	
		}

		return (
			<div>
				<UserDetailWithoutCollections user={user}/>
				<TranslationTable heading={true} title="History of translations" translations={translationRows}/>
			</div>
		);
	}
}
module.exports = UserProfile;
