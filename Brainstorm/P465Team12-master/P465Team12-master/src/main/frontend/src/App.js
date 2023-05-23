
import './App.css';
import {BrowserRouter as Router, Link, Route, Switch} from "react-router-dom";
import React, {useState} from "react";
import styled, {ThemeProvider} from "styled-components";
import {lightTheme, darkTheme, GlobalStyles} from "./themes.js";
import {Home, About, Login, Register, Announcements, StudentCalendar, Courses, User} from "./Dashboard";
import Assignments from './Dashboard/components/Assignments';
import Navbar from './Dashboard/components/Navbar';



const StyledApp = styled.div`
  color: ${(props) => props.theme.fontColor};
`;


function App() {

  
    

  const [theme, setTheme] = useState("light");

  const themeToggler = () => {
    theme === "light" ? setTheme("dark") : setTheme("light");
  };


  return (
    <ThemeProvider theme={theme === "light" ? lightTheme: darkTheme}>
      <GlobalStyles/>
      <StyledApp>
      <div>
          <Router>
              <Navbar/>
              <div>

                  <Switch>
                      <Route path="/" exact component={Home} />
                      <Route path="/about" exact component={About} />
                      <Route path="/login" exact component={Login} />
                      <Route path="/register" exact component={Register} />
                      <Route path="/courses" exact component={Courses} />
                      <Route path="/studentcalendar" exact component={StudentCalendar} />
                      <Route path="/calendar" exact component={StudentCalendar} />
                      <Route path="/announcements" exact component={Announcements} />
                      <Route path="/assignments" exact component={Assignments} />
                      <Route path="/user" exact component={User} />
                  </Switch>
              </div>
          </Router>

      </div>
      <button onClick={() => themeToggler()}>Change Theme</button>
      </StyledApp>
    </ThemeProvider>

  );
}

export default App;

/*
import './App.css';
import {BrowserRouter as Router, Link, Route, Switch} from "react-router-dom";
import {Home, About, Login, Register } from "./multiplePageSetup";
import React, {useState} from 'react';
import {Announcements, StudentCalendar, Courses, User} from "./Dashboard";
import styled, {ThemeProvider} from "styled-components";
import {lightTheme, darkTheme, GlobalStyles} from "./themes.js";

const StyledApp = styled.div`
  color: ${(props) => props.theme.fontColor};
`;


function App() {


  const [theme, setTheme] = useState("light");

  const themeToggler = () => {
    theme === "light" ? setTheme("dark") : setTheme("light");
  };


  return (
      <ThemeProvider theme={theme === "light" ? lightTheme: darkTheme}>
        <GlobalStyles/>
        <StyledApp>
          <div>
            <Router>
              <div>
                <nav/>
                <ul className={"NavLinksContainer"}>
                  <li>
                    <Link className={"headerLinks"} to="/"> Home</Link>
                  </li>
                  <li>
                    <Link className={"headerLinks"} to="/about">About</Link>
                  </li>
                  <li>
                    <Link className={"headerLinks"} to="/login">Login</Link>
                  </li>
                  <li>
                    <Link className={"headerLinks"} to="/register">Register</Link>
                  </li>
                  <li>
                    <Link className={"headerLinks"} to="/studentcalendar">Calendar</Link>
                  </li>
                  <li>
                    <Link className={"headerLinks"} to="/courses">Courses</Link>
                  </li>
                  <li>
                    <Link className={"headerLinks"} to="/announcements">Announcements</Link>
                  </li>
                  <li>
                    <Link className={"headerLinks"} to="/user">Users</Link>
                  </li>
                </ul>
                <nav/>
                <Switch>
                  <Route path="/" exact component={Home} />
                  <Route path="/about" exact component={About} />
                  <Route path="/login" exact component={Login} />
                  <Route path="/register" exact component={Register} />
                  <Route path="/courses" exact component={Courses} />
                  <Route path="/studentcalendar" exact component={StudentCalendar} />
                  <Route path="/announcements" exact component={Announcements} />
                  <Route path="/user" exact component={User} />
                </Switch>
              </div>
            </Router>
          </div>
          <button onClick={() => themeToggler()}>Change Theme</button>
        </StyledApp>
      </ThemeProvider>
  );
}

export default App;

 */
