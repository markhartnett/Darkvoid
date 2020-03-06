import React from 'react';
import ReactDOM from "react-dom";
import Button from "react-bootstrap/Button";
import Home from "./Home";

// Num Students:
// Num Staff:
// Pi chart Gender:
// Pi chart Nationality:
// /moduleEnrolment <-- grades
//

// https://canvasjs.com/react-charts/


class Stats extends React.Component {
    constructor(props) {
        super(props);

        this.state = {numStudents: '0'};
    }

    async getNumStudents() {
        const n = await fetch('http://localhost:8080/students',
            {
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            }).then(response => response.json())
              .then((responseJSON) => responseJSON.length);

        //console.log(typeof(n));
        //console.log(n);
        return n;
    }

    componentDidMount() {
        this.getNumStudents().then((n) => {this.setState({numStudents: n}); console.log(n)});
        console.log(this.state.numStudents);
        console.log(typeof(this.state.numStudents));
    }

    render() {
        return (
            <div className="stats">
                <h1>Stats</h1>
                <ul>
                    <li>Number of students: {this.numStudents}</li>
                </ul>
                <Button onClick={function () {
                    ReactDOM.render(<Home/>, document.getElementById('root'));
                }}>Back</Button>
            </div>
        );
    }
}

export default Stats;