import React from 'react';
import ReactDOM from "react-dom";
import Button from "react-bootstrap/Button";
import Home from "../Home";

class Signup extends React.Component {
    constructor(props) {
        super(props);
        this.state = {username: '', password: '', firstName: '', surname: '', address: '', pnum: '', email: '', gender: '', nationality: ''};

        this.handleChangeUsername = this.handleChangeUsername.bind(this);
        this.handleChangePassword = this.handleChangePassword.bind(this);
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

    handleChangePassword(event) {
        this.setState({password: event.target.value});
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
        // TODO
        // Check username is unique, between Students + Staff
        if (this.state.username === '' ||
            this.state.firstName === '' ||
            this.state.lastName === '' ||
            this.state.phoneNumber === '' ||
            this.state.password === '' ||
            this.state.email === '' ||
            this.state.gender === '' ||
            this.state.nationality === ''
            ) {
            alert("Please select a value for all options");
        } else {
            fetch('http://localhost:8080/students',
                {
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        "username": String(this.state.username),
                        "firstName": String(this.state.firstName),
                        "lastName": String(this.state.surname),
                        "phoneNumber": String(this.state.pnum),
                        "password": String(this.state.password),
                        "email": String(this.state.email),
                        "gender": String(this.state.gender),
                        "nationality": String(this.state.username)
                    })
                }).then((response) => {
                    if (response.status === 200) {
                        ReactDOM.render(<Home/>, document.getElementById('root'));
                    } else {
                        alert("Error " + response.status + " occurred");
                    }
                }
            );
        }
        event.preventDefault();
    }

    render() {
        return (
            <div className="signup">
                <h1>Student Sign Up</h1>
                <form onSubmit={() => this.handleSubmit}>
                    <label>
                        Username:
                        <input type="text" name="name" value={this.state.username} onChange={this.handleChangeUsername}/>
                    </label><br/>
                    <label>
                        Password:
                        <input type="text" name="password" value={this.state.password} onChange={this.handleChangePassword}/>
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
                            <option value="">-- Select --</option>
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