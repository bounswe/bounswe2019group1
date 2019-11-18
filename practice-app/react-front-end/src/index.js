import React from "react";
import ReactDOM from "react-dom";
import { createBrowserHistory } from "history";
import { Router, Route, Switch } from "react-router-dom";

import "assets/scss/material-kit-react.scss?v=1.8.0";

// pages for this product
import ProfilePage from "views/ProfilePage/ProfilePage.js";
import LoginPage from "views/LoginPage/LoginPage.js";
import App from "./App";
import SignupPage from "views/SignupPage/SignupPage";
import EditProfile from "./views/ProfilePage/EditProfile";
import ArticlesPage from "./views/ArticlesPage/ArticlesPage";
var hist = createBrowserHistory();
ReactDOM.render(
    <Router history={hist}>
        <Switch>
            
            <Route path="/login-page" component={LoginPage} />
            {localStorage.getItem("currentUser")?<Route path="/edit-profile" component={EditProfile} />: <Route path="/login-page" component={LoginPage}/>  }
            {localStorage.getItem("currentUser")?<Route path="/profile-page" component={ProfilePage} />: <Route path="/login-page" component={LoginPage}/> }
            <Route path="/sign-up" component={SignupPage} />
            {localStorage.getItem("currentUser")?<Route path="/articles" component={ArticlesPage} />: <Route path="/login-page" component={LoginPage}/> }
            
            <Route path="/" component={App} />
        </Switch>
    </Router>,
    document.getElementById("root")
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
// serviceWorker.unregister();
