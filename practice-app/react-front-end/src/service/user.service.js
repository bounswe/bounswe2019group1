import { environment } from "environments/environment.prod";
//import { handleResponse } from "../utils/responseHandlers";
import axios from "axios";

export function getAllUsers() {
  return axios(`${environment.api_url}auth/list`).then(res => res.data);
}
export function userRetrieve(userId) {
  return axios
    .get(`${environment.api_url}user/retrieve/${userId}/`)
    .then(res => res.data);
}
