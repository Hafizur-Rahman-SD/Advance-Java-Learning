export default function SavedScholarshipsPage() {
    return (
        <div>
            <h1>Saved Scholarships</h1>
            <div className="card">
                <p className="muted">API: GET /api/me/sch/saved (Step 4)</p>
                <p className="muted">Also: POST /api/me/sch/{`{id}`}/save</p>
            </div>
        </div>
    );
}
