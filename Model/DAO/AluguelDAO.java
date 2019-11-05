package Model.DAO;

import Connection.ConnectionFactory;
import Model.Bean.Cliente;
import Model.Bean.Carro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Leandro Melo
 */
public class AluguelDAO {
    
    public void update(String idCliente, String idCarro, String dtAluguel, String dtDevolucao) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE EJL_CARRO SET ID_CLIENTE = ?, "
                    + "DT_ALUGUEL = ?, DT_DEVOLUCAO = ?, ALUGADO = ? WHERE ID = ? AND DELET = ?");
            stmt.setString(1, idCliente);
            stmt.setString(2, convertData(dtAluguel));
            stmt.setString(3, convertData(dtDevolucao));
            stmt.setString(4, "true");
            stmt.setString(5, idCarro);
            stmt.setString(6, "");

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Carro alugado com sucesso!!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alugar carro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public String convertData(String data) {
        //     01 2 34 5 6789
        //DATA 22 / 01 / 2018
        String dia = data.substring(0, 2);
        String mes = data.substring(3, 5);
        String ano = data.substring(6);
        String dataParaSQL = ano+"-"+mes+"-"+dia;
    
        return dataParaSQL;
    }
    public static String dataConvert(String data) {
        //     0123 4 56 7 89
        //DATA 2018 - 01 - 22
        String dia = data.substring(8);
        String mes = data.substring(5, 7);
        String ano = data.substring(0, 4);
        String sqlParaData = dia+"/"+mes+"/"+ano;
    
        return sqlParaData;
    }
    public static String convertKm(String km) {
        //             0 1 2 3 4 5 6 7 8 9 10 11 12
        //KILOMETRAGEM 9 9 9 . 9 9 9 , 0 0    K  M
        String milhar = km.substring(0,3);
        String dezena = km.substring(4, 7);
        String kmParaSQL = milhar + dezena;
    
        return kmParaSQL;
    }
    public static String kmConvert(String km) {
        //             0 1 2 3 4 5 6 7 8 9 10 11 12
        //KILOMETRAGEM 9 9 9 . 9 9 9 , 0 0    K  M
        String milhar = km.substring(0,3);
        String dezena = km.substring(3);
        String sqlParaKm = milhar + "." + dezena + ",00 KM";
    
        return sqlParaKm;
    }
    public static String verificarAluguel(String alugado) {
        String carro = "NÃO";
        if (alugado.equals("0")){
            carro = "NÃO";
        } else if (alugado.equals("1")){
            carro = "SIM";
        }
    
        return carro;
    }
}
