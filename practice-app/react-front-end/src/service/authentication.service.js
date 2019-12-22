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
    .then(res => (res.status === 200 ? res.data : null))
    .then(data => {
      // store user details and jwt token in local storage to keep user logged in between page refreshes
      if (data.token) {
        localStorage.setItem("currentUser", JSON.stringify(data.token));
        localStorage.setItem("userDetails", JSON.stringify({id: "35.163.120.227/user/" + data.user.id, name: data.user.first_name + " "+ data.user.last_name, nickname: data.user.username}));
      }
      return data.token;
    });
}
export function logout() {
  // remove user from local storage to log user out
  localStorage.removeItem("currentUser");
  localStorage.removeItem("userDetails");
}
