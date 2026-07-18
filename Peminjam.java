
public interface Peminjam {

    void pinjamBuku(Buku buku);

    // hariTerlambat = jumlah hari melewati batas maksimal peminjaman
    double hitungDenda(long hariTerlambat);
}
