import java.time.LocalDate;

public class KartuAnggota {
    // PILAR OOP: ENCAPSULATION (Menggunakan private final agar datanya tidak bisa diubah setelah dibuat)
    private final String nomorKartu;
    private final LocalDate tanggalDaftar;

    public KartuAnggota(String nomorKartu) {
        this.nomorKartu = nomorKartu;
        this.tanggalDaftar = LocalDate.now(); // Otomatis mencatat waktu saat ini
    }

    public String getNomorKartu() { return nomorKartu; }
    public LocalDate getTanggalDaftar() { return tanggalDaftar; }

    //polimorfisme
    @Override
    public String toString() {
        return "Kartu No." + nomorKartu + " (terdaftar " + tanggalDaftar + ")";
    }
}
