import { Switch, Route } from 'react-router-dom'
const React = require('react');
const Home = require('./Home');
const UserOverview = require('./users/UserOverview');
import { Link } from 'react-router-dom'

class Main extends React.Component {
	render (){
		return (
				<div>
				
		      <ul>
			       <li><Link to='/'>Home</Link></li>
			       <li><Link to='/useroverview'>Roster</Link></li>
			       <li><Link to='/home'>Schedule</Link></li>
		       </ul>
		       </div>
		  );
	}
}

module.exports = Main;
