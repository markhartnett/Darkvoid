import React from 'react';
import ReactDOM from "react-dom";
import Button from "react-bootstrap/Button";
import Home from "../Home";
import {client} from "hawk";

class Signup extends React.Component {
    constructor(props) {
        super(props);
        this.state = {firstName: '', surname: '', snum: '', address: '', pnum: '', email: ''};

        this.handleChangeName = this.handleChangeName.bind(this);
        this.handleChangeSurname = this.handleChangeSurname.bind(this);
        this.handleChangeSnum = this.handleChangeSnum.bind(this);
        this.handleChangeAddress = this.handleChangeAddress.bind(this);
        this.handleChangePnum = this.handleChangePnum.bind(this);
        this.handleChangeEmail = this.handleChangeEmail.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChangeName(event) {
        this.setState({firstName: event.target.value});
    }

    handleChangeSurname(event) {
        this.setState({surname: event.target.value});
    }

    handleChangeSnum(event) {
        this.setState({snum: event.target.value});
    }

    handleChangeAddress(event) {
        this.setState({address: event.target.value});
    }

    handleChangePnum(event) {
        this.setState({pnum: event.target.value});
    }

    handleChangeEmail(event) {
        this.setState({email: event.target.value});
    }

    handleSubmit(event) {
        //alert('A name was submitted: ' + this.state.firstName);
        //console.log(this.state.firstName);
        /*var id;                                   // Need to get ID of created student/staff to updates
        client({method: 'POST', path: '/students'}).done(response => {
            id =
            this.setState({employees: response.entity._embedded.employees});
        });

        client({method: 'PUT', path: '/students'}).done(response => {
            this.setState({employees: response.entity._embedded.employees});
        });*/
        event.preventDefault();
    }

    render() {
        return (
            <div className="signup">
                <h1>Can signup here</h1>
                <form>
                    <label>
                        First Name:
                        <input type="text" name="name" value={this.state.firstName} onChange={this.handleChangeName}/>
                    </label><br/>
                    <label>
                        Surname Name:
                        <input type="text" name="surname" value={this.state.surname} onChange={this.handleChangeSurname}/>
                    </label><br/>
                    <label>
                        Student Number:
                        <input type="text" name="snum" value={this.state.snum} onChange={this.handleChangeSnum}/>
                    </label><br/>
                    <label>
                        Address:
                        <input type="text" name="address" value={this.state.address} onChange={this.handleChangeAddress}/>
                    </label><br/>
                    <label>
                        Phone Number:
                        <input type="text" name="pnum" value={this.state.pnum} onChange={this.handleChangePnum}/>
                    </label><br/>
                    <label>
                        Email:
                        <input type="text" name="email" value={this.state.email} onChange={this.handleChangeEmail}/>
                    </label><br/>
                    <input type="submit" value="Submit"/>
                </form>
                <Button onClick={function () {
                    ReactDOM.render(<Home/>, document.getElementById('root'));
                }}>Back</Button>
            </div>
        );
    }
}

export default Signup;