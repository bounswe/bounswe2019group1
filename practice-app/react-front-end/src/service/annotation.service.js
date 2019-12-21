import axios from "axios";
import { authHeader } from "utils/auth-header";
import { environment } from "../environments/environment.prod";
import moment from 'moment';

export function getAnnotationsBySource(source){
    const requestOptions = {
        headers: {
            "Content-Type": "application/json"
        }
    };
    return axios(
        `${environment.api_url}annotation/getannotations/?source=${source}`,
        requestOptions
    ).then(res => (res.status === 200 ? res.data : null));
}
function generateCreator() {
    const currentUser = { token: localStorage.getItem("currentUser") };
    if (currentUser && currentUser.token) {
        return `JWT ${currentUser.token}`.replace(/"/g,"");
    } else {
        return {};
    }
}
function getTimestamp(){
    var dateInURL = new Date();
    return moment(dateInURL).format("YYYY-MM-DD HH:mm:ssZ");
}
