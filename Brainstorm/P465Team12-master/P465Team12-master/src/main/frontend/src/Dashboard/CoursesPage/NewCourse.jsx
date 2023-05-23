import './Courses.css'
import { useState } from "react";

const NewCourse = (props) => {

    const [title, setTitle] = useState('');
    const [courseNum, setCourseNum] = useState('')
    const [content, setContent] = useState('');
    const [prof, setProf] = useState('');


    //creates a new course
    const createCourse = (e) => {
        e.preventDefault();
        const course = {title, courseNum, content, prof};

        fetch('http://localhost:8000/courses', {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(course)
        }).then(() => {
            props.setNewCourse(!props.newCourse)
            console.log('new course created')
        })

    }

    //returns the inputs for creating a new course
    return(
        <div className={"newCourse"}>
            <h1 className={"courseHeader"}> Create New Course </h1>
            <form onSubmit={createCourse}>
                <label className={"courseInputs"}> Course Title: </label>
                <input
                    type={"text"}
                    required
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}/>
                <label className={"courseInputs"}> Course Number: </label>
                <input
                    type={"text"}
                    required
                    value={courseNum}
                    onChange={(e) => setCourseNum(e.target.value)}/>
                <label className={"courseInputs"}> Course Details: </label>
                <textarea
                    required
                    value={content}
                    onChange={(e) => setContent(e.target.value)}
                />
                <label className={"courseInputs"}> Course Instructor: </label>
                <input
                    type={"text"}
                    required
                    value={prof}
                    onChange={(e) => setProf(e.target.value)}
                />
                <button className={"createCourse"}> Create Course </button>
            </form>
        </div>
    );
}

export default NewCourse;