"strict mode";
const React = require('react');
const client = require('../../client');
const UserList = require('./UserList');
import { Link } from 'react-router-dom'
import { FormGroup, FormControl, ControlLabel, Button, Panel } from "react-bootstrap/lib"
const currentUser = require('../../currentUser');
const putTogether = require('../../util/arrayUtils').putTogether;

class Friends extends React.Component {

	constructor(props) {
		super(props);	
		this.state = {friends: [], email: '', canDelete: true};
	    this.handleClick = this.handleClick.bind(this);
	}

	
	componentDidMount() {		
		currentUser().done(response => {
			client({method: 'GET', path: '/api/users/' + response.entity.id + "/friends"}).done(response => {
				if (response.entity._embedded) {
					if (response.entity._embedded.users) {	
					    putTogether(this.state.friends, response.entity._embedded.users);
					}
					if (response.entity._embedded.admins) {
					    putTogether(this.state.friends, response.entity._embedded.admins);
					}
				}
				this.forceUpdate();
			});	
		});			
	}
	
	handleClick() {
		const requestObject = {method: 'POST', path: '/friends', params: {email: this.state.email}}; 
		client(requestObject).done(response => {
			if (response.entity) {
			    let newArray = this.state.friends.slice();    
			    newArray.push(response.entity);   
			    this.setState({friends:newArray});
			}
		});
	}

	render() {
		return (
				<div>
					<div className="row bottom-space">
					       <div className="col-md-4">
					       		<input type="text" className="form-control " onChange={e => {this.state.email = e.target.value} } />
					       </div>
		
					       <div className="">
					        	<Button className="" bsStyle="primary" onClick={this.handleClick}>Add as friend!</Button>
				          	</div>
				     </div>
				
					<UserList canDelete={false} canRemoveFriend={true} users={this.state.friends}/>
				</div>
				)
	}
}

module.exports = Friends;
