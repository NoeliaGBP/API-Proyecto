package mx.edu.utez.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import mx.edu.utez.rol.model.RolDAO;
import mx.edu.utez.usuario.model.Usuario;

public class Token {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Token createToken(Usuario usuario){
        Token myToken = new Token();
        try{
            Algorithm algorithm = Algorithm.HMAC256("secretkey");
            String token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("nombreUsuario", usuario.getNombreUsuario())
                    .withClaim("rol", usuario.getIdRol().getIdRol())
                    .sign(algorithm);

            myToken.setToken(token);
        }catch (JWTCreationException e){
            String message = "ERROR: No se genero el token";
            System.err.println("Error No se genero el token");
        }

        return myToken;
    }

    public Object checkToken(String token, boolean identity){
        try{
            Algorithm algorithm = Algorithm.HMAC256("secretkey");
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();

            DecodedJWT jwt = verifier.verify(token);
            RolDAO rolDAO = new RolDAO();
            if(identity){
                Usuario usr = new Usuario();
                usr.setNombreUsuario(jwt.getClaim("nombreUsuario").asString());
                usr.setIdRol(rolDAO.getRolById(jwt.getClaim("rol").asInt()));
                return usr;
            }else{
                return true;
            }
        }catch (JWTVerificationException e){
            return false;
        }catch (Exception e){
            System.err.println("Ha ocurrido un error inesperado...");
            return false;
        }
    }
}
