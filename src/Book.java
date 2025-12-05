public class Book {
    // Attributes (Private):
    private String bookId;
    private String title;
    private String author;
    private String category;
    private int publicationYear;
    private boolean isAvailable;
    private int totalCopies;
    private int availableCopies;
    private static int totalBooks = 0;

    // Construktors:
    // Default constructor (generate auto bookId)
    public Book() {
        totalBooks++;
        this.bookId = String.format("BK%03d", totalBooks);
        this.title = "";
        this.author = "";
        this.category = "Fiction";
        this.publicationYear = 2025;
        this.totalCopies = 0;
        this.availableCopies = 0;
        this.isAvailable = false;
    }

    // Parameterized constructor
    public Book(String title, String author, String category, int publicationYear, int totalCopies) {
        totalBooks++;
        this.bookId = String.format("BK%03d", totalBooks);
        setTitle(title);
        setAuthor(author);
        setCategory(category);
        setPublicationYear(publicationYear);
        setTotalCopies(totalCopies);
        this.availableCopies = totalCopies;
        this.isAvailable = totalCopies > 0;
    }

    // Methods:
    // Tampilkan info lengkap buku
    public void displayBookInfo() {
        System.out.println("[" + bookId + "] " + title);
        System.out.println("Penulis       : " + author);
        System.out.println("Kategori      : " + category);
        System.out.println("Tahun Terbit  : " + publicationYear);
        System.out.println("Umur Buku     : " + getBookAge() + " tahun");
        System.out.println("Total Copy    : " + totalCopies + " eksemplar");
        System.out.print("Tersedia      : " + availableCopies + " eksemplar | Status: ");
        System.out.print(getAvailabilityStatus());
        if (isNewRelease()) {
            System.out.print(" [NEW RELEASE]");
        }
    }

    // Kurangi availableCopies jika > 0, return true jika berhasil
    public boolean borrowBook() {
        if (this.availableCopies > 0) {
            this.availableCopies--;
            this.isAvailable = this.availableCopies > 0;
            return true;
        }
        System.out.println(" Error: Buku tidak tersedia untuk dipinjam");
        return false;
    }

    // Tambah availableCopies
    public void returnBook() {
        if (this.availableCopies < this.totalCopies) {
            this.availableCopies++;
            this.isAvailable = true;
        }
    }

    //  Hitung umur buku (tahun sekarang - publicationYear)
    public int getBookAge() {
        return 2025 - this.publicationYear;
    }

    //  Return true jika buku terbit dalam 2 tahun terakhir
    public boolean isNewRelease() {
        return getBookAge() <= 2;
    }

    // Ruturn status:
    public String getAvailabilityStatus() {
        if (this.availableCopies > 5) {
            return "Banyak Tersedia ";
        } else if (this.availableCopies >= 1 && this.availableCopies <= 5) {
            return "Terbatas";
        } else {
            return "Tidak Tersedia";
        }
    }

    // Return total buku
    public static int getTotalBooks() {
        return totalBooks;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("✗ Error: Judul tidak boleh kosong");
            this.title = "";
        } else {
            this.title = title;
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            System.out.println(" Error: Penulis tidak boleh kosong");
            this.author = "";
        } else {
            this.author = author;
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        String[] validCategories = {"Fiction", "Non-Fiction", "Science", "Technology", "History"};
        boolean isValid = false;

        if (category != null) {
            for (String validCat : validCategories) {
                if (category.equals(validCat)) {
                    isValid = true;
                    break;
                }
            }
        }

        if (isValid) {
            this.category = category;
        } else {
            System.out.println(" Error: Kategori harus Fiction/Non-Fiction/Science/Technology/History");
            this.category = "Fiction";
        }
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        if (publicationYear >= 1900 && publicationYear <= 2025) {
            this.publicationYear = publicationYear;
        } else {
            System.out.println(" Error: Tahun terbit tidak valid (1900-2025)");
            this.publicationYear = 2025;
        }
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        if (totalCopies >= 0) {
            this.totalCopies = totalCopies;
        } else {
            System.out.println(" Error: Total copies harus >= 0");
            this.totalCopies = 0;
        }
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        if (availableCopies >= 0 && availableCopies <= this.totalCopies) {
            this.availableCopies = availableCopies;
            this.isAvailable = availableCopies > 0;
        } else {
            System.out.println(" Error: Available copies tidak valid");
        }
    }
}