import React from "react";
import Button from "react-bootstrap/Button";
import ReactDOM from "react-dom";
import Home from "../Home";

class Login extends React.Component {
    render() {
        return (
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