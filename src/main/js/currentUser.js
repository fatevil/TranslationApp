import { BrowserRouter } from 'react-router-dom'
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

module.exports = function currentUser() {
	return client({method: 'POST', path: '/current'});
};
