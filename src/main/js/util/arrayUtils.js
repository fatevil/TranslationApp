import { BrowserRouter } from 'react-router-dom'
const React = require('react');
const ReactDOM = require('react-dom');

module.exports = {
		
		putTogether: function (array1, array2) {
					 	for (let i = 0; i < array2.length; i++) {
					 		array1.push(array2[i]);
					    }
					    return array1;
					},
		containsUserWithId: function (userArray, userId) {
						if(userArray != null && userArray.length >0){
					        for(let i = 0; i < userArray.length; i++){
					            if(userArray[i].id == userId)
					                return true;
					        }
					    }
					    return false;
					}			
};
