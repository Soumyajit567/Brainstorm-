import { useState, useEffect } from 'react';
import './Assignments.css'
import AgList from "./AgList";
import NewAg from "./NewAg";

const Assignments = () => {

    const [ag, setAg] = useState(null);
    const [newAg, setNewAg] = useState(false);

    const updateAg = () => {
        setNewAg(true);
    };


    //fetches data on the first render
    useEffect(()=> {
        console.log('use effect has occurred');
        fetch('http://localhost:8000/ag').then(response => {
            return response.json();
        })
            .then((data) => {
                console.log(data)
                setAg(data)
            })
    }, [newAg]);


    return (

        //calls anmt list to display announcements
        <div className={"mainContent"}>
            <div>
                <NewAg newAg={newAg} setNewAg={setNewAg}/>
                <h1> All Assignments </h1>
            </div>
            {ag && <AgList assignments={ag} newAg={newAg} setNewAg={setNewAg}/>}
        </div>
    );
}

export default Assignments;
