
public class Mahasiswa extends Anggota {

    private String npm;

    public Mahasiswa(String idAnggota, String nama, String npm) {
        super(idAnggota, nama); // memanggil constructor superclass Anggota
        this.npm = npm;
    }

    public String getNpm() {
        return npm;
    }

    @Override
    public int getMaksHariPinjam() {
        return 7;
    }

    @Override
    public double getTarifDendaPerHari() {
        return 1000.0;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Mahasiswa | " + nama + " | NPM: " + npm
                + " | Maks pinjam: " + getMaksHariPinjam() + " hari"
                + " | Denda: Rp" + (int) getTarifDendaPerHari() + "/hari");
    }

    // Format: MAHASISWA;id;nama;npm
    @Override
    public String toFileString() {
        return "MAHASISWA;" + idAnggota + ";" + nama + ";" + npm;
    }
}
