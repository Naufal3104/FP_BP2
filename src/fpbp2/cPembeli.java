/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fpbp2;

/**
 *
 * @author ahnaf
 */
public class cPembeli {
    //data
    String nama;
    String alamat;
    
    //method
    cPembeli(){
        System.out.println("Default constructor...");
    }
    cPembeli(String n, String a){
        nama=n;
        alamat=a;
        System.out.println("Nama "+nama+" telah dibuat...");
    }
    public void setNama(String n){
        nama=n;
    }
    public void setAlamat(String a){
        alamat=a;
    }
    public String getNama(){
        return nama;
    }
    public String getAlamat(){
        return alamat;
    }
    public String ToString(){
        return nama+" ["+alamat+"]";
    }
}
