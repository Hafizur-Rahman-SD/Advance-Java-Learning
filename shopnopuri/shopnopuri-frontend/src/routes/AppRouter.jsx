import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import AppLayout from "../layout/AppLayout";
import ProtectedRoute from "./ProtectedRoute";

// Pages
import LoginPage from "../features/auth/LoginPage";
import DashboardPage from "../features/dashboard/DashboardPage";
import ProfilePage from "../features/profile/ProfilePage.jsx";
import UniversitiesListPage from "../features/universities/UniversitiesListPage";
import UniversityDetailsPage from "../features/universities/UniversityDetailsPage";
import CareerTestPage from "../features/career/CareerTestPage";
import CareerResultPage from "../features/career/CareerResultPage";
import PredictionPage from "../features/prediction/PredictionPage";
import MatchmakerPage from "../features/matchmaker/MatchmakerPage";
import ScholarshipsPage from "../features/scholarship/ScholarshipsPage";
import SavedScholarshipsPage from "../features/scholarship/SavedScholarshipsPage";
import ScholarshipAppsPage from "../features/scholarship/ScholarshipAppsPage";
import UniAppsPage from "../features/uniapp/UniAppsPage";
import NotFoundPage from "../routes/NotFoundPage";

export default function AppRouter() {
    return (
        <BrowserRouter>
            <Routes>
                {/* Public */}
                <Route path="/login" element={<LoginPage />} />

                {/* App Layout wrapper */}
                <Route element={<AppLayout />}>
                    <Route path="/" element={<Navigate to="/dashboard" replace />} />

                    {/* Protected */}
                    <Route
                        path="/dashboard"
                        element={
                            <ProtectedRoute>
                                <DashboardPage />
                            </ProtectedRoute>
                        }
                    />

                    <Route
                        path="/profile"
                        element={
                            <ProtectedRoute>
                                <ProfilePage />
                            </ProtectedRoute>
                        }
                    />

                    <Route
                        path="/universities"
                        element={
                            <ProtectedRoute>
                                <UniversitiesListPage />
                            </ProtectedRoute>
                        }
                    />
                    <Route
                        path="/universities/:id"
                        element={
                            <ProtectedRoute>
                                <UniversityDetailsPage />
                            </ProtectedRoute>
                        }
                    />

                    <Route
                        path="/career/test"
                        element={
                            <ProtectedRoute>
                                <CareerTestPage />
                            </ProtectedRoute>
                        }
                    />
                    <Route
                        path="/career/result"
                        element={
                            <ProtectedRoute>
                                <CareerResultPage />
                            </ProtectedRoute>
                        }
                    />

                    <Route
                        path="/prediction"
                        element={
                            <ProtectedRoute>
                                <PredictionPage />
                            </ProtectedRoute>
                        }
                    />

                    <Route
                        path="/matchmaker"
                        element={
                            <ProtectedRoute>
                                <MatchmakerPage />
                            </ProtectedRoute>
                        }
                    />

                    <Route
                        path="/scholarships"
                        element={
                            <ProtectedRoute>
                                <ScholarshipsPage />
                            </ProtectedRoute>
                        }
                    />
                    <Route
                        path="/scholarships/saved"
                        element={
                            <ProtectedRoute>
                                <SavedScholarshipsPage />
                            </ProtectedRoute>
                        }
                    />
                    <Route
                        path="/scholarships/apps"
                        element={
                            <ProtectedRoute>
                                <ScholarshipAppsPage />
                            </ProtectedRoute>
                        }
                    />

                    <Route
                        path="/uniapps"
                        element={
                            <ProtectedRoute>
                                <UniAppsPage />
                            </ProtectedRoute>
                        }
                    />

                    {/* 404 */}
                    <Route path="*" element={<NotFoundPage />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
}
