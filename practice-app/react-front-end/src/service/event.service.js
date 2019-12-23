import axios from "axios";
import { environment } from "environments/environment.prod";

export function listEvent() {
    return axios(
        `${environment.api_url}event/list/`
    ).then(res => (res.status === 200 ? res.data : null));
}
