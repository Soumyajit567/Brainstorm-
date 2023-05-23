import './LoginLabels.css'

//js file for the username, password, create account button, facebook button, and google button.

function Login() {
    return (
        <body>
        <img className={"logo"} src="/photos/brainstorm%20logo%20cropped.png" height={125} alt={""}/>
        <p className={"header"}>Log In</p>
        <div className={"loginForm"}>
            <form>

                <label className={"usernameLabel"} htmlFor={"username"}>
                    Username:
                </label>

                <input className={"usernameInput"} type={"text"} id={"username"} />

                <label className={"passwordLabel"} htmlFor={"password"}>
                    Password:
                </label>

                <input className={"passwordInput"} type={"password"} id={"password"} />
                <input className={"submitButton"} type={"submit"} value={"Log in"} />

                <a className={"forgot"} href={"login"}>Forgot Password?</a>

                <label htmlFor={"facebook"}>
                    <img className={"fbPic"}  src="/photos/facebook_sign_in.png" height={50} alt="submit"/>
                </label>

                <label htmlFor={"google"}>
                    <img className={"ggPic"} src="/photos/google_sign_in.png" height={50} alt="submit"/>
                </label>

            </form>
        </div>

        </body>
    );
}

export default Login;