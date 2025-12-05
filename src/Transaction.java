import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Transaction {
    // Private attributes
    private String transactionId;
    private Member member;
    private Book book;
    private String borrowDate;
    private String dueDate;
    private String returnDate;
    private int daysLate;
    private double lateFee;
    private static int totalTransactions = 0;
    private static final double LATE_FEE_PER_DAY = 2000.0;

    // Constructor
    public Transaction(Member member, Book book, String borrowDate, int borrowDurationDays) {
        totalTransactions++;
        this.transactionId = String.format("TRX%03d", totalTransactions);
        this.member = member;
        this.book = book;
        setBorrowDate(borrowDate);
        this.dueDate = calculateDueDate(borrowDate, borrowDurationDays);
        this.returnDate = null;
        this.daysLate = 0;
        this.lateFee = 0.0;

        // Borrow the book
        book.borrowBook();
    }

    // Calculate due date
    private String calculateDueDate(String borrowDate, int borrowDurationDays) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date borrow = sdf.parse(borrowDate);
            long dueTime = borrow.getTime() + (borrowDurationDays * 24L * 60L * 60L * 1000L);
            Date due = new Date(dueTime);
            return sdf.format(due);
        } catch (ParseException e) {
            System.out.println("✗ Error: Format tanggal tidak valid");
            return borrowDate;
        }
    }

    // Process return
    public void processReturn(String returnDate) {
        if (!isValidDate(returnDate)) {
            System.out.println("✗ Error: Format tanggal tidak valid");
            return;
        }

        if (isReturnBeforeBorrow(returnDate)) {
            System.out.println("✗ Error: Tanggal pengembalian tidak boleh sebelum tanggal pinjam");
            return;
        }

        this.returnDate = returnDate;
        this.daysLate = calculateDaysLate(returnDate);
        if (this.daysLate > 0) {
            calculateLateFee();
        }
        this.book.returnBook();
    }

    // Calculate days late
    private int calculateDaysLate(String returnDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date due = sdf.parse(this.dueDate);
            Date returned = sdf.parse(returnDate);

            long diffInMillies = returned.getTime() - due.getTime();
            long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            return diffInDays > 0 ? (int) diffInDays : 0;
        } catch (ParseException e) {
            return 0;
        }
    }

    // Calculate late fee
    public void calculateLateFee() {
        if (this.daysLate > 0) {
            double baseFee = this.daysLate * LATE_FEE_PER_DAY;
            double discount = this.member.getMembershipDiscount();
            this.lateFee = baseFee * (1 - discount);
        } else {
            this.lateFee = 0.0;
        }
    }

    // Check if overdue
    public boolean isOverdue(String currentDate) {
        if (this.returnDate != null) {
            return false;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date due = sdf.parse(this.dueDate);
            Date current = sdf.parse(currentDate);
            return current.after(due);
        } catch (ParseException e) {
            return false;
        }
    }

    // Get transaction status
    public String getTransactionStatus() {
        if (this.returnDate != null) {
            return "Selesai";
        } else if (isOverdue("05-12-2025")) {
            return "Terlambat";
        } else {
            return "Aktif";
        }
    }

    // Display transaction
    public void displayTransaction() {
        String status = getTransactionStatus();
        System.out.println("[" + transactionId + "] " + status.toUpperCase());
        System.out.println("Peminjam      : " + member.getName() + " (" + member.getMemberId() + ") - " + member.getMembershipType());
        System.out.println("Buku          : " + book.getTitle() + " (" + book.getBookId() + ")");
        System.out.println("Tgl Pinjam    : " + borrowDate);
        System.out.println("Tgl Tempo     : " + dueDate);

        if (returnDate != null) {
            System.out.println("Tgl Kembali   : " + returnDate);
            System.out.println("Terlambat     : " + daysLate + " hari");
            System.out.printf("Denda         : Rp %.0f", lateFee);
            if (daysLate > 0) {
                double originalFee = daysLate * LATE_FEE_PER_DAY;
                System.out.printf(" (Rp %.0f - diskon %.0f%%)", originalFee, member.getMembershipDiscount() * 100);
            }
            System.out.println();
        } else {
            int daysRemaining = calculateDaysRemaining("05-12-2025");
            if (daysRemaining >= 0) {
                System.out.println("Status        : Masih Dipinjam (" + daysRemaining + " hari lagi)");
            } else {
                System.out.println("Status        : Terlambat (" + Math.abs(daysRemaining) + " hari)");
            }
        }
        System.out.println("--------------------------------------------");
    }

    // Calculate days remaining
    private int calculateDaysRemaining(String currentDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date due = sdf.parse(this.dueDate);
            Date current = sdf.parse(currentDate);

            long diffInMillies = due.getTime() - current.getTime();
            long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            return (int) diffInDays;
        } catch (ParseException e) {
            return 0;
        }
    }

    // Validate date format
    private boolean isValidDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // Check if return date is before borrow date
    private boolean isReturnBeforeBorrow(String returnDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date borrow = sdf.parse(this.borrowDate);
            Date returned = sdf.parse(returnDate);
            return returned.before(borrow);
        } catch (ParseException e) {
            return false;
        }
    }

    // Static method to get total transactions
    public static int getTotalTransactions() {
        return totalTransactions;
    }

    // Getters and Setters
    public String getTransactionId() {
        return transactionId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        if (isValidDate(borrowDate)) {
            this.borrowDate = borrowDate;
        } else {
            System.out.println("✗ Error: Format tanggal harus DD-MM-YYYY");
        }
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getDaysLate() {
        return daysLate;
    }

    public void setDaysLate(int daysLate) {
        this.daysLate = daysLate;
    }

    public double getLateFee() {
        return lateFee;
    }

    public void setLateFee(double lateFee) {
        this.lateFee = lateFee;
    }

    public static double getLateFeePerDay() {
        return LATE_FEE_PER_DAY;
    }
}