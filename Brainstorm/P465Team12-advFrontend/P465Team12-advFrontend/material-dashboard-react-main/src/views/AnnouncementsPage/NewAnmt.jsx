import './Announcements.css'
import { useState} from "react";

const NewAnmt = (props) => {

    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [author, setAuthor] = useState('');


    //creates a new announcement
    const createAnmt = (e) => {
        e.preventDefault();
        const anmt = {title, content, author};

        fetch('http://localhost:8000/anmts', {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(anmt)
        }).then(() => {
            props.setNewAnmt(!props.newAnmt)
            console.log('new announcement created')
        })
    }


    //returns the inputs for creating a new announcement
    return(
        <div className={"newAnnouncement"}>
            <h1 className={"anmtHeader"}> Create New Announcement </h1>
            <form /*onSubmit={createAnmt}*/>
                <label className={"anmtInputs"}> Announcement Title: </label>
                <input
                    type={"text"}
                    required
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}/>
                <label className={"anmtInputs"}> Announcement Content: </label>
                <textarea
                    required
                    value={content}
                    onChange={(e) => setContent(e.target.value)}
                />
                <label className={"anmtInputs"}> Announcement Author: </label>
                <input
                    type={"text"}
                    required
                    value={author}
                    onChange={(e) => setAuthor(e.target.value)}
                />
                <button className={"createAnmt"}> Create Announcement </button>
            </form>
        </div>
    );
}

export default NewAnmt;