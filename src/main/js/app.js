"strict mode";
import { BrowserRouter } from 'react-router-dom'
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
const UserProfile = require('./components/users/UserProfile');
const Main = require('./components/Main');
const Home = require('./components/Home');
const UserOverview = require('./components/users/UserOverview');	
const AdminUsersOverview = require('./components/admin/AdminUsersOverview');
const Login = require('./auth/Login');
const AdminUserProfile= require('./components/admin/AdminUserProfile');
import { Switch, Route } from 'react-router-dom'

class App extends React.Component {

	constructor(props) {
		super(props);	
	}


	render() {
		return (
				  <div>
						<Route path='/' exact={true} component={AdminUsersOverview}/>  
						<Route path='/user' component={AdminUserProfile}/>
						<Route path="/login" exact component={Login} />
					</div>
				);
	}
}

ReactDOM.render((
  <BrowserRouter>
    <App />
  </BrowserRouter>
), document.getElementById('react'))
	