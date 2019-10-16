import { authenticationService } from "../service/authentication.service";

export function authHeader() {
  // return authorization header with jwt token
  // const currentUser = authenticationService.currentUserValue;
  const currentUser = { token: "jwttoken" };
  if (currentUser && currentUser.token) {
    return { Authorization: `Bearer ${currentUser.token}` };
  } else {
    return {};
  }
}
