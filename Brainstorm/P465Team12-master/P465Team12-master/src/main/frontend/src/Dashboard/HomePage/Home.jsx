import React from "react";
import './Home.css'
function Home() {
    return (
        <div className="home">
            <div>
                <p className={"title"}>
                    Welcome to Brainstorm
                </p>
                <img src="/public/photos/brainstorm logo no text.png" height={500} alt={""} className={"homeLogo"}/>
            </div>
        </div>
    );
}

export default Home;