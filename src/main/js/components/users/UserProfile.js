import React from "react";
const client = require('../../client');
const UserDetailWithoutCollections = require('./UserDetailWithoutCollections');
const TranslationTable = require("./../translation/TranslationTable");
const TranslationRow = require("./../translation/TranslationRow");
const currentUser = require('../../currentUser');
const putTogether = require('../../util/arrayUtils').putTogether;


class UserProfile extends React.Component{
	constructor(props) {
		super(props);
		this.state = {translations: [], canDelete: false};
		if (this.props.location && this.props.location.state && this.props.location.state.user){ 
			this.state.user = this.props.location.state.user;
		}
		this.loadTranslations = this.loadTranslations.bind(this);
	}

	componentDidMount() {
			currentUser().then(response => {
				return response.entity;
			}).then(currentUser => {
				if (!this.state.user) {
					this.state.user = currentUser;
					this.loadTranslations(currentUser.id);
					
					// user can delete his own translations
					this.state.canDelete = true;
				} else {
					let displayedUser = this.props.location.state.user;
					this.state.user = displayedUser;
					this.loadTranslations(displayedUser.id);
					
					//admin can delete translations
					this.state.canDelete = currentUser.admin;
				}		
				
				
			});		
		
	}
	
	loadTranslations(userId) {
		client({method: 'GET', path: '/api/users/' + userId + "/translations"}).done(response => {
			if (response.entity._embedded) {
				if (response.entity._embedded.dummyTranslations) {	
				    putTogether(this.state.translations, response.entity._embedded.dummyTranslations);
				}
				if (response.entity._embedded.yandexTranslations) {
				    putTogether(this.state.translations, response.entity._embedded.yandexTranslations);
				}
			}

			this.forceUpdate();
		});	
	}
	
	render() {
		if (!this.state.user) {
			return <div/>;
		}
		
		let i = 0;
		let translationRows = [];
		if (this.state.translations) {
			translationRows = this.state.translations.map(translation => {
				i++;
				let keyVar = "translation" + i;	
				return <TranslationRow key={keyVar} canDelete={this.state.canDelete} translation={translation}/>
			}
			);	
		}

		return (
			<div>
				<UserDetailWithoutCollections user={this.state.user}/>
				<TranslationTable canDelete={this.state.canDelete} heading={true} title="History of translations" translations={translationRows}/>
			</div>
		);
	}
}
module.exports = UserProfile;
