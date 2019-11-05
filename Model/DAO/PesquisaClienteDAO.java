package Model.DAO;

import Connection.ConnectionFactory;
import Model.Bean.Carro;
import Model.Bean.Cliente;
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
public class PesquisaClienteDAO {
    
    public Cliente readCli(int ID) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = new Cliente();

        try {
            stmt = con.prepareStatement("SELECT * FROM EJL_CLIENTE WHERE ID = ? AND DELET = ?");
            stmt.setInt(1, ID);
            stmt.setString(2, "");
            rs = stmt.executeQuery();

            while (rs.next()) {

                cliente.setID(rs.getInt("ID"));
                cliente.setNome(rs.getString("NOME"));
                cliente.setEndereco(rs.getString("ENDERECO"));
                cliente.setTelefone(numeroConvert(rs.getString("TELEFONE")));
                cliente.setCelular(celularConvert(rs.getString("CELULAR")));
                cliente.setDtNasc(dataConvert(rs.getString("DT_NASC")));
                cliente.setRg(rs.getString("RG"));
                cliente.setCpf(rs.getString("CPF"));
                cliente.setEmail(rs.getString("EMAIL"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PesquisaClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return cliente;
    }
/*    public List<Carro> carrosAlugados() {
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
    }*/
    
    public String convertData(String data) {
        //     01 2 34 5 6789
        //DATA 22 / 01 / 2018
        String dia = data.substring(0, 2);
        String mes = data.substring(3, 5);
        String ano = data.substring(6);
        String dataParaSQL = ano+"-"+mes+"-"+dia;
    
        return dataParaSQL;
    }
    public String convertNumero(String numero) {
        //       0123 4 5678 9 10
        //NUMERO (81) 3202 - 9912
        String ddd = numero.substring(1,3);
        String prefx = numero.substring(5, 9);
        String sufx = numero.substring(10);
        String numeroParaSQL = ddd + prefx + sufx;
    
        return numeroParaSQL;
    }
    public String convertCelular(String celular) {
        //       0123  4 5678 9 10 11 12
        //NUMERO (81)  9 8240 -   4044
        String ddd = celular.substring(1,3);
        String digt = celular.substring(5, 6);
        String prefx = celular.substring(7, 11);
        String sufx = celular.substring(12);
        String celularParaSQL = ddd + digt + prefx + sufx;
    
        return celularParaSQL;
    }
    public String dataConvert(String data) {
        //        0123 4 56 7 89
        //DATA 2018 - 01 - 22
        String dia = data.substring(8);
        String mes = data.substring(5, 7);
        String ano = data.substring(0, 4);
        String sqlParaData = dia+"/"+mes+"/"+ano;
    
        return sqlParaData;
    }
    public String numeroConvert(String numero) {
        //                  01   2345   6789
        //NUMERO ( 81) 3202 - 9912
        String ddd = numero.substring(0,2);
        String prefx = numero.substring(2, 6);
        String sufx = numero.substring(6);
        String sqlParaNumero = "(" + ddd + ") "+ prefx + "-" + sufx;
    
        return sqlParaNumero;
    }
    public String celularConvert(String celular) {
        //                  01  2  3456   78910
        //NUMERO ( 81) 9 8240 - 4044
        String ddd = celular.substring(0,2);
        String digt = celular.substring(2, 3);
        String prefx = celular.substring(3, 7);
        String sufx = celular.substring(7);
        String sqlParaCelular = "(" + ddd + ") " + digt + " " + prefx + "-" + sufx;
    
        return sqlParaCelular;
    }
}
