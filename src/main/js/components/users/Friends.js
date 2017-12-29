"strict mode";
const React = require('react');
const client = require('../../client');
const UserList = require('./UserList');
import { Link } from 'react-router-dom'
import { FormGroup, FormControl, ControlLabel, Button, Panel } from "react-bootstrap/lib"
const currentUser = require('../../currentUser');


class Friends extends React.Component {

	constructor(props) {
		super(props);	
		this.state = {friends: [], email: ''};
	    this.handleClick = this.handleClick.bind(this);
	}

	
	componentDidMount() {		
		const state = this.state;
		const user = CurrentUser.instance;
		
		client({method: 'GET', path: '/api/users/' + user.id + "/friends"}).done(response => {
			this.state.friends = response.entity._embedded.users;
			this.forceUpdate();
		});	
	}
	
	handleClick() {
		const requestObject = {method: 'POST', path: '/friends', params: {email: this.state.email}}; 
		
		client(requestObject).done(response => {
			console.log(response.entity);
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
				
					<UserList users={this.state.friends}/>
				</div>
				)
	}
}

module.exports = Friends;
