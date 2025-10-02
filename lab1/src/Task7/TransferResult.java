package Task7;

public record TransferResult(double originalAmount, double fee, double feePercent, double finalAmount, boolean success,
                             String message, String fromCurrency, String toCurrency) {

    public void printDetailedInfo() {
        System.out.printf("=== TRANSFER DETAILS ===%n");
        System.out.printf("Transfer amount: %.2f %s%n", originalAmount, fromCurrency);
        System.out.printf("Fee: %.1f%% = %.2f %s%n", feePercent, fee, fromCurrency);
        System.out.printf("Amount after fee: %.2f %s%n", originalAmount - fee, fromCurrency);
        System.out.printf("Converted amount: %.2f %s%n", finalAmount, toCurrency);
        System.out.printf("Status: %s%n", message);
    }
}