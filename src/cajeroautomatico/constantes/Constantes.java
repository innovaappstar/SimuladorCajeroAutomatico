/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cajeroautomatico.constantes;

/**
 *
 * @author Kenny
 */
public class Constantes {
    
    public final static String OBJETOHISTORIALBUSQUEDA = "HistorialBusqueda";

    public final static String QR = "qrData";
    public final static String IPPUBLICA = "181.177.243.94:2029";  //181.177.243.94:2029
    public final static String IPLOCAL = "192.168.2.17:2029";
    public final static String IPLOCALKANE = "192.168.2.65:2029";

    public final static int TAMANIODEFAULT = 4;
    public final static int TAMANIOFOCUS = 15;
    public final static String RANGOPREDETERMIADO = "5000";



    public final static int CODRESULTADOVACIO = 0;

    public final static int PUERTO = 2030;
    public final static int RESULT_OK = 1;
    public final static int RESULT_ERROR = -1;  // 1 || 0
    public final static int RESULT_WARNING  = 0;


    public final static int ACTIVO = 1;
    public final static int INACTIVO = 2;

    public final static String SEPBV = "|";
    public final static String SEPSN = "~";
    public final static String SEPAS = "*";
    public final static String SECOM = ",";

    public final static String DESA = "#";

    public final static String DESBV = "\\" + SEPBV;   // '\\|'
    public final static String DESAS = "\\" + SEPAS;
    public final static String DESSLASH = "\\/";

    public final static int BAJA    = 1;    // prioridades para los indicadores
    public final static int MEDIA   = 2;
    public final static int ALTA    = 3;


    public final static int CODHABILITADO = 1;
    public final static int CODDESHABILITADO = 0;

    // abstract communication
    public final static int IDRUTAERRONEA           = -1;


    public final static int IDMENSAJETOWEBSOCKET    = 10003;
    public final static int IDLISTENERWEBSOCKET     = 10004;
    public final static int IDGPS                   = 10005;


    //aÃ±adidos
    public final static int FLAG_LOCAL = 0;
    public final static int FLAG_ENVIADO = 1;
    public final static int COD_SQL = 0;

//    public static Host getHost(){
//        Host host = new Host();
//        host.setHost(Constantes.IPLOCAL);
//        return host;
//    }
//
//    public static Host getHostKane(){
//        Host host = new Host();
//        host.setHost(Constantes.IPLOCALKANE);
//        return host;
//    }
//
//    public static Host getHostPublica(){
//        Host host = new Host();
//        host.setHost(Constantes.IPPUBLICA);
//        return host;
//    }


}
