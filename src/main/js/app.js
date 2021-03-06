"strict mode";
import { BrowserRouter } from 'react-router-dom'
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
const UserProfile = require('./components/users/UserProfile');
const Main = require('./components/Main');
const Home = require('./components/Home');
const UserOverview = require('./components/users/UserOverview');	
const Login = require('./auth/Login');
import { Switch, Route } from 'react-router-dom'
const Header = require('./components/Header');
const Friends = require('./components/users/Friends');
const LogsOverview = require('./components/logs/LogsOverview');

class App extends React.Component {

	constructor(props) {
		super(props);	
	}

	render() {
		
		return (
				  <div>
				  		<Header/>
						<Route path='/' exact={true} component={Home}/>  
						<Route path='/user' component={UserProfile}/>
						<Route path="/translate" exact component={Home} />
						<Route path="/friendlist" exact component={Friends} />
						<Route path="/users" exact component={UserOverview} />
						<Route path="/logs" exact component={LogsOverview} />
					</div>
				);
	}
}

ReactDOM.render((
  <BrowserRouter>
    <App />
  </BrowserRouter>
), document.getElementById('react'))
	