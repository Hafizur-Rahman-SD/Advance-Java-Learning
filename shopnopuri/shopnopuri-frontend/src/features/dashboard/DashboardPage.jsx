import { useEffect, useState } from "react";
import { whoamiApi } from "../auth/authApi";
import { authStorage } from "../auth/authStorage";

export default function DashboardPage() {
    const [loading, setLoading] = useState(true);
    const [who, setWho] = useState("");
    const [err, setErr] = useState("");

    useEffect(() => {
        const load = async () => {
            setErr("");
            setLoading(true);
            try {
                const data = await whoamiApi();
                setWho(String(data));
            } catch (e) {
                setErr("WhoAmI failed. Token valid কিনা check করো।");
            } finally {
                setLoading(false);
            }
        };
        load();
    }, []);

    return (
        <div>
            <h1>Dashboard</h1>

            <div className="grid">
                <div className="card">
                    <h3>Login Status</h3>
                    <p className="muted">
                        {authStorage.getToken() ? "Logged in ✅" : "No token ❌"}
                    </p>
                </div>

                <div className="card">
                    <h3>WhoAmI</h3>
                    {loading ? (
                        <p className="muted">Loading...</p>
                    ) : err ? (
                        <p className="muted">⚠️ {err}</p>
                    ) : (
                        <p style={{ margin: 0 }}>{who}</p>
                    )}
                    <p className="muted" style={{ marginTop: 8 }}>
                        Endpoint: <code>/api/test/whoami</code>
                    </p>
                </div>

                <div className="card">
                    <h3>Next</h3>
                    <p className="muted">Step 4: Profile/Universities API data load.</p>
                </div>
            </div>
        </div>
    );
}
