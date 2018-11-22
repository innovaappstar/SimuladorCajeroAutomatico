/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cajeroautomatico;

import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author Kenny
 */
public class CajeroAutomatico {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double Saldo;
        String DNIregsitrado,DNIingresado,opcion;
         Saldo=Double.parseDouble(JOptionPane.showInputDialog("Ingresa el saldo incial"));
         DNIregsitrado="40414243";
         DNIingresado=JOptionPane.showInputDialog(null, "Ingresar DNI");
         
         if (DNIregsitrado.equals(DNIingresado))
         {
             // simple plantilla de cadena para concatenar un texto con distintas variables, además de ser menos líneas de código..
          String mensajeUsuario = String.format(Locale.getDefault(), "DNI Correcto\nEl saldo actual es de\n%f\nIndicar operación a realizar\n" +
                  "1. Retiro \n2. Deposito \n3. Consulta \n4. Pago de servicios \n5. Transferencias \n", Saldo);
          
          opcion=JOptionPane.showInputDialog(null, mensajeUsuario);
         
         switch(opcion)    
            {
        case "1":
            JOptionPane.showMessageDialog(null, "Escogistes Retiro");
            break;
        case "2":
            JOptionPane.showMessageDialog(null, "Escogistes Depsotio");
            break;
        case "3":
            JOptionPane.showMessageDialog(null, "Consulta");
            break;
        case "4":
            JOptionPane.showMessageDialog(null, "Escogistes Pago de servicios");
            break;
        case "5":
            JOptionPane.showMessageDialog(null, "Escogistes Transferencias");
            break;
        default:
            JOptionPane.showMessageDialog(null, "Opcion no valida");
            break;
            }         
         }
         else
            JOptionPane.showMessageDialog(null, "DNI Incorrecto");
         
                
    }
    
}
