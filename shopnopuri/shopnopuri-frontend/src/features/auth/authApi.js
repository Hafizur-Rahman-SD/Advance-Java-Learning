import http from "../../api/http";
import API from "../../api/routes";

export async function loginApi(payload) {
    // payload shape depends on backend (email vs username)
    const res = await http.post(API.AUTH.LOGIN, payload);
    return res.data;
}

export async function whoamiApi() {
    const res = await http.get(API.AUTH.WHOAMI);
    return res.data; // String: "NO_AUTH" or "username [ROLE...]"
}
