import { environment } from "environments/environment.prod";
import { authHeader } from "utils/auth-header";
import axios from "axios";

export function follow(following_id) {
  const requestOptions = {
    headers: {
      Authorization: authHeader(),
      "Content-Type": "application/json"
    },
    body: {
      following: following_id
    }
  };
  return axios.post(
    `${environment.api_url}follow/follow/`,
    requestOptions.body,
    {
      headers: requestOptions.headers
    }
  );
}

export function listUserFollowers() {
  const requestOptions = {
    headers: {
      Authorization: authHeader()
    }
  };
  return axios(
    `${environment.api_url}follow/listFollower/`,
    requestOptions
  ).then(res => (res.status === 200 ? res.data : null));
}

export function listUserFollowings() {
  const requestOptions = {
    headers: {
      Authorization: authHeader()
    }
  };
  return axios(
    `${environment.api_url}follow/listFollowing/`,
    requestOptions
  ).then(res => (res.status === 200 ? res.data : null));
}

//http://35.163.120.227:8000/follow/followList/
export function listFollowingsById(user_id) {
  const requestOptions = {
    headers: {
      Authorization: authHeader()
    }
  };
  return axios(
    `${environment.api_url}follow/listFollowingWithIdFront/${user_id}/`,
    requestOptions
  ).then(res => (res.status === 200 ? res.data : null));
}

export function listFollowersById(user_id) {
  const requestOptions = {
    headers: {
      Authorization: authHeader()
    }
  };
  return axios(
    `${environment.api_url}follow/listFollowerWithIdFront/${user_id}/`,
    requestOptions
  ).then(res => (res.status === 200 ? res.data : null));
}
