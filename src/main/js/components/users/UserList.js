import React from "react";
import { Link } from 'react-router-dom'
const client = require('../../client');

class UserList extends React.Component{
	
	constructor(props) {
		super(props);
	}

	componentDidMount() {

	}
	
	render() {
		let i = 0;
		let users = [];
		if (this.props.users) {
			 users = this.props.users.map(user => {
				i++;
				let keyVar = "user" + i;
				return <UserRow key={keyVar} user={user}/>
			 }
			);
		}
		return (
			<div>
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
				{users && users.length == 0 &&
					<div className="container">
						<h5 className=" text-center">
							The list is empty
						</h5>
					</div>
				}
			</div>
		)
	}
}

class UserRow extends React.Component{
	
	constructor(props) {
		super(props);
	    this.handleDelete = this.handleDelete.bind(this);
		this.state = {render: true};

	}

	componentDidMount() {
	}

	handleDelete(e) {
		const requestObject = {method: 'DELETE', path: '/friends', params: {email: this.props.user.email}}; 
		client(requestObject).done(response => {
			this.state.render = false;
			console.log(response.entity);
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
				{ this.props.canDelete && this.props.canDelete == true &&
					<td>
						<button type="button" className="btn btn-danger btn-sm" onClick={(e) => this.handleDelete(e)}>	
					    	Delete
					    </button>		
					</td>	
				}
				
			</tr>
		)
	}
}

module.exports = UserList;

