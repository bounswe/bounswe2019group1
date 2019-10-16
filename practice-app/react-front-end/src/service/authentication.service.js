import environment from "../environments/environment.prod";
// import { handleResponse } from "../utils/responseHandlers";
import axios from "axios";

export const authenticationService = {
  login,
  logout
};

function login(username, password) {
  const requestOptions = {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      username: username,
      password: password
    })
  };
  return axios(`${environment.api_url}/user/login`, requestOptions)
    .then()
    .then(token => {
      // store user details and jwt token in local storage to keep user logged in between page refreshes
      localStorage.setItem("currentUser", JSON.stringify(token));
      return token;
    });
}
function logout() {
  // remove user from local storage to log user out
  localStorage.removeItem("currentUser");
}
