"strict mode";
const React = require('react');
const client = require('../../client');
const UserList = require('./UserList');
import { Link } from 'react-router-dom'


class UserOverview extends React.Component {

	constructor(props) {
		super(props);	
		this.state = {users: [], admins: []};
	}

	componentDidMount() {
		client({method: 'GET', path: '/api/users'}).done(response => {
			this.setState({users: response.entity._embedded.users});
			this.setState({admins: response.entity._embedded.admins});
		});
	}

	render() {
		return (
				<div>
					<UserList users={this.state.users}/>
					<UserList users={this.state.admins}/>
				</div>
				)
	}
}

module.exports = UserOverview;
