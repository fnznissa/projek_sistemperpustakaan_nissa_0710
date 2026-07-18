import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Transaksi {

    private static int counter = 0; // KONSEP: Static Variable (Counter untuk ID Transaksi otomatis)

    private final String idTransaksi;
    
    // RELASI OOP: AGGREGATION
    private final Buku buku;
    private final Anggota peminjam; // Polimorfisme
    
    private final LocalDate tanggalPinjam;
    private LocalDate tanggalKembali; 
    private boolean sudahKembali;

    public Transaksi(Buku buku, Anggota peminjam) {
        counter++; // Melacak urutan ID Transaksi otomatis
        this.idTransaksi = "TRX" + counter;
        this.buku = buku;
        this.peminjam = peminjam;
        this.tanggalPinjam = LocalDate.now();
        this.sudahKembali = false;
    }

    public String getIdTransaksi() { return idTransaksi; }
    public Buku getBuku() { return buku; }
    public Anggota getPeminjam() { return peminjam; }
    public LocalDate getTanggalPinjam() { return tanggalPinjam; }
    public boolean isSudahKembali() { return sudahKembali; }

    public double kembalikanBuku() {
        this.tanggalKembali = LocalDate.now();
        this.sudahKembali = true;
        buku.tambahStok();

        long lamaPinjam = ChronoUnit.DAYS.between(tanggalPinjam, tanggalKembali);
        
        // PILAR OOP: POLYMORPHISM 
        long maksHari = peminjam.getMaksHariPinjam();
        long hariTerlambat = lamaPinjam - maksHari;

        // PILAR OOP: POLYMORPHISM 
        return peminjam.hitungDenda(hariTerlambat);
    }

    @Override
    public String toString() {
        String status = sudahKembali ? "Sudah kembali (" + tanggalKembali + ")" : "Sedang dipinjam";
        return idTransaksi + " | " + buku.getJudul() + " dipinjam oleh " + peminjam.getNama()
                + " sejak " + tanggalPinjam + " | " + status;
    }
}