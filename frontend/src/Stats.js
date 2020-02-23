import React from 'react';
import ReactDOM from "react-dom";
import Button from "react-bootstrap/Button";
import Home from "./Home";

function Stats() {
    return (
        <div className="stats">
           <p>Stats</p>
           <Button onClick={function () {
               ReactDOM.render(<Home/>, document.getElementById('root'));
           }}>Back</Button>
        </div>
    );
}

export default Stats;