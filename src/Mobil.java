/**
 * Subclass: Mobil
 * Turunan dari Kendaraan yang merepresentasikan kendaraan roda 4.
 * Mengimplementasikan interface Asuransi dan Perawatan.
 *
 * Konsep OOP yang diterapkan:
 * - Inheritance: extends Kendaraan
 * - Interface Implementation: implements Asuransi, Perawatan
 * - Polymorphism: override getDeskripsi() dan hitungBiayaTambahan()
 * - Encapsulation: atribut private khusus Mobil
 */
public class Mobil extends Kendaraan implements Asuransi, Perawatan {

    // === ATRIBUT KHUSUS MOBIL ===
    private int     jumlahPenumpang;
    private String  transmisi;       // Manual / Otomatis
    private boolean acTersedia;
    private String  catatanPerawatan;

    // === CONSTRUCTOR ===
    public Mobil(String idKendaraan, String merek, String model,
                 int tahunProduksi, String warna, double hargaSewaPerHari,
                 int jumlahPenumpang, String transmisi, boolean acTersedia) {
        super(idKendaraan, merek, model, tahunProduksi, warna, hargaSewaPerHari);
        this.jumlahPenumpang  = jumlahPenumpang;
        this.transmisi        = transmisi;
        this.acTersedia       = acTersedia;
        this.catatanPerawatan = "Servis rutin 10.000 km - Kondisi Prima";
    }

    // === OVERRIDE ABSTRACT METHOD (Polymorphism) ===
    @Override
    public String getDeskripsi() {
        return String.format("Mobil %s Transmisi %s, %d kursi%s",
            getMerek(), transmisi, jumlahPenumpang,
            acTersedia ? " + AC" : "");
    }

    @Override
    public double hitungBiayaTambahan(int jumlahHari) {
        // Biaya tambahan: asuransi + biaya sopir opsional (tidak termasuk di sini)
        return hitungAsuransi(jumlahHari, getHargaSewaPerHari());
    }

    // === IMPLEMENTASI INTERFACE ASURANSI ===
    @Override
    public double hitungAsuransi(int jumlahHari, double hargaSewaPerHari) {
        return RATE_ASURANSI_DASAR * hargaSewaPerHari * jumlahHari;
    }

    @Override
    public String getJenisAsuransi() {
        return "Asuransi Kendaraan Bermotor Roda 4";
    }

    // === IMPLEMENTASI INTERFACE PERAWATAN ===
    @Override
    public boolean periksaKondisi() {
        // Simulasi: mobil selalu layak jalan (bisa dikembangkan dengan logika nyata)
        System.out.println("[PERAWATAN] Pemeriksaan Mobil " + getModel() + " - " + catatanPerawatan);
        return true;
    }

    @Override
    public String getCatatanPerawatan() {
        return catatanPerawatan;
    }

    // === OVERRIDE TAMPILKAN INFO ===
    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("  Penumpang      : " + jumlahPenumpang + " orang");
        System.out.println("  Transmisi      : " + transmisi);
        System.out.println("  AC             : " + (acTersedia ? "Ada" : "Tidak Ada"));
        System.out.println("  Jenis Asuransi : " + getJenisAsuransi());
    }

    // === GETTER & SETTER ===
    public int     getJumlahPenumpang() { return jumlahPenumpang; }
    public String  getTransmisi()       { return transmisi; }
    public boolean isAcTersedia()       { return acTersedia; }
    public void    setCatatanPerawatan(String catatan) { this.catatanPerawatan = catatan; }
}
