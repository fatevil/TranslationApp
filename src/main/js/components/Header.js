import { Switch, Route } from 'react-router-dom'
const React = require('react');
import { Link } from 'react-router-dom'
import { Button } from "react-bootstrap/lib"
const currentUser = require('../currentUser');


class Header extends React.Component {
	constructor(props) {
		super(props);
		this.state = {admin: false};
	}

	componentDidMount() {
		currentUser().then(response => {
			return response.entity;
		}).then(currentUser => {
			this.state.admin = currentUser.admin;
			if (currentUser.admin) {
				this.forceUpdate();
			}
		});		
	}
	
	render (){
		
		return (
				<div className="header">
					{this.state.admin &&
						<h4>Admin </h4>	
					}
					<Link to={{ pathname: '/translate' }}>
						<Button bsStyle="link">Search for translation</Button>
					</Link>
			    	<Link to={{ pathname: '/friendlist' }}>
			    		<Button bsStyle="link">Friend list</Button>
			    	</Link>
				    <Link to={{ pathname: '/user'}}>
			    		<Button bsStyle="link">My Profile</Button>
			    	</Link>
			    	<Link to={{ pathname: '/users'}}>
		    			<Button bsStyle="link">All Users</Button>
		    		</Link>
		    		{this.state.admin &&
		    			<Link to={{ pathname: '/logs'}}>
			    			<Button bsStyle="link">Logs</Button>
			    		</Link>
					}
			    	<a href="/logout">
		    			<Button bsStyle="link" id="logoutButton">Logout</Button>
		    		</a>
				</div>
		  );
	} 
}

module.exports = Header;
