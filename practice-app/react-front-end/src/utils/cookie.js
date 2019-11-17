import cookie from "react-cookie";

export function setCookie(hours, cookie_path) {
  let date = new Date();
  date.setTime(d.getTime() + (hours*60*60*1000));
  cookie.set("onboarded", true, {path: cookie_path, expires: d});
};
