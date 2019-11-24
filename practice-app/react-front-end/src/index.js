import React from "react";
import ReactDOM from "react-dom";
import { createBrowserHistory } from "history";
import { Redirect, Router, Route, Switch } from "react-router-dom";

import "assets/scss/material-kit-react.scss?v=1.8.0";

// pages for this product
import ProfilePage from "views/ProfilePage/ProfilePage.js";
import LoginPage from "views/LoginPage/LoginPage.js";
import App from "./App";
import SignupPage from "views/SignupPage/SignupPage";
import EditProfile from "./views/ProfilePage/EditProfile";
import ArticlesPage from "./views/ArticlesPage/ArticlesPage";
import WalletPage from "./views/WalletPage/WalletPage";

import AddArticle from "./views/AddArticle/AddArticle";
import PortfolioPage from "./views/PortfolioPage/Portfolio";

var hist = createBrowserHistory();

ReactDOM.render(
  <Router history={hist}>
    <Switch>
      <Route path="/login-page" component={LoginPage} />
      <Route path="/sign-up" component={SignupPage} />
      <Route
        path="/edit-profile"
        render={() =>
          localStorage.getItem("currentUser") ? (
            <EditProfile />
          ) : (
            <Redirect to="/login-page" />
          )
        }
      />
      <Route
        path="/profile-page"
        render={() =>
          localStorage.getItem("currentUser") ? (
            <ProfilePage />
          ) : (
            <Redirect to="/login-page" />
          )
        }
      />
      <Route
        path="/articles"
        render={() =>
          localStorage.getItem("currentUser") ? (
            <ArticlesPage />
          ) : (
            <Redirect to="/login-page" />
          )
        }
      />
      <Route
        path="/add-article"
        render={() =>
          localStorage.getItem("currentUser") ? (
            <AddArticle />
          ) : (
            <Redirect to="/login-page" />
          )
        }
      />
      <Route
        path="/wallet-page"
        render={() =>
          localStorage.getItem("currentUser") ? (
            <WalletPage />
          ) : (
            <Redirect to="/login-page" />
          )
        }
      />
      <Route
        path="/portfolio"
        render={() =>
          localStorage.getItem("currentUser") ? (
            <PortfolioPage />
          ) : (
            <Redirect to="/login-page" />
          )
        }
      />
      <Route path="/" component={App} />
    </Switch>
  </Router>,
  document.getElementById("root")
);
// <Route
//         {...props}
//         render={props => (
//           this.state.authenticated ?
//             <Component {...props} /> :
//             <Redirect to='/login' />
//         )}
//       />

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
// serviceWorker.unregister();
