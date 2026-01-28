import { Link } from "react-router-dom";

export default function NotFoundPage() {
    return (
        <div className="card">
            <h2>404 - Page Not Found</h2>
            <p>URL ta vul hoyeche. Back to Dashboard.</p>
            <Link className="btn" to="/dashboard">Go Dashboard</Link>
        </div>
    );
}
