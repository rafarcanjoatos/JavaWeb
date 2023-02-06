package Company;

import DatabaseConector.DatabaseConector;
import java.sql.Statement;
import java.sql.ResultSet;

public class Company {

    // Propriedades
    int[] idCompany;
    String[] companyName = new String[3];

    // Construtor
    public Company() {
    }

    // Método Select
    public String[] getCompanyName() {

        java.sql.Connection c;
        new DatabaseConector();
        c = DatabaseConector.getDatabaseConector();

        try {
            String comando = "SELECT * from company";
            Statement st = c.createStatement();
            int i = 0;

            ResultSet result;
            result = st.executeQuery(comando);

            while (result.next()) {
                companyName[i] = result.getString("company_name");
                i += 1;
            }

        } catch (Exception e) {
            System.out.println("Deu tudo errado");
        }

        return companyName;
    }

    // Método Exibir Nomes
    public void showCompanyNames() {

        Company company = new Company();
        String companyName[] = company.getCompanyName();

        for (String names : companyName) {
            System.out.println("Empresa " + names);
        }
    }
}
