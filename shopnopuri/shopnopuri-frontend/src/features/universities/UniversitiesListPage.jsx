import { Link } from "react-router-dom";

export default function UniversitiesListPage() {
    return (
        <div>
            <h1>Universities</h1>
            <div className="card">
                <p className="muted">API: list + details (Step 4 e real data)</p>
                <p>Demo link:</p>
                <Link className="btn" to="/universities/1">Open University Details (id=1)</Link>
            </div>
        </div>
    );
}
