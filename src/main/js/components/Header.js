import { Switch, Route } from 'react-router-dom'
const React = require('react');
import { Link } from 'react-router-dom'
import { Button } from "react-bootstrap/lib"

class Header extends React.Component {
	render (){
		return (
				<div className="header">
					<Link to={{ pathname: '/translate' }}>
						<Button bsStyle="link">Search for translation</Button>
					</Link>
			    	<Link to={{ pathname: '/friendlist' }}>
			    		<Button bsStyle="link">Friend list</Button>
			    	</Link>	 
				</div>
		  );
	}
}

module.exports = Header;
