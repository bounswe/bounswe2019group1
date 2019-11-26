import { environment } from "../environments/environment.prod";
import { authHeader } from "utils/auth-header";

import axios from "axios";

export function editProfile(values) {
  const requestOptions = {
    headers: {
      Authorization: authHeader(),
      "Content-Type": "application/json"
    },
    body: {
      first_name: values.first_name,
      last_name: values.last_name,
      location: values.location.address,
      title: values.title,
      biography: values.biography
    }
  };
  return axios.put(
    `${environment.api_url}user/updateuser/`,
    requestOptions.body,
    {
      headers: requestOptions.headers
    }
  );
}
export function changePassword(oldPassword, newPassword) {
  const requestOptions = {
    headers: {
      Authorization: authHeader(),
      "Content-Type": "application/json"
    },
    body: {
      old_password: oldPassword,
      new_password: newPassword
    }
  };
  return axios.put(
    `${environment.api_url}user/updateuser/`,
    requestOptions.body,
    {
      headers: requestOptions.headers
    }
  );
}
