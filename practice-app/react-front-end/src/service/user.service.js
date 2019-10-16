import environment from "environments/environment.prod";
import { authHeader } from "utils/auth-header";
//import { handleResponse } from "../utils/responseHandlers";
import axios from "axios";

export const userService = {
  getAll
};

export function getAll() {
  const requestOptions = { method: "GET", headers: authHeader() };
  return axios(`${environment.api_url}/users`, requestOptions);
  /*   .then(
        handleResponse

  );*/
}
