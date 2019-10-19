import { environment } from "../environments/environment.prod";
// import { handleResponse } from "../utils/responseHandlers";
import axios from "axios";

export function login(username, password) {
  const requestOptions = {
    headers: {
      "Content-Type": "application/json"
    },
    body: {
      username: username,
      password: password
    }
  };
  return axios
    .post(
      `${environment.api_url}user/login/`,
      requestOptions.body,
      requestOptions.headers
    )
    .then(res => (res.status == 200 ? res.data.token : null))
    .then(token => {
      // store user details and jwt token in local storage to keep user logged in between page refreshes
      if (token) {
        localStorage.setItem("currentUser", JSON.stringify(token));
      }
      return token;
    });
}
export function logout() {
  // remove user from local storage to log user out
  localStorage.removeItem("currentUser");
}
