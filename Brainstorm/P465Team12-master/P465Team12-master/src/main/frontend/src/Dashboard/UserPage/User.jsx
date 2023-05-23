import { useState, useEffect } from 'react';
import NewUser from "./NewUser";
import UserList from "./UserList";
import './User.css'

const User = () => {

    const [user, setUser] = useState(null);
    const [newUser, setNewUser] = useState(false);

    const updateUsers = () => {
        setNewUser(true);
    };

    //fetches data on the first render
    useEffect(()=> {
        console.log('use effect has occurred');
        fetch('http://localhost:8000/users').then(response => {
            console.log("retrieved users")
            return response.json();
        })
            .then((data) => {
                console.log(data)
                setUser(data)
            })
    }, [newUser]);

    return (

        //outputs announcements
        <div className={"mainContent"}>
            <div>
                <NewUser newUser={newUser} setNewUser={setNewUser}/>
                <h1> All Users </h1>
            </div>
            {user && <UserList users={user} newUser={newUser} setNewUser={setNewUser}/>}
        </div>
    );
}

export default User;