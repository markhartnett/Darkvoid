import React from 'react';
import './Home.css';
import ButtonToolbar from 'react-bootstrap/ButtonToolbar';
import Button from 'react-native';

function Home() {
    return (
        <div className="App">
            <ButtonToolbar>
                <Button>School Stats</Button>
                <Button>Sign Up</Button>
                <Button>Students</Button>
                <Button>Staff</Button>
            </ButtonToolbar>
        </div>
  );
}

export default Home;
