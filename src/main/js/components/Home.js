import { Switch, Route } from 'react-router-dom'
const React = require('react');
import { Link } from 'react-router-dom'

class Home extends React.Component {
	render (){
		return (
		  <div><Link to={{ pathname: '/overview' }}>Hi there! </Link></div>
		  );
	}
}

module.exports = Home;
