import React from "react";
import { Link } from 'react-router-dom'
const client = require('../../client');
import { Button } from "react-bootstrap/lib"
const containsUserWithId = require('../../util/arrayUtils').containsUserWithId ;


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
				let alreadyFriends = false;
				if (containsUserWithId(this.props.friends, user.id) || (this.props.myself && user.id == this.props.myself.id)) {
					alreadyFriends = true;
				}
				
				i++;
				let keyVar = "user" + i;
				return <UserRow canRemoveFriend={this.props.canRemoveFriend} addFriend={this.props.addFriend} alreadyFriends={alreadyFriends} canDelete={this.props.canDelete} key={keyVar} user={user}/>
			});
		}
		return (
			<div>
				{this.props.title &&
					<h3>
						{this.props.title}
					</h3>
				}
				<table className="table table-hover">
			    	<thead>
						<tr>
							<th>ID</th>
							<th>Email</th>
							{ this.props.addFriend &&
								<th></th>	
							}
							{ this.props.canDelete &&
								<th></th>	
							}
							{ this.props.canRemoveFriend &&
								<th></th>	
							}
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
	    this.handleAddFriend = this.handleAddFriend.bind(this);
	    this.handleRemoveFriend = this.handleRemoveFriend.bind(this);
		this.state = {render: true, friendsAlready: false};
	}

	componentDidMount() {
	}

	handleDelete(e) {

		client({method: 'DELETE', path: "/api/users/" + this.props.user.id}).done(response => {
			this.state.render = false;
			this.forceUpdate();
		});
	}

	handleRemoveFriend() {
		const requestObject = {method: 'DELETE', path: '/friends', params: {email: this.props.user.email}}; 
		client(requestObject).done(response => {
			this.state.render = false;
			this.forceUpdate();
		});
	}
	
	
	
	handleAddFriend() {
		const requestObject = {method: 'POST', path: '/friends', params: {email: this.props.user.email}}; 
		client(requestObject).done(response => {
		});
		this.state.friendsAlready = true;
		this.forceUpdate();
	}
	
	render() {
		if (this.state.render == false) {
			return null;
		}
		
		return (
			<tr>	
				<td>{this.props.user.id}</td>
				<td  width="50%"><Link to={{pathname: '/user', state: {user:this.props.user}}}>{this.props.user.email}</Link></td>
				<td>
				{ this.props.addFriend && !this.props.alreadyFriends && !this.state.friendsAlready &&	
        			<Button className="btn btn-primary btn-sm" onClick={this.handleAddFriend}>Add</Button>
				}		
				{ this.props.canDelete == true &&
					<button type="button" className="btn btn-danger btn-sm" onClick={(e) => this.handleDelete(e)}>	
				    	Delete
				    </button>		
				}
				{ this.props.canRemoveFriend &&
					<button type="button" className="btn btn-danger btn-sm" onClick={(e) => this.handleRemoveFriend(e)}>	
				    	Remove friend
				    </button>		
				}
				</td>
			</tr>
		)
	}
}

module.exports = UserList;

