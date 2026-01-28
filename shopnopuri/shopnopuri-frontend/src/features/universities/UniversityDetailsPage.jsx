import { useParams } from "react-router-dom";

export default function UniversityDetailsPage() {
    const { id } = useParams();

    return (
        <div>
            <h1>University Details</h1>
            <div className="card">
                <p className="muted">
                    Showing dummy details for ID: <b>{id}</b>
                </p>
                <p className="muted">API: university details (Step 4)</p>
            </div>
        </div>
    );
}
