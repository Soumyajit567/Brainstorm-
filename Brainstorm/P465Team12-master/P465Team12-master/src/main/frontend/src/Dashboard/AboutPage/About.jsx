import React from "react";
import './About.css'
function About() {
    return (
        <div className="about">
            <img className={"logo"} src="/photos/brainstorm%20logo%20cropped.png" height={125} alt={""}/>
            <div class="container">
                <p className={"statement"}>
                    Brainstorm is a learning management software designed for students K-8
                </p>
            </div>
        </div>
    );
}

export default About;