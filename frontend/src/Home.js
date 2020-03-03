import ButtonToolbar from "react-bootstrap/ButtonToolbar";
import Button from "react-bootstrap/Button";
import React from "react";
import Stats from "./Stats";
import Signup from "./forms/signup";
import Login from "./forms/login";
import ReactDOM from "react-dom";

class Home extends React.Component {
    render() {
        return (
            <div className="Home">
                <ButtonToolbar>
                    <Button onClick={function () {
                        ReactDOM.render(<Stats/>, document.getElementById('root'));
                    }}>School Stats</Button>
                    <Button onClick={function () {
                        ReactDOM.render(<Signup/>, document.getElementById('root'));
                    }}>Sign Up</Button>
                    <Button onClick={function () {
                        ReactDOM.render(<Login/>, document.getElementById('root'));
                    }}>Students</Button>
                    <Button onClick={function () {
                        ReactDOM.render(<Login/>, document.getElementById('root'));
                    }}>Staff</Button>
                </ButtonToolbar>
            </div>
        );
    }
}

export default Home;
