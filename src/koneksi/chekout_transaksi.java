/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koneksi;

/**
 *
 * @author ahnaf
 */
public class chekout_transaksi {

    private static String username;
    private static String user_id;
    private static int id_level; // tambahkan ini

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        chekout_transaksi.username = username;
    }

    public static String getCashierId() {
        return user_id;
    }

    public static void setCashierId(String cashierId) {
        chekout_transaksi.user_id = cashierId;
    }

    public static int getUserRole() { // tambahkan ini
        return id_level;
    }

    public static void setUserRole(int userRole) { // tambahkan ini
        chekout_transaksi.id_level = userRole;
    }

    public static void clearUserSession() {
        username = null;
        user_id = null;
        id_level = 0;
    }
    private static String transactionID;

    public static void setTransactionID(String id) {
        transactionID = id;
    }

    public static String getTransactionID() {
        return transactionID;
    }
}
