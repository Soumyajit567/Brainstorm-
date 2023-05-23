import { useState, useEffect } from 'react';
import './Announcements.css'
import AnmtList from "./AnmtList";
import NewAnmt from "./NewAnmt";

const Announcements = () => {

    const [anmt, setAnmt] = useState(null);
    const [newAnmt, setNewAnmt] = useState(false);

    const updateAnmts = () => {
        setNewAnmt(true);
    };


    //fetches data on the first render
    useEffect(()=> {
        console.log('use effect has occurred');
        fetch('https://brainstormbackend.herokuapp.com/anmts/').then(response => {
            return response.json();
        })
            .then((data) => {
                console.log(data)
                setAnmt(data)
            })
    }, [newAnmt]);


    return (

        //calls anmt list to display announcements
        <div className={"mainContent"}>
            <div>
                <NewAnmt newAnmt={newAnmt} setNewAnmt={setNewAnmt}/>
                <h1> All Announcements </h1>
            </div>
            {anmt && <AnmtList announcements={anmt} newAnmt={newAnmt} setNewAnmt={setNewAnmt}/>}
        </div>
    );
}

export default Announcements;