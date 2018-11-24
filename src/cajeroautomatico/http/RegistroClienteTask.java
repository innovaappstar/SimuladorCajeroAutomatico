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
public class RegistroClienteTask extends BaseAsyntask{

    private final static String nomArrayJSON = "REGISTROCLIENTE";
    private final static String path = "/api/registro_cliente";
    private OnResult onResult;
    Cliente cliente;
//    // http://192.168.2.65:2029/api/tubus/busqueda_ruta/?pOrigen=-12.006804*-77.052771&pDestino=-11.974963*-77.086452&maxRango=2
// http://192.168.1.104:2030/api/registro_cliente/?numeroTarjeta=33676376347643763476347727622&claveTarjeta=1223&nombre=Janet Cardenas Velarde&direccion=miami&telefono=223344444
    public interface OnResult
    {
        void onResult(int codResultado, String message , Cliente cliente);
    }
    
    public RegistroClienteTask(Cliente cliente, OnResult onResult){
        super(nomArrayJSON, String.format(Locale.getDefault(),
                "%s/?numeroTarjeta=%s&claveTarjeta=%s&nombre=%s&direccion=%s&telefono=%s", 
                path, 
                cliente.getNumeroTarjeta(), 
                cliente.getClaveTarjeta(), 
                cliente.getNombre(),
                cliente.getDireccion(),
                cliente.getTelefono()), EHTTP.POST); 
//        super(nomArrayJSON,  (path + "/?numeroTarjeta=" + cliente.getNumeroTarjeta() + "&claveTarjeta=" + cliente.getClaveTarjeta() + "&nombre=" + 
//                cliente.getNombre() + "&direccion=" + cliente.getDireccion() + "&telefono=" + cliente.getTelefono()), EHTTP.POST);
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
