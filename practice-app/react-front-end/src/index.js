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
import Article from "./views/ArticlesPage/Article";
import AddArticle from "./views/AddArticle/AddArticle";
import EditArticle from "./views/AddArticle/EditArticle";
import PortfolioPage from "./views/PortfolioPage/Portfolio";
import SearchResults from "./views/SearchResults/SearchResults";
import NotificationPage from "./views/PortfolioPage/Notification";
import UserProfile from "./views/ProfilePage/UserProfile";
import ResetPassword from "views/ResetPassword/ResetPassword";
var hist = createBrowserHistory();

ReactDOM.render(
  <Router history={hist}>
    <Switch>
      <Route path="/login-page" component={LoginPage} />
      <Route path="/sign-up" component={SignupPage} />
      <Route path="/reset-password" component={ResetPassword} />

      <Route
        path="/edit-profile"
        render={() =>
          localStorage.getItem("currentUser") ? (
            <EditProfile history={hist} />
          ) : (
            <Redirect to="/login-page" />
          )
        }
      />
      <Route
        path="/profile-page"
        render={() =>
          localStorage.getItem("currentUser") ? (
            <ProfilePage history={hist} />
          ) : (
            <Redirect to="/login-page" />
          )
        }
      />
      <Route
        path="/search-results"
        render={() =>
          localStorage.getItem("currentUser") ? (
            <SearchResults history={hist} />
          ) : (
            <Redirect to="/login-page" />
          )
        }
      />
      <Route
        path="/notifications"
        render={() =>
          localStorage.getItem("currentUser") ? (
            <NotificationPage history={hist} />
          ) : (
            <Redirect to="/login-page" />
          )
        }
      />
      <Route
        path="/articles"
        render={() =>
          localStorage.getItem("currentUser") ? (
            <ArticlesPage history={hist} />
          ) : (
            <Redirect to="/login-page" />
          )
        }
      />
      <Route
        path="/article"
        render={() =>
          localStorage.getItem("currentUser") ? (
            <Article history={hist} />
          ) : (
            <Redirect to="/article" />
          )
        }
      />
      <Route
        path="/add-article"
        render={() =>
          localStorage.getItem("currentUser") ? (
            <AddArticle history={hist} />
          ) : (
            <Redirect to="/login-page" />
          )
        }
      />
      <Route
        path="/wallet-page"
        render={() =>
          localStorage.getItem("currentUser") ? (
            <WalletPage history={hist} />
          ) : (
            <Redirect to="/login-page" />
          )
        }
      />
      <Route
        path="/edit-article"
        render={() =>
          localStorage.getItem("currentUser") ? (
            <EditArticle history={hist} />
          ) : (
            <Redirect to="/login-page" />
          )
        }
      />
      <Route
        path="/article/:id"
        render={() =>
          localStorage.getItem("currentUser") ? (
            <Article history={hist} />
          ) : (
            <Redirect to="/login-page" />
          )
        }
      />
      <Route
        path="/article/edit/:id"
        render={() =>
          localStorage.getItem("currentUser") ? (
            <EditArticle history={hist} />
          ) : (
            <Redirect to="/login-page" />
          )
        }
      />
      <Route
        path="/user/:id"
        render={() =>
          localStorage.getItem("currentUser") ? (
            <UserProfile history={hist} />
          ) : (
            <Redirect to="/login-page" />
          )
        }
      />
      <Route
        path="/market"
        render={() =>
          localStorage.getItem("currentUser") ? (
            <PortfolioPage history={hist} />
          ) : (
            <Redirect to="/login-page" />
          )
        }
      />
      <Route path="/" component={App} />
      <Route
        path="/portfolio"
        render={() =>
          localStorage.getItem("currentUser") ? (
            <PortfolioPage history={hist} />
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
