import java.time.LocalDate;

public class KartuAnggota {

    private final String nomorKartu; //enkapsulasi
    private final LocalDate tanggalDaftar;

    public KartuAnggota(String nomorKartu) {
        this.nomorKartu = nomorKartu;
        this.tanggalDaftar = LocalDate.now();
    }

    public String getNomorKartu() {
        return nomorKartu;
    }

    public LocalDate getTanggalDaftar() {
        return tanggalDaftar;
    }

    @Override //abstraksi
    public String toString() {
        return "Kartu No." + nomorKartu + " (terdaftar " + tanggalDaftar + ")";
    }
}
