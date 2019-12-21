import axios from "axios";
import { authHeader } from "utils/auth-header";
import { environment } from "../environments/environment.prod";

export function getNotifications(){
    const requestOptions = {
        headers: {
            Authorization: authHeader(),
            "Content-Type": "application/json"
        }
    };
    return axios(
        `${environment.api_url}notification/listsetnotification/`,
        requestOptions
    ).then(res => (res.status === 200 ? res.data : null));
}

