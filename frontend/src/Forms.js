import React from 'react';
import ReactDOM from "react-dom";
import Button from "react-bootstrap/Button";
import Home from "./Home";

//name, surname,  student ID, address, phone number, email address
export function Signup() {
    return (
        <div className="signup">
            <h1>Can signup here</h1>
            <form>
                <label>
                    First Name:
                    <input type="text" name="name" />
                </label><br/>
                <label>
                    Surname Name:
                    <input type="text" name="surname" />
                </label><br/>
                <label>
                    Student Number:
                    <input type="text" name="snum" />
                </label><br/>
                <label>
                    Address:
                    <input type="text" name="address" />
                </label><br/>
                <label>
                    Phone Number:
                    <input type="text" name="pnum" />
                </label><br/>
                <label>
                    Email:
                    <input type="text" name="email" />
                </label><br/>
                <input type="submit" value="Submit" />
            </form>
            <Button onClick={function () {
                ReactDOM.render(<Home/>, document.getElementById('root'));
            }}>Back</Button>
        </div>
    );
}

export function Login() {
    return(
        <div className="signup">
            <h1>Login</h1>
            <form>
                <label>
                    Username:
                    <input type="text" name="username"/>
                </label><br/>
                <label>
                    Password:
                    <input type="text" name="password"/>
                </label><br/>
                <input type="submit" value="Submit" />
            </form>
            <Button onClick={function () {
                ReactDOM.render(<Home/>, document.getElementById('root'));
            }}>Back</Button>
        </div>
    )
}
export default Signup;