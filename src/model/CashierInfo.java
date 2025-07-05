package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CashierInfo {
    private String cashierName;
    private LocalDateTime orderTime;

    public CashierInfo(String cashierName) {
        this.cashierName = cashierName;
        this.orderTime = LocalDateTime.now();
    }

    public String getCashierName() {
        return cashierName;
    }

    public String getFormattedOrderTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return orderTime.format(formatter);
    }

    @Override
    public String toString() {
        return "\n----------------------\n" + "Cashier: " + cashierName + "\n" + "Order Time: " + getFormattedOrderTime();
    }
}
