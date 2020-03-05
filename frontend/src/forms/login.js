import React from "react";
import Button from "react-bootstrap/Button";
import ReactDOM from "react-dom";
import Home from "../Home";
import Cookies from 'universal-cookie';

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
        // TODO
        // Call for Students + Staff
        // Check all usernames til entered on is found
        // If not found, end. If found, check password
        // If password is accurate, update loggedIn and staff cookies, return to homeds
        fetch('http://localhost:8080/students',
            {
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            }).then((response) => {
                if (response.status === 200) { // https://developer.mozilla.org/en-US/docs/Web/API/Response
                    const reader = response.body.getReader(); // returns: https://developer.mozilla.org/en-US/docs/Web/API/ReadableStream
                    reader.read()


                    ReactDOM.render(<Home/>, document.getElementById('root'));
                    return;
                } else {
                    alert("Error " + response.status + " occurred");
                }
            }
        );
        const url = String(this.state.username) + '/' + String(this.state.password);
        const studentId = fetch('http://localhost:8080/login/student/' + url,
            {
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            }).then(response => response.json());

        console.log("ID: " + studentId);
        //if (studentId !== -1) {
            const student = fetch('http://localhost:8080/' + studentId,
                {
                    method: 'GET',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                }).then(response => response.json());
        console.log("student " + student);
        console.log("Name: " + student.name);
        //    const cookies = new Cookies();
        //    cookies.set('name', student.name, { path: '/' });
        //    cookies.set('loggedIn', 'true', { path: '/' });
        //    cookies.set('staff', 'false', { path: '/' });
        //}



        event.preventDefault();
    }

    render() {
        const cookies = new Cookies();
        return (
            <div className="signup">
                <h1>Login</h1>
                <form onSubmit={this.handleSubmit}>
                    <label>
                        Username:
                        <input type="text" name="username"/>
                    </label><br/>
                    <label>
                        Password:
                        <input type="text" name="password"/>
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