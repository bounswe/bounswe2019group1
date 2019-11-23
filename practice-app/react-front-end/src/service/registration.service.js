import { environment } from "../environments/environment.prod";
// import { handleResponse } from "../utils/responseHandlers";
import axios from "axios";

export function registerBasic(username, pass, email, name, surname, location) {
  const requestOptions = {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: {
      username: username,
      password: pass,
      email: email,
      first_name: name,
      last_name: surname,
      location: location.address
    }
  };
  return axios.post(
    `${environment.api_url}user/registerbasic/`,
    requestOptions.body,
    requestOptions.headers
  );
}
export function RegisterTrader(
  username,
  pass,
  email,
  name,
  surname,
  location,
  iban
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
      iban_number: iban
    }
  };
  return axios.post(
    `${environment.api_url}user/registertrader/`,
    requestOptions.body,
    requestOptions.headers
  );
}
