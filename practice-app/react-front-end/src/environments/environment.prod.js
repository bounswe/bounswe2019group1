import { secret } from "./environment.private";

export const environment = {
  api_url: "http://backendurl/api",
  static_url: "http://backendurl/static",
  googleApiKey: secret.googleApiKey
};
