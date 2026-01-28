import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { loginApi } from "./authApi";
import { authStorage } from "./authStorage";

export default function LoginPage() {
    const navigate = useNavigate();
    const location = useLocation();
    const from = location.state?.from || "/dashboard";

    const [username, setUsername] = useState(""); // or email
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const [err, setErr] = useState("");

    useEffect(() => {
        if (authStorage.getToken()) navigate("/dashboard");
    }, [navigate]);

    const onSubmit = async (e) => {
        e.preventDefault();
        setErr("");

        if (!username.trim() || !password.trim()) {
            setErr("Username/Email এবং Password দুটাই লাগবে।");
            return;
        }

        try {
            setLoading(true);

            // Change "username" to "email" if your backend expects email
            const data = await loginApi({
                username: username.trim(),
                email: username.trim(),
                password,
            });

            const token =
                data?.token || data?.accessToken || data?.jwt || data?.access_token;

            if (!token) {
                setErr("Login OK কিন্তু token key পেলাম না। Login response দেখাতে হবে।");
                return;
            }

            authStorage.setToken(token);
            navigate(from);
        } catch (e2) {
            const msg =
                e2?.response?.data?.message ||
                e2?.response?.data?.error ||
                "Login failed. Credentials বা endpoint issue হতে পারে।";
            setErr(msg);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="authWrap">
            <div className="card authCard">
                <h2>Login</h2>
                <p className="muted">Token auto-save হবে, user কে paste করতে হবে না।</p>

                <form onSubmit={onSubmit}>
                    <label className="label">Username / Email</label>
                    <input
                        className="input"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        placeholder="your username/email"
                        autoComplete="username"
                    />

                    <label className="label">Password</label>
                    <input
                        className="input"
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        placeholder="••••••••"
                        autoComplete="current-password"
                    />

                    {err && (
                        <div style={{ marginTop: 10 }} className="card">
                            <b>⚠️ Error:</b> <span className="muted">{err}</span>
                        </div>
                    )}

                    <div className="row">
                        <button className="btn" type="submit" disabled={loading}>
                            {loading ? "Logging in..." : "Login"}
                        </button>
                        <button
                            className="btn btnGhost"
                            type="button"
                            onClick={() => {
                                authStorage.clearToken();
                                setUsername("");
                                setPassword("");
                                setErr("");
                            }}
                        >
                            Clear
                        </button>
                    </div>
                </form>

                <p className="muted" style={{ marginTop: 12 }}>
                    IELTS word: <b>authenticate</b> = verify identity (login)
                </p>
            </div>
        </div>
    );
}
