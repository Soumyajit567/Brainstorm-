const UserList = ({users, newUser, setNewUser}) => {

    const deleteUser= (e) => {
        fetch('http://localhost:8000/users/' + e, {
            method: 'DELETE'
        }).then(() => {
            setNewUser(!newUser)
            console.log("deleted a user")
        })
    }

    return (
        <div className="course-list">
            {users.map((user) => (
                <div className={"user-preview"} key={user.id}>
                    <h2 className={"userTitle"}>{ user.username }</h2>
                    <h3 className={"userNumber"}>{ user.email }</h3>
                    <div className={"userContent"}>
                        {user.content}
                    </div>
                    <button className={"deleteUser"} onClick={() => (deleteUser(user.id))}> Delete User </button>
                </div>
            ))}
        </div>
    );
}

export default UserList;