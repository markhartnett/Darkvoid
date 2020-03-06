import React from "react";
import Button from "react-bootstrap/Button";
import ReactDOM from "react-dom";
import Home from "../Home";
//import Cookies from 'universal-cookie';

class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {username: '', password: ''};

        this.handleChangeUsername = this.handleChangeUsername.bind(this);
        this.handleChangePassword = this.handleChangePassword.bind(this);
    }

    handleChangeUsername(event) {
        this.setState({username: event.target.value});
    }

    handleChangePassword(event) {
        this.setState({password: event.target.value});
    }

    handleSubmit(event) {
        const s = this.state;

        const p = this.performLogin(s);

        //event.preventDefault();
    }

    async performLogin(s) {
        // TODO
        // If password is accurate, update loggedIn and staff cookies, return to home

        console.log("Username: " + s.username);
        console.log("Password: " + s.password);
        const url = String(s.username) + '/' + String(s.password);
        const studentId = await fetch('http://localhost:8080/login/student/' + url,
            {
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            }).then(response => response.json());
        console.log("Halfway");
        console.log("Halfway");

        if (studentId !== -1) {
            const student = await fetch('http://localhost:8080/students/' + studentId,
                {
                    method: 'GET',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                }).then(response => response.json());
            //    const cookies = new Cookies();
            //    cookies.set('name', student.name, { path: '/' });
            //    cookies.set('loggedIn', 'true', { path: '/' });
            //    cookies.set('staff', 'false', { path: '/' });
            alert("student " + student);
            alert("Name: " + student.name);
        }

        console.log("ID: " + studentId);
    };




    render() {
        return (
            <div className="login">
                <h1>Login</h1>
                <form onSubmit={() => this.handleSubmit()}>
                    <label>
                        Username:
                        <input type="text" name="username" value={this.state.username} onChange={this.handleChangeUsername}/>
                    </label><br/>
                    <label>
                        Password:
                        <input type="text" name="password" value={this.state.password} onChange={this.handleChangePassword}/>
                    </label><br/>
                    <input type="submit" value="Submit"/>
                </form>
                <Button onClick={function () {
                    ReactDOM.render(<Home/>, document.getElementById('root'));
                }}>Back</Button>
            </div>
        )
    }
}

export default Login;