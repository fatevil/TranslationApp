import React  from 'react';
import Moment from 'react-moment';
 
class FormatedDatetime extends React.Component {
    render() {
        return (
            <Moment format="MMMM Do YYYY, hh:mm:ss">
                {this.props.date}
            </Moment>
        );
    }
}
module.exports = FormatedDatetime;