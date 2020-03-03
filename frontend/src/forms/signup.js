import React from 'react';
import ReactDOM from "react-dom";
import Button from "react-bootstrap/Button";
import Home from "../Home";
import {client} from "hawk";

class Signup extends React.Component {
    constructor(props) {
        super(props);
        this.state = {username: '', firstName: '', surname: '', address: '', pnum: '', email: '', gender: '', nationality: ''};

        this.handleChangeUsername = this.handleChangeUsername.bind(this);
        this.handleChangeName = this.handleChangeName.bind(this);
        this.handleChangeSurname = this.handleChangeSurname.bind(this);
        this.handleChangeAddress = this.handleChangeAddress.bind(this);
        this.handleChangePnum = this.handleChangePnum.bind(this);
        this.handleChangeEmail = this.handleChangeEmail.bind(this);
        this.handleChangeNationality = this.handleChangeNationality.bind(this);
        this.handleChangeGender = this.handleChangeGender.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChangeUsername(event) {
        this.setState({username: event.target.value});
    }

    handleChangeName(event) {
        this.setState({firstName: event.target.value});
    }

    handleChangeSurname(event) {
        this.setState({surname: event.target.value});
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

    handleChangeGender(event) {
        this.setState({gender: event.target.value});
    }

    handleChangeNationality(event) {
        this.setState({nationality: event.target.value});
    }

    handleSubmit(event) {
        fetch('/students',
            {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    username: this.state.username,
                    firstName: this.state.firstName,
                    lastName: this.state.lastName,
                    phoneNumber: this.state.pnum,
                    email: this.state.email,
                    gender: this.state.gender,
                    nationality: this.state.username
                })
            });
        event.preventDefault();
    }

    render() {
        return (
            <div className="signup">
                <h1>Student Sign Up</h1>
                <form>
                    <label>
                        Username:
                        <input type="text" name="name" value={this.state.username} onChange={this.handleChangeUsername}/>
                    </label><br/>
                    <label>
                        First Name:
                        <input type="text" name="name" value={this.state.firstName} onChange={this.handleChangeName}/>
                    </label><br/>
                    <label>
                        Surname Name:
                        <input type="text" name="surname" value={this.state.surname} onChange={this.handleChangeSurname}/>
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
                    <label>
                        Gender:
                        <select id="gender" name="gender" value={this.state.gender} onChange={this.handleChangeGender}>
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                            <option value="other">Other</option>
                            <option value="preferNot">Prefer Not to Say</option>
                        </select>
                    </label><br/>
                    <label>
                        Nationality:
                        <input type="text" name="email" value={this.state.nationality} onChange={this.handleChangeNationality}/>
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