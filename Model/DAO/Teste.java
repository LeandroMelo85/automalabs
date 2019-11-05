/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;
import Model.DAO.CarroDAO;

/**
 *
 * @author Leandro Melo
 */
public class Teste {
    
    public static String KM = "935.436,00 KM";
    public static String recebido;

   
    public static void main(String[] args) {
        
        recebido = CarroDAO.convertKm(KM);
        
        System.out.println("Valor recebido:" + recebido);
        
        
    }
    
}
