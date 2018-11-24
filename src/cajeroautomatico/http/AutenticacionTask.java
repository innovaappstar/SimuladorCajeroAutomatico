/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cajeroautomatico.http;

import cajeroautomatico.utils.JSONUtils;
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
    
    public AutenticacionTask(){
        super(nomArrayJSON, path, EHTTP.POST);
    }
     
    
    public void onPostDataJSONObject(int codEstado, JSONObject jsonObject)throws JSONException
    {
        // only the childs to use this method
        JSONObject objConfigUsuario = jsonObject.getJSONObject(nomArrayJSON);

        int codResultado = JSONUtils.verificarInteger(objConfigUsuario, COLNAMES.CODRESULTADO.getString());
        String desResultado = JSONUtils.verificarString(objConfigUsuario, COLNAMES.DESRESULTADO.getString());
        int idSQLite1 = JSONUtils.verificarInteger(objConfigUsuario, COLNAMES.IDSQLITE.getString());
        
        System.out.println("---------------");
        System.out.println(desResultado);

    }

    @Override
    public void onPostDataJSON(int codEstado, JSONArray jsonArray) throws JSONException {
    }
    
    public enum COLNAMES
    {
        IDSQLITE                ("_idSQLite"),
        DESRESULTADO            ("DesResultado"),
        CODRESULTADO            ("CodResultado"),
        NOMBREUSUARIO           ("nombre"),
        RESULT                  ("result");

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
