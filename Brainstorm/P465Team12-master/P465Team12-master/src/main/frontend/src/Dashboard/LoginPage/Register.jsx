import React from "react";
import './LoginLabels.css'

function Register() {
    return (
        <body>
        <img className={"logo"} src="/photos/brainstorm%20logo%20cropped.png" height={125} alt={""}/>
        <p className={"header"}>
            Register
        </p>
        <form className={"loginForm"}>

            <label className={"usernameLabel"} htmlFor={"username"}>
                Username:
            </label>

            <input className={"usernameInput"} type={"text"} id={"username"} />

            <label className={"passwordLabel"} htmlFor={"password"}>
                Password:
            </label>

            <input className={"passwordInput"} type={"password"} id={"password"} />

            <label className={"firstNameLabel"} htmlFor={"text"}>
                First Name:
            </label>

            <input className={"firstNameInput"} type={"text"} id={"fistName"} />
            <label className={"lastNameLabel"} htmlFor={"text"}>
                Last Name:
            </label>

            <input className={"LastNameInput"} type={"text"} id={"lastName"} />

            <label className={"emailLabel"} htmlFor={"text"}>
                Email Address:
            </label>

            <input className={"emailInput"} type={"text"} id={"emailAddress"} />

            <label className={"firstQuestionLabel"} htmlFor={"text"}>
                What city where you born in?:
            </label>

            <input className={"firstQuestionInput"} type={"text"} id={"firstQuestion"} />

            <label className={"secondQuestionLabel"} htmlFor={"text"}>
                What high school did you attend?:
            </label>

            <input className={"secondQuestionInput"} type={"text"} id={"secondQuestion"} />



            <input className={"submitButton"} type={"submit"} value={"Create Account"} />

            <label htmlFor={"facebook"}>
                <img className={"fbPic"}  src="/photos/facebook_sign_in.png" height={50} alt="submit"/>
            </label>

            <label htmlFor={"google"}>
                <img className={"ggPic"} src="/photos/google_sign_in.png" height={50} alt="submit"/>
            </label>

        </form>
        </body>
    );
}

export default Register;