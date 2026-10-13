/**
 * Abstract Class: Kendaraan
 * Merepresentasikan kendaraan secara umum sebagai base class
 * dalam sistem rental kendaraan.
 *
 * Konsep OOP yang diterapkan:
 * - Abstract Class: Kendaraan tidak bisa diinstansiasi langsung
 * - Encapsulation: atribut private dengan getter/setter
 * - Polymorphism: method abstract yang di-override di subclass
 */
public abstract class Kendaraan {

    // === ATRIBUT (Encapsulation - private) ===
    private String idKendaraan;
    private String merek;
    private String model;
    private int tahunProduksi;
    private String warna;
    private double hargaSewaPerHari;
    private boolean tersedia;

    // === CONSTRUCTOR ===
    public Kendaraan(String idKendaraan, String merek, String model,
                     int tahunProduksi, String warna, double hargaSewaPerHari) {
        this.idKendaraan     = idKendaraan;
        this.merek           = merek;
        this.model           = model;
        this.tahunProduksi   = tahunProduksi;
        this.warna           = warna;
        this.hargaSewaPerHari = hargaSewaPerHari;
        this.tersedia        = true; // default tersedia saat pertama kali didaftarkan
    }

    // === ABSTRACT METHOD (Polymorphism) ===
    /**
     * Setiap subclass wajib mengimplementasikan deskripsi kendaraannya sendiri.
     */
    public abstract String getDeskripsi();

    /**
     * Setiap subclass wajib menghitung biaya tambahan (mis. bensin, asuransi) sendiri.
     */
    public abstract double hitungBiayaTambahan(int jumlahHari);

    // === CONCRETE METHOD ===
    /**
     * Menghitung total biaya sewa kendaraan.
     * @param jumlahHari lama penyewaan dalam hari
     * @return total biaya sewa
     */
    public double hitungTotalBiaya(int jumlahHari) {
        return (hargaSewaPerHari * jumlahHari) + hitungBiayaTambahan(jumlahHari);
    }

    /**
     * Menampilkan informasi umum kendaraan.
     */
    public void tampilkanInfo() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("  ID Kendaraan   : " + idKendaraan);
        System.out.println("  Merek          : " + merek);
        System.out.println("  Model          : " + model);
        System.out.println("  Tahun Produksi : " + tahunProduksi);
        System.out.println("  Warna          : " + warna);
        System.out.printf ("  Harga Sewa/Hari: Rp %.2f%n", hargaSewaPerHari);
        System.out.println("  Status         : " + (tersedia ? "✔ TERSEDIA" : "✘ DISEWA"));
        System.out.println("  Deskripsi      : " + getDeskripsi());
        System.out.println("╚══════════════════════════════════════════╝");
    }

    // === GETTER & SETTER (Encapsulation) ===
    public String getIdKendaraan()              { return idKendaraan; }
    public String getMerek()                    { return merek; }
    public String getModel()                    { return model; }
    public int    getTahunProduksi()            { return tahunProduksi; }
    public String getWarna()                    { return warna; }
    public double getHargaSewaPerHari()         { return hargaSewaPerHari; }
    public boolean isTersedia()                 { return tersedia; }

    public void setHargaSewaPerHari(double harga) {
        if (harga > 0) this.hargaSewaPerHari = harga;
        else System.out.println("[ERROR] Harga sewa harus lebih dari 0.");
    }

    public void setTersedia(boolean tersedia)   { this.tersedia = tersedia; }
}
