package models;

public class Usuario {
    private String nombre;
    private String correo;
    private String direccion;
    private String clave;

    public Usuario(String nombre, String correo, String direccion, String clave) {
        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    
}
