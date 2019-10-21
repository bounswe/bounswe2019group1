import { environment } from "../environments/environment.prod";
// import { handleResponse } from "../utils/responseHandlers";
import axios from "axios";

export function registerBasic(username, pass, email, name, surname) {
  const requestOptions = {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: {
      username: username,
      password: pass,
      email: email,
      first_name: name,
      last_name: surname
    }
  };
  return axios
    .post(
      `${environment.api_url}user/registerbasic/`,
      requestOptions.body,
      requestOptions.headers
    )
    .then(res => res.status);
}
export function RegisterTrader(
  username,
  pass,
  email,
  name,
  surname,
  location,
  iban,
  citizenshipno
) {
  // Register the trading user.
  const requestOptions = {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: {
      username: username,
      password: pass,
      email: email,
      first_name: name,
      last_name: surname,
      location: location.address,
      iban_number: iban,
      citizenship_number: citizenshipno
    }
  };
  return axios
    .post(
      `${environment.api_url}user/registertrader/`,
      requestOptions.body,
      requestOptions.headers
    )
    .then(res => res.status);
}
