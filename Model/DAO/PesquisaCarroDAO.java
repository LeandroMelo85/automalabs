package Model.DAO;

import Connection.ConnectionFactory;
import Model.Bean.Carro;
import static Model.DAO.CarroDAO.dataConvert;
import static Model.DAO.CarroDAO.kmConvert;
import static Model.DAO.CarroDAO.verificarAluguel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leandro Melo
 */
public class PesquisaCarroDAO {
    
    public Carro readCar(int ID) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Carro carro = new Carro();

        try {
            stmt = con.prepareStatement("SELECT * FROM EJL_CARRO WHERE ID = ? AND DELET = ?");
            stmt.setInt(1, ID);
            stmt.setString(2, "");
            rs = stmt.executeQuery();

            while (rs.next()) {

                carro.setId(rs.getInt("ID"));
                carro.setIdCliente(rs.getInt("ID_CLIENTE"));
                carro.setFabricante(rs.getString("FABRICANTE"));
                carro.setModelo(rs.getString("MODELO"));
                carro.setCor(rs.getString("COR"));
                carro.setMotor(rs.getString("MOTOR"));
                carro.setTpCombustivel(rs.getString("TP_COMB"));
                carro.setDtCadastro(dataConvert(rs.getString("DT_CAD")));
                carro.setKmAtual(kmConvert(rs.getString("KM_ATUAL")));
                carro.setAlugado(verificarAluguel(rs.getString("ALUGADO")));
                carro.setDtAluguel(dataConvert(rs.getString("DT_ALUGUEL")));
                carro.setDtDevolucao(dataConvert(rs.getString("DT_DEVOLUCAO")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return carro;
    }
    public List<Carro> carrosAlugados() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Carro> consulta = new ArrayList<>();

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
                
                consulta.add(car);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return consulta;
    }
    
    public List<Carro> carrosDisponiveis() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Carro> consulta = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM EJL_CARRO WHERE DELET = ? AND ALUGADO = ?");
            stmt.setString(1, "");
            stmt.setString(2, "false");
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
                
                consulta.add(car);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return consulta;
    }
public List<Carro> searchForFabricante(String fabric) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Carro> consulta = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM EJL_CARRO WHERE FABRICANTE LIKE ?  AND DELET = ?");
            stmt.setString(1, "%"+fabric+"%");
            stmt.setString(2, "");
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
                
                consulta.add(car);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return consulta;
    }
    public List<Carro> searchForModelo(String model) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Carro> consulta = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM EJL_CARRO WHERE MODELO LIKE ?  AND DELET = ?");
            stmt.setString(1, "%"+model+"%");
            stmt.setString(2, "");
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
                
                consulta.add(car);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return consulta;
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
