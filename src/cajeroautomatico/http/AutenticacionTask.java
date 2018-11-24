/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cajeroautomatico.http;

import cajeroautomatico.constantes.Constantes;
import cajeroautomatico.entities.Cliente;
import cajeroautomatico.utils.JSONUtils;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Kenny
 */
public class AutenticacionTask extends BaseAsyntask{

    private final static String nomArrayJSON = "AUTENTICACION";
    private final static String path = "/api/autenticacion";
    private OnResult onResult;
    
    public interface OnResult
    {
        void onResult(int codResultado, String message , Cliente cliente);
    }
        
    public AutenticacionTask(Cliente cliente, String claveTarjeta, OnResult onResult){
//        super(nomArrayJSON, path, EHTTP.POST); 
        super(nomArrayJSON, String.format(Locale.getDefault(),
                "%s/?numeroTarjeta=%s&claveTarjeta=%s", 
                path, 
                cliente.getNumeroTarjeta(), 
                claveTarjeta), EHTTP.GET); 
        this.onResult = onResult;
    }
     
    
    public void onPostDataJSONObject(int codEstado, JSONObject jsonObject)throws JSONException
    {
        // only the childs to use this method
        JSONObject jsonCliente = jsonObject.getJSONObject(nomArrayJSON);
        Cliente cliente = new Cliente();

        int codResultado = JSONUtils.verificarInteger(jsonCliente, COLNAMES.CODRESULTADO.getString());
        String desResultado = JSONUtils.verificarString(jsonCliente, COLNAMES.DESRESULTADO.getString()); 
        if(codResultado == Constantes.RESULT_OK){ 
            jsonCliente = jsonCliente.getJSONObject(COLNAMES.CLIENTE.getString());

            cliente.setNumeroTarjeta(JSONUtils.verificarString(jsonCliente, COLNAMES.NUMEROTARJETA.getString()));
            cliente.setNombre(JSONUtils.verificarString(jsonCliente, COLNAMES.NOMBRE.getString()));
            cliente.setClaveTarjeta(JSONUtils.verificarString(jsonCliente, COLNAMES.CLAVETARJETA.getString()));
            cliente.setDireccion(JSONUtils.verificarString(jsonCliente, COLNAMES.DIRECCION.getString()));
            cliente.setTelefono(JSONUtils.verificarString(jsonCliente, COLNAMES.TELEFONO.getString()));  
            cliente.setSaldo(Float.valueOf(JSONUtils.verificarString(jsonCliente, COLNAMES.SALDO.getString()))); 
           
        } 
        this.onResult.onResult(codResultado, desResultado, cliente);
    }

    @Override
    public void onPostDataJSON(int codEstado, JSONArray jsonArray) throws JSONException {
    }
    
    public enum COLNAMES
    { 
        DESRESULTADO            ("DesResultado"),
        CODRESULTADO            ("CodResultado"), 
        CLIENTE                 ("Cliente"), 
        
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
