import FileUpload from './FileUpload.js';
const AgList = ({assignments, newAg, setNewAg}) => {


    //deletes the announcement from the database.json
    const deleteAg= (e) => {
        fetch('http://localhost:8000/ag/' + e, {
            method: 'DELETE'
        }).then(() => {
            setNewAg(!newAg)
            console.log("deleted an assignment")
        })
    }

    //returns the announcement lists
    return (
        <div className="ag-list">
            {assignments.map((asg) => (
                <div className={"ag-preview"} key={asg.id}>
                    <h2 className={"agTitle"}>{ asg.title }</h2>
                    <div className={"agContent"}>
                        {asg.question}
                    </div>
                    <p className={"writtenByAg"}>Written By: {asg.author}</p> 
                    <div className='container mt-4'>
                    <h4 className='display-4 text-center mb-4'>
                    <i className='fab fa-react' />  File Upload
                    </h4>
                        <FileUpload />
                    </div> 
                </div>
            ))}
        </div>
    );
}

export default AgList;