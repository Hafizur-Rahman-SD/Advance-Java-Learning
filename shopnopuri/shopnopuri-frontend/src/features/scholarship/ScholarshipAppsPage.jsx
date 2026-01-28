export default function ScholarshipAppsPage() {
    return (
        <div>
            <h1>Scholarship Applications</h1>
            <div className="card">
                <p className="muted">API: GET /api/me/sch/apps (Step 4)</p>
                <p className="muted">Also: POST /api/me/sch/{`{id}`}/apply</p>
            </div>
        </div>
    );
}
