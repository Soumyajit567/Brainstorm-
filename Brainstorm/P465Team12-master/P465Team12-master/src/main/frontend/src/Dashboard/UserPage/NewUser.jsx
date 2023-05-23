import './User.css'
import { useState} from "react";

const NewUser = (props) => {

    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('')
    const [content, setContent] = useState('');

    //creates a new user
    const createUser = (e) => {
        e.preventDefault();
        const course = {username, email, content};

        fetch('http://localhost:8000/users', {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(course)
        }).then(() => {
            props.setNewUser(!props.newUser)
            console.log('new user created')
        })

    }

    //returns the inputs for creating a new announcement
    return(
        <div className={"newUser"}>
            <h1 className={"usersHeader"}> Create New User </h1>
            <form onSubmit={createUser}>
                <label className={"userInputs"}> Username: </label>
                <input
                    type={"text"}
                    required
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}/>
                <label className={"userInputs"}> User email: </label>
                <input
                    type={"text"}
                    required
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}/>
                <label className={"userInputs"}> User Content: </label>
                <textarea
                    required
                    value={content}
                    onChange={(e) => setContent(e.target.value)}
                />
                <label className={"userInputs"}> Course Instructor: </label>
                <button className={"createUser"}> Create User </button>
            </form>
        </div>
    );
}

export default NewUser;