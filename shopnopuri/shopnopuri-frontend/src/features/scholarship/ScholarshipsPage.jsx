import { Link } from "react-router-dom";

export default function ScholarshipsPage() {
    return (
        <div>
            <h1>Scholarships</h1>
            <div className="grid">
                <div className="card">
                    <h3>Search / Filter</h3>
                    <p className="muted">API: GET /api/scholarships (Step 4)</p>
                </div>

                <div className="card">
                    <h3>Saved</h3>
                    <p className="muted">API: GET /api/me/sch/saved</p>
                    <Link className="btn" to="/scholarships/saved">Open Saved</Link>
                </div>

                <div className="card">
                    <h3>Applications</h3>
                    <p className="muted">API: GET /api/me/sch/apps</p>
                    <Link className="btn" to="/scholarships/apps">Open Applications</Link>
                </div>
            </div>
        </div>
    );
}
