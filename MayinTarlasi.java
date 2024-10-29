import java.util.Random;
import java.util.Scanner;

public class MayinTarlasi {
    private static final int BOYUT = 5; // Matris boyutu
    private static final int MAYIN_SAYISI = 5; // Mayın sayısı
    private static final char MAYIN = '*';
    private static final char BOS = '-';
    private static final char ACIK = '0';

    private char[][] tahta;
    private boolean[][] mayinlar;
    private boolean oyunBitti;

    public MayinTarlasi() {
        tahta = new char[BOYUT][BOYUT];
        mayinlar = new boolean[BOYUT][BOYUT];
        oyunBitti = false;
        tahtaHazirla();
        mayinlariYerlestir();
    }

    private void tahtaHazirla() {
        for (int i = 0; i < BOYUT; i++) {
            for (int j = 0; j < BOYUT; j++) {
                tahta[i][j] = BOS;
            }
        }
    }

    private void mayinlariYerlestir() {
        Random random = new Random();
        int mayinYerlesmis = 0;
        while (mayinYerlesmis < MAYIN_SAYISI) {
            int satir = random.nextInt(BOYUT);
            int sutun = random.nextInt(BOYUT);
            if (!mayinlar[satir][sutun]) {
                mayinlar[satir][sutun] = true;
                mayinYerlesmis++;
            }
        }
    }

    private void tahtaYazdir() {
        for (int i = 0; i < BOYUT; i++) {
            for (int j = 0; j < BOYUT; j++) {
                if (mayinlar[i][j] && oyunBitti) {
                    System.out.print(MAYIN + " ");
                } else {
                    System.out.print(tahta[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    private boolean mayinVarMi(int satir, int sutun) {
        return mayinlar[satir][sutun];
    }

    private void hucreAc(int satir, int sutun) {
        if (tahta[satir][sutun] != BOS) return;
        if (mayinlar[satir][sutun]) {
            oyunBitti = true;
            return;
        }

        int komsuMayinSayisi = komsuMayinSayisiniBul(satir, sutun);
        tahta[satir][sutun] = (char) ('0' + komsuMayinSayisi);
    }

    private int komsuMayinSayisiniBul(int satir, int sutun) {
        int sayi = 0;
        for (int i = satir - 1; i <= satir + 1; i++) {
            for (int j = sutun - 1; j <= sutun + 1; j++) {
                if (i >= 0 && i < BOYUT && j >= 0 && j < BOYUT && mayinlar[i][j]) {
                    sayi++;
                }
            }
        }
        return sayi;
    }

    public void oyna() {
        Scanner scanner = new Scanner(System.in);
        while (!oyunBitti) {
            tahtaYazdir();
            System.out.print("Satır seçiniz (0-" + (BOYUT - 1) + "): ");
            int satir = scanner.nextInt();
            System.out.print("Sütun seçiniz (0-" + (BOYUT - 1) + "): ");
            int sutun = scanner.nextInt();

            hucreAc(satir, sutun);

            if (oyunBitti) {
                System.out.println("Mayına bastınız! Oyun bitti.");
                tahtaYazdir();
            }
        }
    }

    public static void main(String[] args) {
        MayinTarlasi oyun = new MayinTarlasi();
        oyun.oyna();
    }
}
