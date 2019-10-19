export function authHeader() {
  const currentUser = { token: localStorage.getItem("currentUser") };
  if (currentUser && currentUser.token) {
    return `JWT ${currentUser.token}`.replace(/"/g,"");
  } else {
    return {};
  }
}
