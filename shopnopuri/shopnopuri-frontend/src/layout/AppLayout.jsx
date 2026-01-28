import { Link, NavLink, Outlet, useNavigate } from "react-router-dom";
import { authStorage } from "../features/auth/authStorage";

export default function AppLayout() {
    const navigate = useNavigate();
    const token = authStorage.getToken();

    const onLogout = () => {
        authStorage.clearToken();
        navigate("/login");
    };

    const navClass = ({ isActive }) => "navlink" + (isActive ? " active" : "");

    return (
        <div>
            <header className="topbar">
                <div className="brand">
                    <Link to="/dashboard" className="brandLink">
                        ShopnoPuri
                    </Link>
                    <span className="badge">Frontend</span>
                </div>

                <nav className="nav">
                    <NavLink to="/dashboard" className={navClass}>
                        Dashboard
                    </NavLink>
                    <NavLink to="/universities" className={navClass}>
                        Universities
                    </NavLink>
                    <NavLink to="/career/test" className={navClass}>
                        Career Test
                    </NavLink>
                    <NavLink to="/prediction" className={navClass}>
                        Prediction
                    </NavLink>
                    <NavLink to="/matchmaker" className={navClass}>
                        Matchmaker
                    </NavLink>
                    <NavLink to="/scholarships" className={navClass}>
                        Scholarships
                    </NavLink>
                    <NavLink to="/uniapps" className={navClass}>
                        Applications
                    </NavLink>
                    <NavLink to="/profile" className={navClass}>
                        Profile
                    </NavLink>
                </nav>

                <div className="actions">
                    {!token ? (
                        <Link to="/login" className="btn">
                            Login
                        </Link>
                    ) : (
                        <button className="btn" onClick={onLogout}>
                            Logout
                        </button>
                    )}
                </div>
            </header>

            <main className="container">
                <Outlet />
            </main>

            <footer className="footer">
                <span>ShopnoPuri © {new Date().getFullYear()}</span>
            </footer>
        </div>
    );
}
