import { environment } from "../environments/environment.prod";
import { authHeader } from "utils/auth-header";

import axios from "axios";

export function editProfile(values) {
  const requestOptions = {
    headers: {
      Authorization: authHeader(),
      "Content-Type": "application/json"
    }
  };
  return axios.put(
    `${environment.api_url}user/updateuser/`,
    JSON.stringify(values),
    requestOptions.headers
  );
}
