import axios from "axios";
import {authHeader} from "utils/auth-header";
import {environment} from "../environments/environment.prod";
import moment from 'moment';

export function getAnnotationsBySource(source) {
    const requestOptions = {
        headers: {
            "Content-Type": "application/json"
        }
    };
    return axios(
        `${environment.annotation_server_url}annotation/getannotations/?source=${source}/`,
        requestOptions
    ).then(res => (res.status === 200 ? res.data : null));
}

async function generateCreator() {
    var exist = false;
    var creator = JSON.parse(localStorage.getItem("userDetails"));
    exist = await doesCreatorExist(creator.id);
    console.log(exist);
    creator.type = "Person";
    return exist ? creator.id : creator;
}

function getTimestamp() {
    var dateInURL = new Date();
    return moment(dateInURL).format("YYYY-MM-DD HH:mm:ssZ");
}

export function doesCreatorExist(user_id) {
    const requestOptions = {
        headers: {
            Authorization: authHeader(),
            "Content-Type": "application/json"
        }
    };
    return axios(
        `${environment.annotation_server_url}annotation/creatorexist/?id=${user_id}`,
        requestOptions
    ).then(res => (res.status === 200 ? res.data : null))
        .then(data => data.message === "creator exists");
}


export async function createAnnotation(values) {
    const requestOptions = {
        headers: {
            Authorization: authHeader(),
            "Content-Type": "application/json"
        },
        body: {
            "@context": "http://www.w3.org/ns/anno.jsonld",
            id: values.id,
            creator: await generateCreator(),
            body: values.body,
            target: values.target,
            type: "Annotation",
            motivation: values.motivation,
            created: getTimestamp()
        }
    }
    return axios.post(
        `${environment.annotation_server_url}annotation/createannotation/`,
        requestOptions.body,
        {
            headers: requestOptions.headers
        }
    );


}