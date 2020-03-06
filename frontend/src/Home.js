import ButtonToolbar from "react-bootstrap/ButtonToolbar";
import Button from "react-bootstrap/Button";
import React from "react";
import Stats from "./Stats";
import Signup from "./forms/signup";
import Login from "./forms/login";
import ReactDOM from "react-dom";
import Cookies from 'universal-cookie';

class Home extends React.Component {
    render() {
        const cookies = new Cookies();
        cookies.set('name', '', { path: '/' });
        cookies.set('loggedIn', 'false', { path: '/' });
        cookies.set('staff', 'false', { path: '/' });
        return (
            <div className="Home">
                <h1>Welcome {cookies.get('name')}</h1>
                <ButtonToolbar>
                    <Button onClick={function () {
                        ReactDOM.render(<Stats/>, document.getElementById('root'));
                    }}>School Stats</Button><br/><br/>
                    <Button onClick={function () {
                        ReactDOM.render(<Signup/>, document.getElementById('root'));
                    }}>Student Sign Up</Button>
                    <Button onClick={function () {
                        ReactDOM.render(<Login/>, document.getElementById('root'));
                    }}>Login</Button>
                </ButtonToolbar><br/>
                <img src="https://bridge.jo/wp-content/uploads/2016/12/aicum-8.jpg" alt="university" height="300" width="400"/>
            </div>
        );
    }
}

export default Home;
