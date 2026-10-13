/**
 * Subclass: Truk
 * Turunan dari Kendaraan yang merepresentasikan kendaraan berat.
 * Mengimplementasikan interface Asuransi dan Perawatan.
 *
 * Konsep OOP yang diterapkan:
 * - Inheritance: extends Kendaraan
 * - Interface Implementation: implements Asuransi, Perawatan
 * - Polymorphism: override getDeskripsi() dan hitungBiayaTambahan()
 * - Encapsulation: atribut private khusus Truk
 */
public class Truk extends Kendaraan implements Asuransi, Perawatan {

    // === ATRIBUT KHUSUS TRUK ===
    private double kapasitasMuatan; // dalam ton
    private String jenisKargo;      // Box / Flatbed / Tangki
    private boolean suratIzinMuatan;
    private String catatanPerawatan;

    // === CONSTRUCTOR ===
    public Truk(String idKendaraan, String merek, String model,
                int tahunProduksi, String warna, double hargaSewaPerHari,
                double kapasitasMuatan, String jenisKargo, boolean suratIzinMuatan) {
        super(idKendaraan, merek, model, tahunProduksi, warna, hargaSewaPerHari);
        this.kapasitasMuatan  = kapasitasMuatan;
        this.jenisKargo       = jenisKargo;
        this.suratIzinMuatan  = suratIzinMuatan;
        this.catatanPerawatan = "Kalibrasi timbangan & cek rem - Layak Operasi";
    }

    // === OVERRIDE ABSTRACT METHOD (Polymorphism) ===
    @Override
    public String getDeskripsi() {
        return String.format("Truk %s kapasitas %.1f Ton, kargo %s%s",
            getMerek(), kapasitasMuatan, jenisKargo,
            suratIzinMuatan ? " (Surat Izin Lengkap)" : " (Surat Izin Belum Lengkap)");
    }

    @Override
    public double hitungBiayaTambahan(int jumlahHari) {
        // Biaya tambahan truk: asuransi (rate tinggi) + biaya surat izin muatan
        double biayaAsuransi = hitungAsuransi(jumlahHari, getHargaSewaPerHari());
        double biayaIzin     = suratIzinMuatan ? 50000.0 : 150000.0; // denda jika belum lengkap
        return biayaAsuransi + biayaIzin;
    }

    // === IMPLEMENTASI INTERFACE ASURANSI ===
    @Override
    public double hitungAsuransi(int jumlahHari, double hargaSewaPerHari) {
        // Truk punya rate asuransi lebih tinggi (8%)
        return 0.08 * hargaSewaPerHari * jumlahHari;
    }

    @Override
    public String getJenisAsuransi() {
        return "Asuransi Kendaraan Berat / Komersial";
    }

    // === IMPLEMENTASI INTERFACE PERAWATAN ===
    @Override
    public boolean periksaKondisi() {
        System.out.println("[PERAWATAN] Pemeriksaan Truk " + getModel() + " - " + catatanPerawatan);
        return suratIzinMuatan; // hanya layak jika surat izin lengkap
    }

    @Override
    public String getCatatanPerawatan() {
        return catatanPerawatan;
    }

    // === OVERRIDE TAMPILKAN INFO ===
    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.printf("  Kap. Muatan    : %.1f Ton%n", kapasitasMuatan);
        System.out.println("  Jenis Kargo    : " + jenisKargo);
        System.out.println("  Surat Izin     : " + (suratIzinMuatan ? "Lengkap" : "Belum Lengkap"));
        System.out.println("  Jenis Asuransi : " + getJenisAsuransi());
    }

    // === GETTER & SETTER ===
    public double  getKapasitasMuatan()  { return kapasitasMuatan; }
    public String  getJenisKargo()       { return jenisKargo; }
    public boolean isSuratIzinMuatan()   { return suratIzinMuatan; }
    public void    setCatatanPerawatan(String catatan) { this.catatanPerawatan = catatan; }
}
