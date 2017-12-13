"strict mode";
const React = require('react');
const client = require('../../client');
const UserList = require('./UserList');
import { Link } from 'react-router-dom'
const FormatedDatetime = require('../../util/FormatedDatetime');


class UserDetailWithoutCollections extends React.Component {

	constructor(props) {
		super(props);	
	}

	componentDidMount() {
	}

	render() {
		const user = this.props.user;
		return (
				<div className="float-left">
				<dl className="dl-horizontal card-description">

					    <dt>Email</dt>
					    <dd>{user.email}</dd>	
					    <dt>Registered</dt>
					    <dd><FormatedDatetime date={user.creationDate}/></dd>
					</dl>
				</div>
				)
	}
}

module.exports = UserDetailWithoutCollections;
