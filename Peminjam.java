// PILAR OOP: ABSTRACTION (Abstraksi Penuh lewat Interface)
// KONSEP: Interface (Kontrak kelakuan yang wajib dimiliki objek peminjam)
// =========================================================================
public interface Peminjam {

    void pinjamBuku(Buku buku); // Abstract method (implisit)

    // hariTerlambat = jumlah hari melewati batas maksimal peminjaman
    double hitungDenda(long hariTerlambat); // Abstract method
}
