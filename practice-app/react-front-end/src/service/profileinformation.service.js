import { environment } from "environments/environment.prod";
import { authHeader } from "utils/auth-header";
// import { handleResponse } from "../utils/responseHandlers";
import axios from "axios";

export function getProfileInfo() {
  const requestOptions = {
    headers: {
      Authorization: authHeader()
    }
  };

  return axios
    .get(`${environment.api_url}user/profile/`, {
      headers: requestOptions.headers
    })
    .then(res => (res.status === 200 ? res.data : null));
}

export function getProfilePhoto(image_path) {
  return axios
    .get(`${image_path}`)
    .then(res => (res.status === 200 ? res.data : null));
}
