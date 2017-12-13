import React from "react";
import { Link } from 'react-router-dom'
const client = require('../../client');


class AdminUserList extends React.Component{
	
	constructor(props) {
		super(props);
	}

	componentDidMount() {

	}
	
	render() {
		var users = this.props.users.map(user =>
			<AdminUserRow key={user._links.self.href} user={user}/>
		);
		return (
			<table className="table table-hover">
		    	<thead>
					<tr>
						<th>ID</th>
						<th>Email</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					{users}
				</tbody>
			</table>
		)
	}
}

class AdminUserRow extends React.Component{
	
	constructor(props) {
		super(props);
		this.state = {render: true};
	}

	handleDelete(e) {
		const parent = this.props.parent;

		client({method: 'DELETE', path: "/api/users/" + this.props.user.id}).done(response => {
			this.state.render = false;
			this.forceUpdate();
		});
	}
	
	
	render() {
		if (this.state.render == false) {
			return null;
		}
		return (
			<tr>	
				
				<td>{this.props.user.id}</td>
				<td><Link to={{pathname: '/user', state: {user:this.props.user}}}>{this.props.user.email}</Link></td>
				<td>
				<button type="button" className="btn btn-danger btn-sm" onClick={(e) => this.handleDelete(e)}>	
			    	Delete
			    </button>	
				</td>
			</tr>
		)
	}
}

module.exports = AdminUserList;

