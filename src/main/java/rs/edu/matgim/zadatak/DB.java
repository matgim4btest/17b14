package rs.edu.matgim.zadatak;
//skrtskrt123
import java.sql.*;

public class DB {

    Connection connection;
    String connectionString = "jdbc:sqlite:src\\main\\java\\Banka.db";

    public void connect() throws SQLException
    {
        this.connection=DriverManager.getConnection(connectionString);
    }
    public void printFilijala() {
        try ( Connection conn = DriverManager.getConnection(connectionString);  Statement s = conn.createStatement()) {

            ResultSet rs = s.executeQuery("SELECT * FROM Filijala");
            while (rs.next()) {
                int IdFil = rs.getInt("IdFil");
                String Naziv = rs.getString("Naziv");
                String Adresa = rs.getString("Adresa");

                System.out.println(String.format("%d\t%s\t%s", IdFil, Naziv, Adresa));
            }

        } catch (SQLException ex) {
            System.out.println("Greska prilikom povezivanja na bazu");
            System.out.println(ex);
        }
    }
    public void printePositiveRacun() throws SQLException
    {
        String upit="SELECT * FROM Racun WHERE Stanje>0";
                Statement st=connection.createStatement();
                ResultSet rs=st.executeQuery(upit);
                while(rs.next())
                {
                    int a,b,c,d,e;
                    a=rs.getInt(1);
                    String s=rs.getString(2);
                    b=rs.getInt(3);
                    c=rs.getInt(4);
                    d=rs.getInt(5);
                    e=rs.getInt(6);
                    System.out.println(a+"\t"+b+"\t"+s+"\t"+c+"\t"+d+"\t"+e+"\t");
                }
    }
    boolean zadatak(int idRacFrom,int idRacTo, float c) throws SQLException
    {
        try{
        connection.setAutoCommit(false);
        String upit1="SELECT Stanje FROM Racun WHERE IdRac=?";
        PreparedStatement st=connection.prepareStatement(upit);
        st.setInt(1,idRacFrom);
        ResultSet rs=st.executeQuery(upit);
        connection.commit();
        float stanjee=rs.getInt(1);
        if(stanjee>c)
        {
            String upit2="UPDATE Racun SET Stanje=Stanje-? WHERE IdRac=?";
            PreparedStatement st1=connection.prepareStatement(upit2);
            st1.setFloat(1, c);
            st1.setInt(2, idRacFrom);
            st1.executeUpdate();
            String upit3="UPDATE Racun SET Stanje=Stanje+? WHERE IdRac=?";
            PreparedStatement st2=connection.prepareStatement(upit2);
            st1.setFloat(1, c);
            st1.setInt(2, idRacTo);
            st2.executeUpdate();
            connection.commit();
        }
        connection.setAutoCommit(true);
        }
        catch(SQLException ex)
        {
            System.out.println("Dogodila se greska");
            connection.rollback();
        }
        return true;
    }

}
