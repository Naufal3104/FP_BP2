/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fpbp2;

/**
 *
 * @author ahnaf
 */
public class cMakanan {
    //data
    private String nama;
    private int harga;
    private int stok;
    
    //method
    cMakanan(){
            System.out.println("Default constructor...");
    }
    cMakanan(String n, int h, int s){
        nama=n;
        harga=h;
        stok=s;
        System.out.println("Menu "+nama+" telah dibuat...");
    }
    public void setNama(String n){
        nama=n;
    }
    public void setHarga(int h){
        harga=h;
    }
    public void setStok(int s){
        stok=s;
    }
    public String getNama(){
        return nama;
    }
    public int getHarga(){
        return harga;
    }
    public int getStok(){
        return stok;
    }
    public String ToString(){
        return nama+" ("+harga+") "+stok;
    }
}
    

