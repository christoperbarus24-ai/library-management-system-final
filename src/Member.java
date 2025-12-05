import java.util.UUID;

public class Member {
    // Attributes (Private):
    private String memberId;
    private String name;
    private String email;
    private String phoneNumber;
    private int registrationYear;
    private String membershipType;
    private static int totalMembers = 0;

    // Constructors:
    // Default constructor (generate auto memberId)
    public Member() {
        totalMembers++;
        this.memberId = String.format("MBR%03d", totalMembers);
        this.name = "";
        this.email = "";
        this.phoneNumber = "";
        this.registrationYear = 2025;
        this.membershipType = "Silver";
    }

    // Parameterized constructor
    public Member(String name, String email, String phoneNumber, int registrationYear, String membershipType) {
        totalMembers++;
        this.memberId = String.format("MBR%03d", totalMembers);
        setName(name);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setRegistrationYear(registrationYear);
        setMembershipType(membershipType);
    }

    // Methods:
    // Tampilkan info lengkap anggota
    public void displayInfo() {
        System.out.println( memberId + name);
        System.out.println("Email         : " + email);
        System.out.println("Phone         : " + phoneNumber);
        System.out.println("Tahun Daftar  : " + registrationYear);
        System.out.println("Durasi Member : " + getMembershipDuration() + " tahun");
        System.out.println("Batas Pinjam  : " + getMaxBorrowLimit() + " buku");
        System.out.println("Diskon Denda  : " + (int)(getMembershipDiscount() * 100) + "%");
    }

    // Upgrade tipe membership (validasi: Silver → Gold → Platinum)
    public void upgradeMembership(String newType) {
        if (this.membershipType.equals("Silver") && (newType.equals("Gold") || newType.equals("Platinum"))) {
            this.membershipType = newType;
            System.out.println(" " + name + " berhasil di-upgrade ke " + newType + "!");
        } else if (this.membershipType.equals("Gold") && newType.equals("Platinum")) {
            this.membershipType = newType;
            System.out.println(" " + name + " berhasil di-upgrade ke " + newType + "!");
        } else {
            System.out.println(" Upgrade tidak valid. Urutan: Silver → Gold → Platinum");
        }
    }

    // Return batas pinjam berdasarkan membership:
    public int getMaxBorrowLimit() {
        switch (this.membershipType) {
            case "Platinum": return 10;
            case "Gold": return 7;
            case "Silver": return 5;
            default: return 5;
        }
    }

    // Hitung lama menjadi anggota (tahun sekarang - registrationYear)
    public int getMembershipDuration() {
        return 2025 - this.registrationYear;
    }

    // Return diskon denda keterlambatan:
    public double getMembershipDiscount() {
        switch (this.membershipType) {
            case "Platinum": return 50;
            case "Gold": return 30;
            case "Silver": return 10;
            default: return 10;
        }
    }

    // Return total anggota yang telah dibuat
    public static int getTotalMembers() {
        return totalMembers;
    }

    // Getters & Setters untuk semua private attributes
    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println(" Error: Nama tidak boleh kosong");
            this.name = "";
        } else {
            this.name = name;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && email.contains("@") && email.contains(".")) {
            this.email = email;
        } else {
            System.out.println(" Error: Email tidak valid (harus mengandung @ dan .)");
            this.email = "";
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && phoneNumber.length() >= 10 && phoneNumber.length() <= 13) {
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println(" Error: Nomor telepon harus 10-13 digit");
            this.phoneNumber = "";
        }
    }

    public int getRegistrationYear() {
        return registrationYear;
    }

    public void setRegistrationYear(int registrationYear) {
        if (registrationYear >= 2015 && registrationYear <= 2025) {
            this.registrationYear = registrationYear;
        } else {
            System.out.println(" Error: Tahun registrasi harus antara 2015-2025");
            this.registrationYear = 2025;
        }
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        if (membershipType != null &&
                (membershipType.equals("Silver") || membershipType.equals("Gold") || membershipType.equals("Platinum"))) {
            this.membershipType = membershipType;
        } else {
            System.out.println(" Error: Membership type harus Silver/Gold/Platinum");
            this.membershipType = "Silver";
        }
    }
}