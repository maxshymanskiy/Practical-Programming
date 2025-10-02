package Task7;

/*
Завдання № 7.
Відтворіть перекази між банківськими рахунками. Реалізуйте клас Банк та
Рахунок (BankAccount). Один користувач може мати декілька рахунків в
одному чи кількох банках. Рахунок прив’язаний до заданої під час
створення банку валюти. При переказі між рахунками з різною валютою
необхідно конвертувати переказ до валюти цільового рахунку. Комісії:
- Переказ між власними рахунками в межах одного банку: 0%
- Переказ між власними рахунками у різних банках: 2%
- Переказ між рахунками різних користувачів в одному банку: 3%
- Переказ між рахунками різних користувачів у різних банках: 6%
 */

public class Task7 {
    public static void main(String[] args) {
        final Bank privatBank = new Bank("PrivatBank");
        final Bank monoBank = new Bank("MonoBank");
        final Bank ukrsibBank = new Bank("UkrsibBank");

        final BankAccount ivanPrivatUAH = new BankAccount("Ivan", privatBank, "UAH", 10000);
        final BankAccount ivanPrivatUSD = new BankAccount("Ivan", privatBank, "USD", 500);
        final BankAccount ivanMonoEUR = new BankAccount("Ivan", monoBank, "EUR", 300);
        final BankAccount petroPrivatUAH = new BankAccount("Petro", privatBank, "UAH", 2000);
        final BankAccount olenaUkrsibEUR = new BankAccount("Olena", ukrsibBank, "EUR", 800);

        System.out.println("=== INITIAL ACCOUNT STATUS ===");
        printAccounts(ivanPrivatUAH, ivanPrivatUSD, ivanMonoEUR, petroPrivatUAH, olenaUkrsibEUR);
        System.out.println();

        try {
            // 1. Own accounts in the same bank (0% fee)
            System.out.println("1. TRANSFER BETWEEN OWN ACCOUNTS IN SAME BANK:");
            TransferService.transfer(ivanPrivatUAH, ivanPrivatUSD, 1000);
            System.out.println("=".repeat(50) + "\n");

            // 2. Own accounts in different banks (2% fee)
            System.out.println("2. TRANSFER BETWEEN OWN ACCOUNTS IN DIFFERENT BANKS:");
            TransferService.transfer(ivanPrivatUSD, ivanMonoEUR, 100);
            System.out.println("=".repeat(50) + "\n");

            // 3. Different users in the same bank (3% fee)
            System.out.println("3. TRANSFER BETWEEN DIFFERENT USERS IN SAME BANK:");
            TransferService.transfer(ivanPrivatUAH, petroPrivatUAH, 500);
            System.out.println("=".repeat(50) + "\n");

            // 4. Different users in different banks (6% fee)
            System.out.println("4. TRANSFER BETWEEN DIFFERENT USERS IN DIFFERENT BANKS:");
            TransferService.transfer(petroPrivatUAH, olenaUkrsibEUR, 300);
            System.out.println("=".repeat(50) + "\n");

            // 5. Attempt to transfer more than available
            System.out.println("5. ATTEMPT TO TRANSFER MORE THAN AVAILABLE:");
            TransferService.transfer(olenaUkrsibEUR, ivanMonoEUR, 1000);

        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println("\n=== FINAL ACCOUNT STATUS ===");
        printAccounts(ivanPrivatUAH, ivanPrivatUSD, ivanMonoEUR, petroPrivatUAH, olenaUkrsibEUR);
    }

    private static void printAccounts(BankAccount... accounts) {
        for (BankAccount account : accounts) {
            System.out.println("  " + account);
        }
    }
}