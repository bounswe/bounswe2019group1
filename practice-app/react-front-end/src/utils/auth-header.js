export function authHeader() {
  const currentUser = { token: localStorage.getItem("currentUser") };
  if (currentUser && currentUser.token) {
    return { Authorization: `Bearer ${currentUser.token}` };
  } else {
    return {};
  }
}
