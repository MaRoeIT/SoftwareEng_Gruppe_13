package no.hiof.g13;

import no.hiof.g13.models.*;
import no.hiof.g13.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Bruker> brukere = new ArrayList<>();

        try {
            Connection connection = mysql.getConnection();

            Statement statement = connection.createStatement();

            String selectQuery = "select * from gruppe13.bruker";

            ResultSet rs = statement.executeQuery(selectQuery);

            while (rs.next()) {
                Bruker bruker = new Bruker();
                bruker.setBruker_id(rs.getInt("bruker_id"));
                bruker.setFornavn(rs.getString("fornavn"));
                bruker.setEtternavn(rs.getString("etternavn"));
                bruker.setStatus_id(rs.getInt("status_id(FK)"));
                bruker.setKontakt_id(rs.getInt("kontakt_id(FK)"));

                brukere.add(bruker);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Bruker bruker : brukere) {
            System.out.println("Bruker id: " +bruker.getBruker_id()+ " Navn: " +bruker.getFornavn());
        }

    }
}
