package rs.edu.matgim.zadatak;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Program {

    public static void main(String[] args) {

        DB _db = new DB();
        _db.printFilijala();
        try {
            _db.printePositiveRacun();
        } catch (SQLException ex) {
            System.out.println("Greska");
        }
        
    }
}
