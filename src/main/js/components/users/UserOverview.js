"strict mode";
const React = require('react');
const client = require('../../client');
const UserList = require('./UserList');
import { Link } from 'react-router-dom'
const currentUser = require('../../currentUser');

class UserOverview extends React.Component {

	constructor(props) {
		super(props);	
		this.state = {users: [], admins: [], canDelete: false, userFriends: [], adminFriends: []};
	}

	componentDidMount() {
		currentUser().done(response => {
			console.log(response.entity);
			this.state.myself = response.entity;
			this.state.canDelete = response.entity.admin;
			client({method: 'GET', path: '/api/users'}).then(response => {
				this.setState({users: response.entity._embedded.users});
				this.setState({admins: response.entity._embedded.admins});
			}).then(() => {
				client({method: 'GET', path: '/api/users/' + response.entity.id + "/friends"}).then(response => {
					if (response.entity._embedded) {
						if (response.entity._embedded.users) {	
						    this.state.userFriends = response.entity._embedded.users;
						}
						if (response.entity._embedded.admins) {
							this.state.adminFriends = response.entity._embedded.admins;
						}
					}
				}).then(() => {
					this.state.addFriend = true;
					this.forceUpdate();
				});
			});	
			
				
		});
	}

	
	
	render() {
		return (
				<div>
					<UserList myself={this.state.myself} addFriend={this.state.addFriend} friends={this.state.userFriends} canDelete={this.state.canDelete} title="Users" users={this.state.users}/>
					<UserList myself={this.state.myself} addFriend={this.state.addFriend} friends={this.state.adminFriends} canDelete={this.state.canDelete} title="Administrators" users={this.state.admins}/>
				</div>
				)
	}
}

module.exports = UserOverview;
