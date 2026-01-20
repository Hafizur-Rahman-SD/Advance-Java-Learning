package bd.edu.seu.retakequiz4.controller;

import bd.edu.seu.retakequiz4.dto.DashboardStatsResponse;
import bd.edu.seu.retakequiz4.service.DashboardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/stats")
    public DashboardStatsResponse stats() {
        long total = service.totalOrdersToday();
        long pending = service.pendingOrdersToday();
        return new DashboardStatsResponse(total, pending, service.revenueThisMonth());
    }
}
