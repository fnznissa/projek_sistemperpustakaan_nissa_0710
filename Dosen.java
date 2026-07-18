public class Dosen extends Anggota {

    private String nip;

    public Dosen(String idAnggota, String nama, String nip) {
        super(idAnggota, nama); //inheritance
        this.nip = nip;
    }

    public String getNip() {
        return nip;
    }

    @Override //abstraksi dari kelas Anggota
    public int getMaksHariPinjam() {
        return 30;
    }

    @Override
    public double getTarifDendaPerHari() {
        return 500.0;
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("Dosen     | " + nama + " | NIP: " + nip
                + " | Maks pinjam: " + getMaksHariPinjam() + " hari"
                + " | Denda: Rp" + (int) getTarifDendaPerHari() + "/hari");
    }

    public void mengajar(Mahasiswa m) { //asosiasi
        System.out.println(nama + " mengajar " + m.getNama());
    }

    // Format: DOSEN;id;nama;nip
    @Override
    public String toFileString() {
        return "DOSEN;" + idAnggota + ";" + nama + ";" + nip;
    }
}
