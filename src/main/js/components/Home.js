
import { Switch, Route } from 'react-router-dom'
const React = require('react');
import { Link } from 'react-router-dom'
const TranslationForm = require("./translation/TranslationForm");

class Home extends React.Component {
	render (){
		return (
		  <TranslationForm/>
		  );
	}
}

module.exports = Home;
