package bd.edu.seu.retakequiz4.dto;

import java.math.BigDecimal;

public class DashboardStatsResponse {

    private long totalOrdersToday;
    private long pendingOrdersToday;
    private BigDecimal revenueThisMonth;

    public DashboardStatsResponse(long totalOrdersToday, long pendingOrdersToday, BigDecimal revenueThisMonth) {
        this.totalOrdersToday = totalOrdersToday;
        this.pendingOrdersToday = pendingOrdersToday;
        this.revenueThisMonth = revenueThisMonth;
    }

    public long getTotalOrdersToday() {
        return totalOrdersToday;
    }

    public long getPendingOrdersToday() {
        return pendingOrdersToday;
    }

    public BigDecimal getRevenueThisMonth() {
        return revenueThisMonth;
    }
}
