/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cajeroautomatico.utils;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Kenny
 */
public class JSONUtils {
        /**
     * comprueba si string contiene formato json(object || array)
     * @param json
     * @return
     */
    public static boolean isJSONValid(String json) {
        try {
            new JSONObject(json);
        } catch (JSONException ex) {
            try {
                new JSONArray(json);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }


    /**
     * devuelve un objeto json array que se encuentra dentro de un
     * objeto JSON
     * @param jsonObject JSONObject
     * @param KEY String
     * @return JSONArray
     * @throws Exception
     */
    public static JSONArray getJSONArrayFromJSONObject(JSONObject jsonObject, final String KEY)throws Exception
    {
        if(jsonObject == null)
            throw new Exception("jsonObject vac?o!");

        return jsonObject.getJSONArray(KEY);    // parsing
    }


    /**
     * devuelve una lista de un jsonArray que contiene un arreglo de string
     * @param jsonArray JSONArray
     * @throws Exception
     */
    public static ArrayList<String> getArrayListStringFromJSONArray(JSONArray jsonArray)throws Exception
    {
        ArrayList<String> alJSON = new ArrayList<String>();
        for (int i = 0; i < jsonArray.length(); i++)
            alJSON.add((String) jsonArray.get(i));

        return alJSON;    // parsing
    }

    /**
     * devuelve una lista de un jsonArray que contiene un arreglo de int
     * @param jsonArray JSONArray
     * @throws Exception
     */
    public static ArrayList<Integer> getArrayListIntegerFromJSONArray(JSONArray jsonArray)throws Exception
    {
        ArrayList<Integer> alJSON = new ArrayList<Integer>();
        for (int i = 0; i < jsonArray.length(); i++)
            alJSON.add((Integer) jsonArray.get(i));

        return alJSON;    // parsing
    }




    /**
     * M?todo que obtiene el valor String de un jsonObject..
     *
     * @param jsonObject
     *            - JSONObject que apunta a un item devuelto del servicio de
     *            datos
     * @param name
     *            - Nombre de la columna de la cual se quiere obtener la
     *            informaci?n
     * @return Devuelve el valor String de la columna indicada en el cursor
     *         indicado. Por defecto se devolver? una cadena vac?a
     */
    public static String verificarString(JSONObject jsonObject, String name) {
        String aux = "";
        try {
            aux = jsonObject.isNull(name) ? "" : jsonObject.getString(name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return aux;
    }

    /**
     * M?todo que obtiene el valor Integer de un jsonObject
     *
     * @param jsonObject
     *            - JSONObject que apunta a un item devuelto del servicio de
     *            datos
     * @param name
     *            - Nombre de la columna de la cual se quiere obtener la
     *            informaci?n
     * @return Devuelve el valor Integer de la columna indicada en el cursor
     *         indicado. Por defecto se devolver? 0
     */
    public static Integer verificarInteger(JSONObject jsonObject, String name) {
        int aux = 0;
        try {
            aux = jsonObject.isNull(name) ? 0 : jsonObject.getInt(name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return aux;
    }

    /**
     * M?todo que obtiene el valor Integer de un jsonObject
     *
     * @param jsonObject
     *            - JSONObject que apunta a un item devuelto del servicio de
     *            datos
     * @param name
     *            - Nombre de la columna de la cual se quiere obtener la
     *            informaci?n
     * @return Devuelve el valor Integer de la columna indicada en el cursor
     *         indicado. Por defecto se devolver? 0
     */
    public static Float verificarFloat(JSONObject jsonObject, String name) {
        float aux = 0;
        try {
            aux = jsonObject.isNull(name) ? 0 : Float.parseFloat(jsonObject.getString(name));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return aux;
    }
}
