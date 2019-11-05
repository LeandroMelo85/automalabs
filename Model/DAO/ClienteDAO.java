package Model.DAO;

import Connection.ConnectionFactory;
import Model.Bean.Cliente;
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
public class ClienteDAO {
    
    public void create(Cliente cliente) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO EJL_CLIENTE (NOME, ENDERECO, "
                    + "TELEFONE, CELULAR, DT_NASC, RG, CPF, EMAIL, DELET) "
                    + "VALUES(?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, convertNumero(cliente.getTelefone()));
            stmt.setString(4, convertCelular(cliente.getCelular()));
            stmt.setString(5, convertData(cliente.getDtNasc()));
            stmt.setString(6, cliente.getRg());
            stmt.setString(7, cliente.getCpf());
            stmt.setString(8, cliente.getEmail());
            stmt.setString(9, "");

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Cliente> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM EJL_CLIENTE WHERE DELET = ?");
            stmt.setString(1, "");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Cliente cli = new Cliente();
                cli.setID(rs.getInt("ID"));
                cli.setNome(rs.getString("NOME"));
                cli.setEndereco(rs.getString("ENDERECO"));
                cli.setTelefone(numeroConvert(rs.getString("TELEFONE")));
                cli.setCelular(celularConvert(rs.getString("CELULAR")));
                cli.setDtNasc(dataConvert(rs.getString("DT_NASC")));
                cli.setRg(rs.getString("RG"));
                cli.setCpf(rs.getString("CPF"));
                cli.setEmail(rs.getString("EMAIL"));
                
                clientes.add(cli);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientes;
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
