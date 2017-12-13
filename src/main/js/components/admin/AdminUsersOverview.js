"strict mode";
const React = require('react');
const client = require('../../client');
const AdminUserList = require('./AdminUserList');
import { Link } from 'react-router-dom'


class AdminUsersOverview extends React.Component {

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
					<AdminUserList users={this.state.users}/>
					<AdminUserList users={this.state.admins}/>
				</div>
				)
	}
}

module.exports = AdminUsersOverview;
