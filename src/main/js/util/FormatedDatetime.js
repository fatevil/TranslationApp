import React  from 'react';
import Moment from 'react-moment';
 
class FormatedDatetime extends React.Component {
    render() {
        return (
        <div>
            <Moment format="MMMM Do YYYY, hh:mm:ss">
                {this.props.date}
            </Moment>
        </div>
        );
    }
}
module.exports = FormatedDatetime;