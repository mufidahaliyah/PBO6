package PBO6;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Path folderKerja = Paths.get("").toAbsolutePath();
        Path fileSumber = folderKerja.resolve("employees.txt");

        // Jika tidak ada di folder kerja, coba cari di folder PBO6.
        if (Files.notExists(fileSumber)) {
            Path fileDalamPBO6 = folderKerja
                    .resolve("PBO6")
                    .resolve("employees.txt");

            if (Files.exists(fileDalamPBO6)) {
                fileSumber = fileDalamPBO6;
            }
        }

        if (Files.notExists(fileSumber)) {
            System.out.println("File employees.txt tidak ditemukan.");
            System.out.println("Letakkan file di salah satu lokasi berikut:");
            System.out.println(folderKerja.resolve("employees.txt"));
            System.out.println(folderKerja.resolve("PBO6").resolve("employees.txt"));
            return;
        }

        Path fileSalinan = fileSumber.resolveSibling("copy_employees.txt");

        try {
            List<String> daftarKaryawan = new ArrayList<>(
                    Files.readAllLines(fileSumber, StandardCharsets.UTF_8)
            );

            System.out.println("========================================");
            System.out.println("          DAFTAR DATA KARYAWAN");
            System.out.println("========================================");

            if (daftarKaryawan.isEmpty()) {
                System.out.println("File ditemukan, tetapi belum berisi data.");
            } else {
                for (int i = 0; i < daftarKaryawan.size(); i++) {
                    System.out.printf("%2d. %s%n", i + 1, daftarKaryawan.get(i));
                }
            }

            Files.write(fileSalinan, daftarKaryawan, StandardCharsets.UTF_8);

            System.out.println("----------------------------------------");
            System.out.println("Jumlah data : " + daftarKaryawan.size());
            System.out.println("File sumber : " + fileSumber.toAbsolutePath());
            System.out.println("File salinan: " + fileSalinan.toAbsolutePath());
            System.out.println("Penyalinan berhasil.");

        } catch (IOException e) {
            System.out.println("Gagal membaca atau menyalin file: " + e.getMessage());
        }
    }
}