import { useState, useEffect } from 'react';
import './Courses.css'
import CourseList from "./CourseList";
import NewCourse from "./NewCourse";

const Courses = () => {

    return (

        //outputs courses
        <div className={"mainContent"}>
            <div>
                <NewCourse newCourse={newCourse} setNewCourse={setNewCourse}/>
                <h1> All Courses </h1>
            </div>
            {course && <CourseList courses={course} newCourse={newCourse} setNewCourse={setNewCourse}/>}
        </div>
    );
}

export default Courses;