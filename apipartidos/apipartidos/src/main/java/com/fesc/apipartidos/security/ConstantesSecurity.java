package com.fesc.apipartidos.security;

public class ConstantesSecurity {
    
    //tiempo de expiracion del token
    public static final long FECHA_EXPIRACION= 864000000;

    //Constante para URL del login 
    public static final String SINGUP_URL= "/usuario";

    //cuando hago la peticion envio al encabezado unas propiedades, que estara leyendo el api (entre esas el token)
    //cuando se refiere a Authorization es la palabra que entiende por defecto para la autorizacion
    public static final String HEADER_STRING= "Authorization";

    
    //cuando encuentre esta palabra todo lo que encuentre en adelante es el token 
    public static final String TOKEN_PREFIJO= "Bearer ";

    //token encriptado 
    public static final String TOKEN_SECRETO = "aciZNknCWq1numJ3uYFVtClpyt16NdoGOxOp0jM7cNc9H3fS0S2PrQetsnnHZnsUm4/ZVsi+yldYGDWTGcQnOg==";

}
