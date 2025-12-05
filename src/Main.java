import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Header
        System.out.println("============================================");
        System.out.println("LIBRARY MANAGEMENT SYSTEM");
        System.out.println("============================================\n");

        // ==================== REGISTRASI ANGGOTA ====================
        System.out.println("=== REGISTRASI ANGGOTA ===");
        ArrayList<Member> members = new ArrayList<>();

        Member m1 = new Member("Alice Johnson", "alice.j@email.com", "081234567890", 2020, "Platinum");
        members.add(m1);
        System.out.println(" Anggota berhasil ditambahkan: " + m1.getMemberId() + " - " + m1.getName() + " (" + m1.getMembershipType() + ")");

        Member m2 = new Member("Bob Smith", "bob.smith@email.com", "081298765432", 2022, "Gold");
        members.add(m2);
        System.out.println(" Anggota berhasil ditambahkan: " + m2.getMemberId() + " - " + m2.getName() + " (" + m2.getMembershipType() + ")");

        Member m3 = new Member("Charlie Brown", "charlie.b@email.com", "081223456789", 2024, "Silver");
        members.add(m3);
        System.out.println(" Anggota berhasil ditambahkan: " + m3.getMemberId() + " - " + m3.getName() + " (" + m3.getMembershipType() + ")");

        Member m4 = new Member("Diana Prince", "diana.p@email.com", "081287654321", 2021, "Gold");
        members.add(m4);
        System.out.println(" Anggota berhasil ditambahkan: " + m4.getMemberId() + " - " + m4.getName() + " (" + m4.getMembershipType() + ")");

        // ==================== REGISTRASI BUKU ====================
        System.out.println("\n=== REGISTRASI BUKU ===");
        ArrayList<Book> books = new ArrayList<>();

        Book b1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fiction", 1925, 5);
        books.add(b1);
        System.out.println(" Buku berhasil ditambahkan: " + b1.getBookId() + " - \"" + b1.getTitle() + "\" by " + b1.getAuthor());

        Book b2 = new Book("Clean Code", "Robert C. Martin", "Technology", 2008, 8);
        books.add(b2);
        System.out.println(" Buku berhasil ditambahkan: " + b2.getBookId() + " - \"" + b2.getTitle() + "\" by " + b2.getAuthor());

        Book b3 = new Book("Sapiens", "Yuval Noah Harari", "History", 2011, 6);
        books.add(b3);
        System.out.println(" Buku berhasil ditambahkan: " + b3.getBookId() + " - \"" + b3.getTitle() + "\" by " + b3.getAuthor());

        Book b4 = new Book("1984", "George Orwell", "Fiction", 1949, 4);
        books.add(b4);
        System.out.println(" Buku berhasil ditambahkan: " + b4.getBookId() + " - \"" + b4.getTitle() + "\" by " + b4.getAuthor());

        Book b5 = new Book("The Pragmatic Programmer", "Hunt & Thomas", "Technology", 1999, 3);
        books.add(b5);
        System.out.println(" Buku berhasil ditambahkan: " + b5.getBookId() + " - \"" + b5.getTitle() + "\" by " + b5.getAuthor());

        Book b6 = new Book("Atomic Habits", "James Clear", "Non-Fiction", 2018, 10);
        books.add(b6);
        System.out.println(" Buku berhasil ditambahkan: " + b6.getBookId() + " - \"" + b6.getTitle() + "\" by " + b6.getAuthor());

        // ==================== TRANSAKSI PEMINJAMAN ====================
        System.out.println("\n=== TRANSAKSI PEMINJAMAN ===");
        ArrayList<Transaction> transactions = new ArrayList<>();

        Transaction t1 = new Transaction(m1, b2, "01-12-2025", 14);
        transactions.add(t1);
        System.out.println(" Peminjaman berhasil: " + m1.getName() + " meminjam \"" + b2.getTitle() + "\"");
        System.out.println(" Tanggal Pinjam: " + t1.getBorrowDate() + " | Jatuh Tempo: " + t1.getDueDate());

        Transaction t2 = new Transaction(m2, b1, "05-12-2025", 14);
        transactions.add(t2);
        System.out.println(" Peminjaman berhasil: " + m2.getName() + " meminjam \"" + b1.getTitle() + "\"");
        System.out.println(" Tanggal Pinjam: " + t2.getBorrowDate() + " | Jatuh Tempo: " + t2.getDueDate());

        Transaction t3 = new Transaction(m3, b3, "10-11-2025", 14);
        transactions.add(t3);
        System.out.println(" Peminjaman berhasil: " + m3.getName() + " meminjam \"" + b3.getTitle() + "\"");
        System.out.println(" Tanggal Pinjam: " + t3.getBorrowDate() + " | Jatuh Tempo: " + t3.getDueDate());

        Transaction t4 = new Transaction(m4, b4, "20-11-2025", 14);
        transactions.add(t4);
        System.out.println(" Peminjaman berhasil: " + m4.getName() + " meminjam \"" + b4.getTitle() + "\"");
        System.out.println(" Tanggal Pinjam: " + t4.getBorrowDate() + " | Jatuh Tempo: " + t4.getDueDate());

        // Additional transactions
        Transaction t5 = new Transaction(m1, b5, "25-11-2025", 14);
        transactions.add(t5);
        System.out.println(" Peminjaman berhasil: " + m1.getName() + " meminjam \"" + b5.getTitle() + "\"");

        Transaction t6 = new Transaction(m2, b6, "28-11-2025", 14);
        transactions.add(t6);
        System.out.println(" Peminjaman berhasil: " + m2.getName() + " meminjam \"" + b6.getTitle() + "\"");

        Transaction t7 = new Transaction(m3, b1, "15-11-2025", 14);
        transactions.add(t7);
        System.out.println(" Peminjaman berhasil: " + m3.getName() + " meminjam \"" + b1.getTitle() + "\"");

        Transaction t8 = new Transaction(m4, b2, "18-11-2025", 14);
        transactions.add(t8);
        System.out.println(" Peminjaman berhasil: " + m4.getName() + " meminjam \"" + b2.getTitle() + "\"");

        // ==================== PENGEMBALIAN BUKU ====================
        System.out.println("\n=== PENGEMBALIAN BUKU ===");

        t3.processReturn("04-12-2025");
        System.out.println(" " + m3.getName() + " mengembalikan \"" + b3.getTitle() + "\"");
        System.out.println(" Tanggal Kembali: " + t3.getReturnDate() + " | Terlambat: " + t3.getDaysLate() + " hari");
        System.out.printf(" Denda: Rp %.0f (setelah diskon %.0f%%)\n", t3.getLateFee(), m3.getMembershipDiscount() * 100);

        t4.processReturn("03-12-2025");
        System.out.println(" " + m4.getName() + " mengembalikan \"" + b4.getTitle() + "\"");
        System.out.println(" Tanggal Kembali: " + t4.getReturnDate() + " | Tepat Waktu");
        System.out.println(" Denda: Rp 0");

        t7.processReturn("02-12-2025");
        System.out.println(" " + m3.getName() + " mengembalikan \"" + b1.getTitle() + "\"");
        System.out.println(" Tanggal Kembali: " + t7.getReturnDate() + " | Terlambat: " + t7.getDaysLate() + " hari");
        System.out.printf(" Denda: Rp %.0f\n", t7.getLateFee());

        t8.processReturn("01-12-2025");
        System.out.println(" " + m4.getName() + " mengembalikan \"" + b2.getTitle() + "\"");

        // ==================== DAFTAR ANGGOTA ====================
        System.out.println("\n============================================");
        System.out.println("DAFTAR ANGGOTA PERPUSTAKAAN");
        System.out.println("============================================");
        for (Member member : members) {
            member.displayInfo();
        }
        System.out.println("Total Anggota Terdaftar: " + Member.getTotalMembers());

        // ==================== DAFTAR BUKU ====================
        System.out.println("\n============================================");
        System.out.println("DAFTAR KOLEKSI BUKU");
        System.out.println("============================================");
        for (Book book : books) {
            book.displayBookInfo();
        }
        System.out.println("Total Buku Terdaftar: " + Book.getTotalBooks());

        // ==================== DAFTAR TRANSAKSI ====================
        System.out.println("\n============================================");
        System.out.println("DAFTAR TRANSAKSI PEMINJAMAN");
        System.out.println("============================================");
        for (Transaction transaction : transactions) {
            transaction.displayTransaction();
        }

        // ==================== STATISTIK ====================
        System.out.println("\n============================================");
        System.out.println("STATISTIK SISTEM");
        System.out.println("============================================");
        System.out.println("Total Anggota Terdaftar    : " + Member.getTotalMembers() + " orang");
        System.out.println("Total Buku Tersedia        : " + Book.getTotalBooks() + " judul");
        System.out.println("Total Transaksi            : " + Transaction.getTotalTransactions() + " transaksi");

        int activeTransactions = 0;
        int overdueTransactions = 0;
        double totalFees = 0.0;

        for (Transaction t : transactions) {
            if (t.getTransactionStatus().equals("Aktif")) {
                activeTransactions++;
            }
            if (t.getReturnDate() != null && t.getDaysLate() > 0) {
                overdueTransactions++;
            }
            totalFees += t.getLateFee();
        }

        System.out.println("Transaksi Aktif            : " + activeTransactions + " peminjaman");
        System.out.println("Transaksi Terlambat        : " + overdueTransactions + " peminjaman");
        System.out.printf("Total Denda Terkumpul      : Rp %.0f\n", totalFees);
        System.out.println("\nAnggota Paling Aktif       : " + m1.getName() + " (" + m1.getMembershipType() + ")");
        System.out.println("Buku Paling Populer        : " + b2.getTitle() + " (" + b2.getCategory() + ")");
        System.out.println("Kategori Favorit           : Technology & Fiction");
        System.out.println("============================================");

        // ==================== TEST UPGRADE MEMBERSHIP ====================
        System.out.println("\n=== TEST UPGRADE MEMBERSHIP ===");
        System.out.print(" Sebelum upgrade: " + m3.getName() + " - " + m3.getMembershipType() + "\n");
        m3.upgradeMembership("Gold");
        System.out.println(" Batas Pinjam Baru: " + m3.getMaxBorrowLimit() + " buku | Diskon Denda Baru: " + (int)(m3.getMembershipDiscount() * 100) + "%");

        // ==================== TEST VALIDASI ====================
        System.out.println("\n=== TEST VALIDASI ===");
        Member invalidMember = new Member();
        invalidMember.setEmail("invalidemail");
        invalidMember.setPhoneNumber("123");
        invalidMember.setMembershipType("Bronze");

        Book invalidBook = new Book();
        invalidBook.setPublicationYear(1800);
        invalidBook.setCategory("Comics");

        Book unavailableBook = new Book("Test Book", "Test Author", "Fiction", 2020, 0);
        unavailableBook.borrowBook();

        System.out.println("\n============================================");
        System.out.println("PROGRAM SELESAI");
        System.out.println("============================================");
    }
}