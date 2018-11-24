/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cajeroautomatico.http;

import cajeroautomatico.constantes.Constantes;
import cajeroautomatico.entities.Cliente;
import cajeroautomatico.utils.JSONUtils;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Kenny
 */
public class ReporteClienteTask extends BaseAsyntask{

    private final static String nomArrayJSON = "REPORTECLIENTES";
    private final static String path = "/api/reporte_clientes";
    private OnResult onResult;
    
    public interface OnResult
    {
        void onResult(int codResultado, String message , ArrayList<Cliente> alCliente);
    }
    
    public ReporteClienteTask(OnResult onResult){
        super(nomArrayJSON, path, EHTTP.GET);
        this.onResult = onResult;
    }
     
    
    public void onPostDataJSONObject(int codEstado, JSONObject jsonObject)throws JSONException
    {
        // only the childs to use this method
        ArrayList<Cliente> alCliente = new ArrayList<Cliente>();
        JSONObject jsonReporteClientes = jsonObject.getJSONObject(nomArrayJSON);

        int codResultado = JSONUtils.verificarInteger(jsonReporteClientes, COLNAMES.CODRESULTADO.getString());
        String desResultado = JSONUtils.verificarString(jsonReporteClientes, COLNAMES.DESRESULTADO.getString());
        if(codResultado == Constantes.RESULT_ERROR){
            // callback
            this.onResult.onResult(codResultado, desResultado, alCliente);
            return;
        }
        JSONArray listJsonClientes = jsonReporteClientes.getJSONArray(COLNAMES.LISTACLIENTES.getString());
        for (int i = 0; i < listJsonClientes.length(); i++) {
            JSONObject jsonCliente = listJsonClientes.getJSONObject(i);
            Cliente cliente = new Cliente();
            cliente.setNumeroTarjeta(JSONUtils.verificarString(jsonCliente, COLNAMES.NUMEROTARJETA.getString()));
            cliente.setNombre(JSONUtils.verificarString(jsonCliente, COLNAMES.NOMBRE.getString()));
            cliente.setClaveTarjeta(JSONUtils.verificarString(jsonCliente, COLNAMES.CLAVETARJETA.getString()));
            cliente.setDireccion(JSONUtils.verificarString(jsonCliente, COLNAMES.DIRECCION.getString()));
            cliente.setTelefono(JSONUtils.verificarString(jsonCliente, COLNAMES.TELEFONO.getString())); 
            cliente.setSaldo(Float.valueOf(JSONUtils.verificarString(jsonCliente, COLNAMES.SALDO.getString()))); 
         
            alCliente.add(cliente);
        }
          
        this.onResult.onResult(codResultado, desResultado, alCliente);
    }

    @Override
    public void onPostDataJSON(int codEstado, JSONArray jsonArray) throws JSONException {
    }
    
    public enum COLNAMES
    {
        DESRESULTADO            ("DesResultado"),
        CODRESULTADO            ("CodResultado"),
        LISTACLIENTES           ("ListaClientes"),

        NUMEROTARJETA           ("numeroTarjeta"),
        NOMBRE                  ("nombre"),
        CLAVETARJETA            ("claveTarjeta"),
        TELEFONO                ("telefono"),
        SALDO                   ("saldo"),
        DIRECCION               ("direccion");

        String s;
        COLNAMES(String s)
        {
            this.s = s;
        }

        public String getString()
        {
            return s;
        }
    }
    
}
