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
		if (!this.props.user) {
			return (
					<div className="col-md-6">
						<h5 className=" text-center">
							The user is not defined!
						</h5>
					</div>
					);
		}
		const user = this.props.user;
		return (
				<div className="col-md-6">
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
