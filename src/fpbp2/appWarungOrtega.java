/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fpbp2;
import java.util.Scanner;
/**
 *
 * @author ahnaf
 */
public class appWarungOrtega {
    public static void main(String[] args) {
        Scanner s=new Scanner (System.in);
        int pilih=0;
        cMakanan mkn = new cMakanan("Rawon", 15000, 12);
        cPembeli pb = new cPembeli("Anop", "Rungkut");
        
        //menu
        do{
        System.out.println("---WARUNG ORTEGA---");
        System.out.println("1.Menu Makanan");
        System.out.println("2.Data Pembeli");
        System.out.println("3.Transaksi");
        System.out.println("4.Exit");
        System.out.print("Pilih = ");
        pilih=s.nextInt();
        switch (pilih){
            case 1:
                int pilih2=0;
                do{
                System.out.println("1.Lihat Menu");
                System.out.println("2.Tambah Menu");
                System.out.println("3.Edit Menu");
                System.out.println("4.Hapus Menu");
                System.out.println("5.Kembali");
                System.out.print("Pilih = ");
                pilih2=s.nextInt();
                switch(pilih2){
                    case 1:
                        if (mkn==null){
                            System.out.println("Menu Kosong...");
                        } else {
                        String t = mkn.ToString();
                        System.out.println("Menu : " + t);
                        System.out.println("----------");
                        break;
                        }
                    case 2:
                        s = new Scanner(System.in);
                        System.out.print("Nama Menu : ");
                        String nm = s.nextLine();
                        System.out.print("Harga :");
                        int hm = s.nextInt();
                        System.out.print("Stok : ");
                        int st = s.nextInt();
                        mkn = new cMakanan(nm, hm, st);
                        break;
                    case 3:
                        System.out.print("Harga Baru : ");
                        int hb = s.nextInt();
                        System.out.print("Stok Baru : ");
                        st = s.nextInt();
                        mkn.setHarga(hb);
                        mkn.setStok(st);
                        break;
                    case 4:
                        s=new Scanner (System.in);
                        System.out.print("Menu yang ingin dihapus : ");
                        String mh = s.nextLine();
                        if (mh.equalsIgnoreCase(mkn.getNama())){
                            mkn=null;
                            System.out.println("Hapus Berhasil...");
                        } else System.out.println("Error...");
                        break;
                }
                }while(pilih2!=5);
            case 2:
                pilih2=0;
                    do {
                    System.out.println("1.Lihat Data");
                    System.out.println("2.Tambah Data");
                    System.out.println("3.Edit Data");
                    System.out.println("4.Hapus Data");
                    System.out.print("Pilih = ");
                    pilih2 = s.nextInt();
                    switch(pilih2){
                        case 1:
                            if (pb==null){
                                System.out.println("Data Tidak Ada..");
                            }
                            String t = pb.ToString();
                            System.out.println("Data "+t);
                            break;
                        case 2:
                            System.out.print("Nama : ");
                            String n = s.next();
                            System.out.print("Alamat : ");
                            String a = s.next();
                            pb.setNama(n);
                            pb.setAlamat(a);
                            break;
                        case 3:
                            System.out.print("Nama Baru : ");
                            String np = s.next();
                            System.out.print("Alamat Baru :");
                            String ab = s.next();
                            pb = new cPembeli(np, ab);
                            break;
                        case 4:
                            System.out.print("Masukkan nama yang ingin dihapus : ");
                            String hp = s.next();
                            if (hp.equalsIgnoreCase(pb.getNama())){
                                pb=null;
                                System.out.println("Hapus Berhasil");
                            } else System.out.println("Error...");
                    }
                    }while(pilih2!=4);
                break;
        }
       }while(pilih!=4);
    }
}
