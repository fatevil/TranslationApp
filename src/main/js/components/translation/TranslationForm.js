"strict mode";
import { BrowserRouter } from 'react-router-dom'
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('../../client');
const currentUser = require('../../currentUser');
const TranslationTable = require("./TranslationTable")
const TranslationRow = require("./TranslationRow")
import { FormGroup, FormControl, ControlLabel, Button, Panel } from "react-bootstrap/lib"
const putTogether = require('../../util/arrayUtils').putTogether;

class TranslationForm extends React.Component {

	constructor(props) {
		super(props);	
	    this.handleClick = this.handleClick.bind(this);

	    this.state = {from: 'cz', to: 'en', engine: 'yandex', text: '', translations: [], user: '', newTranslations: []};
	}
	
	handleClick() {		
		const state = this.state;
		const parameters = {originalText: state.text, textFrom: state.from, textTo: state.to, provider: state.engine};
		const requestObject = {method: 'POST', path: '/translate', params: parameters}; 
		
		client(requestObject).done(response => {
			console.log("translate, done: reponse.entity");
			console.log(response.entity);

		    let newArray = this.state.newTranslations.slice();
		    newArray.push(response.entity);
		    this.setState({newTranslations:newArray});			
		});
	}
	
	componentDidMount() {
		currentUser().done(response => {
			client({method: 'GET', path: '/api/users/' + response.entity.id + "/translations"}).done(response => {
				
				if (response.entity._embedded) {
					if (response.entity._embedded.dummyTranslations) {	
					    putTogether(this.state.translations, response.entity._embedded.dummyTranslations);
					}
					if (response.entity._embedded.yandexTranslations) {
					    putTogether(this.state.translations, response.entity._embedded.yandexTranslations);
					}
				}
				
				this.forceUpdate();
			});	
		});		
	}

	render() {
		let i = 0;
		let translationRows = [];
		if (this.state.translations) {
			translationRows = this.state.translations.map(translation => {
				i++;
				let keyVar = "translation" + i;	
				return <TranslationRow key={keyVar} canDelete={true} translation={translation}/>
			}
			);	
		}
		let newTranslationRows = [];
		if (this.state.newTranslations) {
			newTranslationRows = this.state.newTranslations.map(translation => {
				i++;
				let keyVar = "translation" + i;	
				return <TranslationRow key={keyVar} canDelete={true} translation={translation}/>
			}
			);	
		}
		
		return (
				  <div className="">
				  	<FormGroup controlId="formControlsSelect">
			          <div className="row">
				          <div className="col-md-2 bottom-space text-left font-weight-bold ">
				          	<ControlLabel>Translate from</ControlLabel>
					          <FormControl className="" inputRef={ el => this.inputEl=el }
				              componentClass="select" placeholder="select" onChange={e => {this.state.from = e.target.value} }>
					          	<option value="cz">Čestina</option>
					          	<option value="en">Angličtina</option>  
					          	<option value="de">Němčina</option>
					          </FormControl>
					       </div>
					       <div className="col-md-2 bottom-space text-left font-weight-bold ">
					          <ControlLabel>Translate to</ControlLabel>
					          <FormControl className="" inputRef={ el => this.inputEl=el }
					          componentClass="select" placeholder="select" onChange={e => {this.state.to = e.target.value} }>
					          	<option value="en">Angličtina</option>
					          	<option value="cz">Čestina</option>
					          	<option value="de">Němčina</option>
					          </FormControl>
					       </div>
					       <div className="col-md-3 bottom-space text-left font-weight-bold ">
					          <ControlLabel>Search Engine</ControlLabel>
					          <FormControl className="" inputRef={ el => this.inputEl=el }
					          componentClass="select" placeholder="select" onChange={e => {this.state.engine = e.target.value} }>
					            <option value="yandex">Yandex Translator</option>  
					            <option value="dummy">Dummy Translator</option>
					            <option value="microsoft">Microsoft Translation API</option>
					          </FormControl>
					       </div>
					       <div className="col-md-2 bottom-space text-center">
					          	<div className="row text-center">	
					          		<ControlLabel className=" col-md-12 text-center clear-floating">Go!</ControlLabel>
					          	</div>
					          	<div className="row text-center">
					          		<Button className="clear-floating col-md-12" bsStyle="primary" onClick={this.handleClick}>Translate!</Button>
				          		</div>
								
					       </div>
			          </div>
				       <div className="bottom-space">
				       		<textarea className="form-control" onChange={e => {this.state.text = e.target.value} } rows="3"></textarea>
				       </div>
			        </FormGroup>
					<TranslationTable canDelete={true} translations={newTranslationRows}/>
					<TranslationTable canDelete={true} heading={true} title="History of translations" translations={translationRows}/>
				</div>
				);
	}
}

module.exports = TranslationForm;
