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
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Kenny
 */
public class EdicionSaldoClienteTask extends BaseAsyntask{

    private final static String nomArrayJSON = "UPDATESALDOCLIENTE";
    private final static String path = "/api/update_saldo_cliente";
    private OnResult onResult;
    Cliente cliente;
    public interface OnResult
    {
        void onResult(int codResultado, String message , Cliente cliente);
    }
    
    public EdicionSaldoClienteTask(Cliente cliente, String saldoFinal, OnResult onResult){
        super(nomArrayJSON, String.format(Locale.getDefault(),
                "%s/?numeroTarjeta=%s&saldo=%s", 
                path, 
                cliente.getNumeroTarjeta(), 
                saldoFinal), EHTTP.POST); 
        this.url = super.reemplazarEspacios(url);   // 
        this.cliente = cliente;
        this.onResult = onResult;
    }
     
    
    public void onPostDataJSONObject(int codEstado, JSONObject jsonObject)throws JSONException
    {
        JSONObject jsonRegistroCliente = jsonObject.getJSONObject(nomArrayJSON);

        int codResultado = JSONUtils.verificarInteger(jsonRegistroCliente, COLNAMES.CODRESULTADO.getString());
        String desResultado = JSONUtils.verificarString(jsonRegistroCliente, COLNAMES.DESRESULTADO.getString());
         
        this.onResult.onResult(codResultado, desResultado, this.cliente);
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
