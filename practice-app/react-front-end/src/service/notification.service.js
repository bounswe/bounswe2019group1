import axios from "axios";
import { authHeader } from "utils/auth-header";
import { environment } from "../environments/environment.prod";

export function getNotifications(){
    const requestOptions = {
        headers: {
            Authorization: authHeader()
        }
    };
    return axios(
        `${environment.api_url}notification/listsetnotification/`,
        requestOptions
    ).then(res => (res.status === 200 ? res.data : null));
}

export function setEquipmentNotification(values) {
    const requestOptions = {
        headers: {
            Authorization: authHeader(),
            "Content-Type": "application/json"
        },
        body: {
            currency: values.currency,
            amount: values.amount,
            is_bigger: values.is_bigger
        }
    };
    return axios.post(
        `${environment.api_url}notification/setnotification/`,
        requestOptions.body,
        {
            headers: requestOptions.headers
        }
    );
}