/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cajeroautomatico.http;

import cajeroautomatico.constantes.Constantes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import sun.net.www.http.HttpClient;
import org.json.*;

/**
 *
 * @author Kenny
 */
public abstract class BaseAsyntask {
    
    private String nomArrayJSON;
    private String url;
    protected String HOST = "http://192.168.1.104:2030";
    public BaseAsyntask(String nomArrayJSON, String url) {
        this.nomArrayJSON = nomArrayJSON;
        this.url = (HOST + url);
    }
    
    public abstract void onPostDataJSON(int codEstado, JSONArray jsonArray)throws JSONException;

    public void onPostDataJSONObject(int codEstado, JSONObject jsonObject)throws JSONException
    {
        // only the childs to use this method
    }

    public void onPostError(int codEstado, String descripcion)
    {
        // mÃŠtodo en clase padre
    }
    
    /**
     * ejecuta petición get
     * devuelve respuesta exito/error
     */
    public void execute(){
        String response = this.GET(this.url); // only get for the moment...
        this.onPostExecute(response);
    }
    
//    @Override
    protected void onPostExecute(String result)
    {
        try {
            if (result.length() == 0)
            {
                this.onPostDataJSON(Constantes.RESULT_ERROR, new JSONArray());
                return;
            }

            JSONObject json = new JSONObject(result);   // { dt0 : [{}], dt1 : [{}] }, { result : { dt0 : [{}], dt1 : [{}] } }
            if(json.length() > 1)
            {
                this.onPostDataJSONObject(Constantes.RESULT_OK, json);
                return;
            }

            if(json.length() > 0)
            {
                this.onPostDataJSONObject(Constantes.RESULT_OK, json);
                return;
            }

            JSONArray jsonArray = json.getJSONArray(this.nomArrayJSON);
            if (jsonArray != null && jsonArray.length() > 0){
                this.onPostDataJSON(Constantes.RESULT_OK, jsonArray);
            }
            else{
                this.onPostDataJSON(Constantes.RESULT_ERROR, jsonArray);
            }


        }catch (JSONException jsonex)
        {
            jsonex.printStackTrace();
//            new IncidenciaDAO().insertIncidencia(Constantes.getIncidenciaAplicativo(jsonex.getMessage()));
        }
    }

    
    protected String GET(String urlString){
        InputStream inputStream = null;
        String result = "";
        try
        {
            String responsestring = "";
            URL url = new URL(urlString);
            HttpURLConnection c = (HttpURLConnection)url.openConnection();  //connecting to url
            c.setRequestMethod("GET");
            result = this.convertInputStreamToString(c.getInputStream());
        } catch (Exception e) {
            // si no hay internet
            this.onPostError(Constantes.RESULT_ERROR, e.getMessage());
        }
        return result;
    }
    
        /**
     * 1|2|3|
     * apd-123|se trabÃŗ la unidad|
     * nota : procurar trabajar con buffer's no tan extensos o podrÃ­a tardar el serializado
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
    
}
