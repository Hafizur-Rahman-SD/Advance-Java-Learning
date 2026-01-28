import axios from "axios";
import API from "./routes";
import { authStorage } from "../features/auth/authStorage";

const http = axios.create({
    baseURL: API.BASE_URL,
    headers: { "Content-Type": "application/json" },
});

http.interceptors.request.use(
    (config) => {
        const token = authStorage.getToken();
        if (token) config.headers.Authorization = `Bearer ${token}`;
        return config;
    },
    (error) => Promise.reject(error)
);

http.interceptors.response.use(
    (res) => res,
    (error) => {
        if (error?.response?.status === 401) {
            authStorage.clearToken();
            if (window.location.pathname !== "/login") window.location.href = "/login";
        }
        return Promise.reject(error);
    }
);

export default http;
