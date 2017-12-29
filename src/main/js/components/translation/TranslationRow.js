import { BrowserRouter } from 'react-router-dom'
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('../../client');
import { FormGroup, FormControl, ControlLabel, Button, Panel } from "react-bootstrap/lib"


class TranslationRow extends React.Component{
	
	constructor(props) {
		super(props);		
		this.state = {render: true, admin: false};

	}

	handleDelete(e) {
		const parent = this.props.parent;

		client({method: 'DELETE', path: "/api/translations/" + this.props.translation.id}).done(response => {
			this.state.render = false;
			this.forceUpdate();
		});
	}
	
	
	render() {		
		if (this.state.render == false) {
			return null;
		}
		return (
			<tr>	
				<td>{this.props.translation.textFrom}</td>
				<td>{this.props.translation.textTo}</td>
				<td>{this.props.translation.textOriginal}</td>
				<td>{this.props.translation.textTranslated}</td>
				<td>
				{ this.props.canDelete && this.props.canDelete == true &&
					<button type="button" className="btn btn-danger btn-sm" onClick={(e) => this.handleDelete(e)}>	
				    	Delete
				    </button>	   	
				}
				</td>
			</tr>
		)
	}
}
module.exports = TranslationRow;
