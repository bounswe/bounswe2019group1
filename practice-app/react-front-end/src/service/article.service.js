import { environment } from "environments/environment.prod";
import { authHeader } from "utils/auth-header";
import axios from "axios";

export function getPublicArticles() {
  return axios
    .get(`${environment.api_url}article/listPublicArticles/`)
    .then(res => (res.status === 200 ? res.data : null));
}

export function getMyArticles() {
  const requestOptions = {
    headers: {
      Authorization: authHeader()
    }
  };
  return axios
    .get(`${environment.api_url}article/list/`, {
      headers: requestOptions.headers
    })
    .then(res => (res.status === 200 ? res.data : null));
}

export function getArticleById(article_id) {
  const requestOptions = {
    method: "GET",
    headers: {
      Authorization: authHeader(),
      "Content-Type": "application/json"
    },
    body: {
      id: article_id
    }
  };
  return axios(`${environment.api_url}article/getById/`, requestOptions).then(
    res => (res.status === 200 ? res.data : null)
  );
}

export function getArticlesByUserId(userid) {
  const requestOptions = {
    method: "GET",
    headers: {
      ContentType: "application/json"
    },
    body: {
      id: userid
    }
  };
  return axios
    .get(`${environment.api_url}article/listArticleByUserId/`, {
      body: requestOptions.body,
      headers: requestOptions.headers
    })
    .then(res => (res.status === 200 ? res.data : null));
}

export function createArticle(values) {
  const requestOptions = {
    headers: {
      Authorization: authHeader(),
      "Content-Type": "application/json"
    },
    body: {
      title: values.title,
      content: values.content,
      is_public: values.is_public
    }
  };
  return axios.post(
    `${environment.api_url}article/create/`,
    requestOptions.body,
    {
      headers: requestOptions.headers
    }
  );
}

export function updateArticle(values) {
  const requestOptions = {
    headers: {
      Authorization: authHeader(),
      ContentType: "application/json"
    },
    body: {
      id: values.id,
      title: values.title,
      content: values.content,
      is_public: values.is_public
    }
  };
  return axios
    .put(
      `${environment.api_url}article/update`,
      {
        body: requestOptions.body
      },
      { headers: requestOptions.headers }
    )
    .then(res => (res.status === 200 ? res.data : null));
}
