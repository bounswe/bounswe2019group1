import { secret } from "./environment.private";

export const environment = {
  api_url: "http://35.163.120.227:8000/",
  annotation_server_url: "http://35.163.120.227:8020/",
  static_url: "http://backendurl/static",
  googleApiKey: secret.googleApiKey
};
