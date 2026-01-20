package bd.edu.seu.retakequiz4.service;

import bd.edu.seu.retakequiz4.model.OrderStatus;
import bd.edu.seu.retakequiz4.repo.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.*;

@Service
public class DashboardService {

    private final OrderRepository repo;

    public DashboardService(OrderRepository repo) {
        this.repo = repo;
    }

    public long totalOrdersToday() {
        LocalDate today = LocalDate.now();
        Instant start = today.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant end = today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant();
        return repo.countByCreatedAtBetween(start, end);
    }

    public long pendingOrdersToday() {
        LocalDate today = LocalDate.now();
        Instant start = today.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant end = today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant();
        return repo.countByStatusAndCreatedAtBetween(OrderStatus.PENDING, start, end);
    }

    public BigDecimal revenueThisMonth() {
        return BigDecimal.ZERO;
    }
}
