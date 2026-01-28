import { Link } from "react-router-dom";

export default function CareerTestPage() {
    return (
        <div>
            <h1>Career Test</h1>
            <div className="card">
                <p className="muted">API: Career Test submit (Step 4)</p>
                <Link className="btn" to="/career/result">Go to Result (dummy)</Link>
            </div>
        </div>
    );
}
