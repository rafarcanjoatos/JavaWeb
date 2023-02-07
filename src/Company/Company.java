package Company;

import DatabaseConector.DatabaseConector;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.lang.Exception;
import java.util.Scanner;

public class Company {

    /////// PROPERTIES
    int[] companyId = new int[10];
    String[] companyName = new String[10];
    int[] companyHasDoctors = new int[10];

    /////// CONSTRUCTOR
    public Company() {
        selectCompany();
    }

    /////// GET
    public String[] getCompanyName() {
        return companyName;
    }

    public int[] getCompanyId() {
        return companyId;
    }

    public int[] getCompanyHasDoctors() {
        return companyHasDoctors;
    }

    /////// SET
    public void setCompanyName(String[] companyName) {
        this.companyName = companyName;
    }

    public void setCompanyId(int[] companyId) {
        this.companyId = companyId;
    }

    public void setCompanyHasDoctors(int[] companyHasDoctors) {
        this.companyHasDoctors = companyHasDoctors;
    }

    /////// SHOW
    public void showCompany() {

        String[] companyNames = getCompanyName();
        int[] companyId = getCompanyId();
        int[] companyHasDoctors = getCompanyHasDoctors();
        int i = 0;

        for (String names : companyNames) {
            if (names != null) {
                System.out.println("\nId " + companyId[i]);
                System.out.println("Empresa " + names);
                System.out.println("Possui Doutores " + companyHasDoctors[i] + "\n");
                i += 1;
            }
        }
    }

    /////// CONECT
    public static java.sql.Connection conectMysql() {
        java.sql.Connection con;
        new DatabaseConector();
        con = DatabaseConector.getDatabaseConector();
        return con;
    }

    /////// SELECT
    public void selectCompany() {

        java.sql.Connection connect;
        connect = conectMysql();

        try {
            String comando = "SELECT * from company";
            Statement st = connect.createStatement();
            int i = 0;

            ResultSet result;
            result = st.executeQuery(comando);

            while (result.next()) {
                companyId[i] = result.getInt("id_company");
                companyName[i] = result.getString("company_name");
                companyHasDoctors[i] = result.getInt("has_doctors");
                i += 1;
            }

            setCompanyId(companyId);
            setCompanyName(companyName);
            setCompanyHasDoctors(companyHasDoctors);

        } catch (Exception e) {
            System.out.println("Erro ao consultar company");
        }
    }

    //////// INSERT
    public String insertCompany() {

        String alert = "Empresa Inserida com sucesso";
        Scanner input = new Scanner(System.in);
        java.sql.Connection connect;
        connect = conectMysql();

        System.out.println("\n Digite o nome da nova empresa: ");
        String companyName = input.next();

        System.out.println("Essa empresa possui doutores: ");
        int companyHasDoctors = input.nextInt();

        try {
            PreparedStatement st = connect
                    .prepareStatement("insert into `company` (`company_name`, `has_doctors`) VALUES (?, ?)");

            st.setString(1, companyName);
            st.setInt(2, companyHasDoctors);

            st.executeUpdate();
            connect.close();

        } catch (Exception e) {
            System.out.println("Erro ao inserir " + companyName + e);
        }

        selectCompany();
        return alert;
    }

    //////// DELETE
    public String deleteCompany() {

        String alert = "Empresa Excluída com sucesso";
        Scanner input = new Scanner(System.in);
        java.sql.Connection connect;
        connect = conectMysql();

        System.out.println("\n Digite o nome da empresa que quer excluir: ");
        String companyName = input.next();

        try {
            PreparedStatement st = connect
                    .prepareStatement("DELETE FROM `company` WHERE `company_name` = ?");

            st.setString(1, companyName);

            st.executeUpdate();
            connect.close();

        } catch (Exception e) {
            System.out.println("Erro ao deletar " + companyName + e);
        }

        selectCompany();
        return alert;
    }
}
