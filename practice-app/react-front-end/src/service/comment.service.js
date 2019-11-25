import { environment } from "environments/environment.prod";
import { authHeader } from "utils/auth-header";
import axios from "axios";

export function listCommentByArticleId(article_id) {
  const requestOptions = {
    headers: {
      Authorization: authHeader(),
      "Content-Type": "application/json"
    }
  };
  return axios
    .get(
      `${environment.api_url}article-comment/list/${article_id}`,
      requestOptions
    )
    .then(res => (res.status === 200 ? res.data : null));
}

export function createComment(values) {
  const requestOptions = {
    headers: {
      Authorization: authHeader(),
      "Content-Type": "application/json"
    }
  };
  return axios();
}
