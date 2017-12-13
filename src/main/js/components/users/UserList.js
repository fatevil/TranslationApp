import React from "react";
import { Link } from 'react-router-dom'


class UserList extends React.Component{
	
	constructor(props) {
		super(props);
	}

	componentDidMount() {

	}
	
	render() {
		var users = this.props.users.map(user =>
			<UserRow key={user._links.self.href} user={user}/>
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

class UserRow extends React.Component{
	
	constructor(props) {
		super(props);
	}

	componentDidMount() {
	}
	
	render() {
		return (
			<tr>	
				
				<td>{this.props.user.id}</td>
				<td><Link to={{pathname: '/user', state: {user:this.props.user}}}>{this.props.user.email}</Link></td>
				<td>
					TODO: delete	
				</td>
			</tr>
		)
	}
}

module.exports = UserList;

