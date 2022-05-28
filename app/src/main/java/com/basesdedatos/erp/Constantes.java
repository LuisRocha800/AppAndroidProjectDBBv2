package com.basesdedatos.erp;

public class Constantes {

    private final static String URL_WEB_SERVICE = "https://warm-refuge-72804.herokuapp.com/";
    public final static String URL_PERSONA_ID_WEB_SERVICE = URL_WEB_SERVICE+"Persona_Buscar_ID_GET.php";
    public final static String URL_USUARIOCLIENTE_ID_WEB_SERVICE = URL_WEB_SERVICE+"UsuarioCliente_Buscar_ID_GET.php";
    public final static String URL_USUARIOEMPLEADO_ID_WEB_SERVICE =URL_WEB_SERVICE+"UsuarioEmpleado_Buscar_ID_GET.php";
    public final static String URL_USUARIOADMIN_ID_WEB_SERVICE =URL_WEB_SERVICE+"UsuarioAdmin_Buscar_ID_GET.php";
    public final static String URL_USUARIOGLOBAL_ID_WEB_SERVICE =URL_WEB_SERVICE+"UsuarioGlobal_Buscar_ID_GET.php";
    public final static String URL_ACTUALIZAR_PERSONA = URL_WEB_SERVICE+"Actualizar_Persona_POST.php";
    public final static String URL_ACTUALIZAR_DIRECCION = URL_WEB_SERVICE+"Actualizar_Direccion_POST.php";

    public final static String URL_INSERTAR_PROVEEDOR = URL_WEB_SERVICE+"Insertar__Proveedor_POST.php";
    public final static String URL_ACTUALIZAR_PROVEEDOR = URL_WEB_SERVICE+"Actualizar_Proveedor_POST.php";
    public final static String URL_ELIMINAR_PROVEEDOR = URL_WEB_SERVICE+"Delete_Proveedor_POST.php";
    public final static String URL_BUSCAR_PROVEEDOR_ID = URL_WEB_SERVICE+"Consulta_Proveedor_ID_GET.php";

    public final static String URL_INSERTAR_RUTA = URL_WEB_SERVICE+"Insertar_Ruta_POST.php";
    public final static String URL_ACTUALIZAR_RUTA = URL_WEB_SERVICE+"Actualizar_Ruta_POST.php";
    public final static String URL_ELIMINAR_RUTA = URL_WEB_SERVICE+"Delete_Ruta_POST.php";
    public final static String URL_BUSCAR_RUTA_ID = URL_WEB_SERVICE+"Consulta_Ruta_ID_GET.php";

    public final static String URL_CONSULTA_FECHAS = URL_WEB_SERVICE+"Consulta_Ventas_Fecha_POST.php";

    public final static String URL_CONSULTA_VENTA = URL_WEB_SERVICE+"Consultar_ventas_POST.php";

    // "URL_CLIENTE_X_ID" variable temporal solo para que no tire errores, no afecta funcionamiento
    // Luego lo corrijo
    public final static String URL_CLIENTE_X_ID = "";


}
