import './Assignments.css'
import { useState} from "react";
import FileUpload from './FileUpload.js';

const NewAg = (props) => {

    const [atitle, setAgTitle] = useState('');
    const [aquestion, setAgQuestion] = useState('');
    const [aauthor, setAgAuthor] = useState('');


    //creates a new assignment
    const createAg = (e) => {
        e.preventDefault();
        const ag = {atitle, aquestion, aauthor};

        fetch('http://localhost:8000/ag', {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(ag)
        }).then(() => {
            props.setNewAg(!props.newAg)
            console.log('new assignment created')
        })

    }


    //returns the inputs for creating a new assignment
    return(
        <div className={"newAssignment"}>
            <h1 className={"agHeader"}> Create New Assignment </h1>
            <form onSubmit={createAg}>
                <label className={"agInputs"}> Assignment Title: </label>
                <input
                    type={"text"}
                    required
                    value={atitle}
                    onChange={(e) => setAgTitle(e.target.value)}/>
                <label className={"agInputs"}> Assignment Question: </label>
                <textarea
                    required
                    value={aquestion}
                    onChange={(e) => setAgQuestion(e.target.value)}
                />
                
               <label className={"agInputs"}> Assignment Author: </label>
                <input
                    type={"text"}
                    required
                    value={aauthor}
                    onChange={(e) => setAgAuthor(e.target.value)}
                /> 
                
                <button className={"createAg"}> Create Assignment </button>
                
  
            </form>
        </div>
    );
}

export default NewAg;