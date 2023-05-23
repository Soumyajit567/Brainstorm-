const AnmtList = ({announcements, newAnmt, setNewAnmt}) => {


    //deletes the announcement from the database.json
    const deleteAnmt= (e) => {
        fetch('http://localhost:8000/anmts/' + e, {
            method: 'DELETE'
        }).then(() => {
            setNewAnmt(!newAnmt)
            console.log("deleted an announcement")
        })
    }

    //returns the announcement lists
    return (
        <div className="anmt-list">
            {announcements.map((anmts) => (
                <div className={"anmt-preview"} key={anmts.id}>
                    <h2 className={"amntTitle"}>{ anmts.title }</h2>
                    <div className={"amntContent"}>
                        {anmts.content}
                    </div>
                    <p className={"writtenBy"}>Written by {anmts.author}</p>
                    <button className={"deleteAnmt"} onClick={() => (deleteAnmt(anmts.id))}> Delete Announcement </button>
                </div>
            ))}
        </div>
    );
}

export default AnmtList;