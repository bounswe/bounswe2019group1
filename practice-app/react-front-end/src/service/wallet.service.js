import { environment } from "environments/environment.prod";
import { authHeader } from "utils/auth-header";
import axios from "axios";

export function getMyWallet() {
  const requestOptions = {
    headers: {
      Authorization: authHeader()
    }
  };
  return axios(
    `${environment.api_url}wallet/retrieve/`,
    requestOptions
  ).then(res => (res.status === 200 ? res.data : null));
}


//http://35.163.120.227:8000/wallet/retrieve/
export function getWalletById(user_id) {
  const requestOptions = {
    headers: {
      Authorization: authHeader()
    }
  };
  return axios(
    `${environment.api_url}wallet/retrieve/${user_id}/`,
    requestOptions
  ).then(res => (res.status === 200 ? res.data : null));
}

export function getWalletById2(user_id) {
  const requestOptions = {
    headers: {
      Authorization: authHeader()
    }
  };
  return axios(
    `${environment.api_url}wallet/retrieve/${user_id}/`,
    requestOptions
  ).then(res => (res.status === 200 ? res.data : null));
}
