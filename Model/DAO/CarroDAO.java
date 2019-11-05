package Model.DAO;

import Connection.ConnectionFactory;
import Model.Bean.Carro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Leandro Melo
 */
public class CarroDAO {
    
    public void create(Carro carro) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO EJL_CARRO (FABRICANTE, MODELO, "
                    + "COR, MOTOR, TP_COMB, DT_CAD, KM_ATUAL, ALUGADO, DELET, ID_CLIENTE, "
                    + "DT_ALUGUEL, DT_DEVOLUCAO) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, carro.getFabricante());
            stmt.setString(2, carro.getModelo());
            stmt.setString(3, carro.getCor());
            stmt.setString(4, carro.getMotor());
            stmt.setString(5, carro.getTpCombustivel());
            stmt.setString(6, convertData(carro.getDtCadastro()));
            stmt.setString(7, convertKm(carro.getKmAtual()));
            stmt.setString(8, "false");
            stmt.setString(9, "");
            stmt.setString(10, "0");
            stmt.setString(11, "2000-01-01");
            stmt.setString(12, "2000-01-01");

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Carro cadastrado com sucesso!!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Carro> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Carro> carro = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM EJL_CARRO WHERE DELET = ?");
            stmt.setString(1, "");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Carro car = new Carro();
                car.setId(rs.getInt("ID"));
                car.setIdCliente(rs.getInt("ID_CLIENTE"));
                car.setFabricante(rs.getString("FABRICANTE"));
                car.setModelo(rs.getString("MODELO"));
                car.setCor(rs.getString("COR"));
                car.setMotor(rs.getString("MOTOR"));
                car.setTpCombustivel(rs.getString("TP_COMB"));
                car.setDtCadastro(dataConvert(rs.getString("DT_CAD")));
                car.setKmAtual(kmConvert(rs.getString("KM_ATUAL")));
                car.setAlugado(verificarAluguel(rs.getString("ALUGADO")));
//                System.out.println(rs.getString("ALUGADO"));
//                System.out.println(car.getAlugado());
                car.setDtAluguel(dataConvert(rs.getString("DT_ALUGUEL")));
                car.setDtDevolucao(dataConvert(rs.getString("DT_DEVOLUCAO")));
                
                carro.add(car);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return carro;
    }
    
/*    public static List<Carro> readAlugados() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Carro> carro = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM EJL_CARRO WHERE DELET = ? AND ALUGADO = ?");
            stmt.setString(1, "");
            stmt.setString(2, "true");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Carro car = new Carro();
                car.setId(rs.getInt("ID"));
                car.setIdCliente(rs.getInt("ID_CLIENTE"));
                car.setFabricante(rs.getString("FABRICANTE"));
                car.setModelo(rs.getString("MODELO"));
                car.setCor(rs.getString("COR"));
                car.setMotor(rs.getString("MOTOR"));
                car.setTpCombustivel(rs.getString("TP_COMB"));
                car.setDtCadastro(dataConvert(rs.getString("DT_CAD")));
                car.setKmAtual(kmConvert(rs.getString("KM_ATUAL")));
                car.setAlugado(verificarAluguel(rs.getString("ALUGADO")));
                car.setDtAluguel(dataConvert(rs.getString("DT_ALUGUEL")));
                car.setDtDevolucao(dataConvert(rs.getString("DT_DEVOLUCAO")));
                
                carro.add(car);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return carro;
    }
*/   
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
