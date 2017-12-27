import { BrowserRouter } from 'react-router-dom'
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('../../client');
import { FormGroup, FormControl, ControlLabel, Button, Panel } from "react-bootstrap/lib"

class TranslationTable extends React.Component{
	
	constructor(props) {
		super(props);
	}

	componentDidMount() {
	}
	
	render() {
		
		return (
			<div className="translation-table">
				{this.props.title &&
					<h3>
						{this.props.title}
					</h3>
				}
				<table className="table table-hover">
				{ this.props.heading && this.props.heading == true &&
					<thead>
						<tr>
							<th>From</th>
							<th>To</th>
							<th>Original</th>
							<th>Translated</th>
							<th></th>
						</tr>
					</thead>
				}
					<tbody>
					{this.props.translations && this.props.translations.length > 0 &&  
						this.props.translations
					}
					</tbody>
				</table>
				{this.props.translations && this.props.translations.length == 0 &&
					<div className="container">
						<h5 className=" text-center">
							The list is empty
						</h5>
					</div>
				}
			</div>
		)
	}
}

module.exports = TranslationTable;
