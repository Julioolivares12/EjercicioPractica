package backend;

import java.util.ArrayList;

public class Usuarios {
    private ArrayList correos = new ArrayList();
    private ArrayList claves = new ArrayList();
    private ArrayList nombres = new ArrayList();
    private ArrayList niveles = new ArrayList();

    public static  Usuarios UsuariosIntance;

    public static Usuarios getInstance(){
        if(UsuariosIntance == null){
            UsuariosIntance = new Usuarios();
        }
        return  UsuariosIntance;
    }

    private Usuarios(){
        //int nivel = 1;
        //for(int idx = 0; idx <= 99; idx++){
        //    correos.add("jacevedo"+idx+"@utec");
        //    claves.add("clave"+idx);
        //    nombres.add("Jorge " + idx);
        //
        //    if(nivel == 4){
        //        nivel = 1;
        //    }
        //    niveles.add(String.valueOf(nivel));
        //    nivel++;
        //}
    }



    public void setCorreo(String correo){
        correos.add(correo);
    }
    public void setClave(String clave){
        claves.add(clave);
    }
    public void setNombre(String nombre){
        nombres.add(nombre);
    }
    public void setNivel(String nivel){
        niveles.add(nivel);
    }
    public String getCorreo(int indice){
        return correos.get(indice).toString();
    }
    public String getClave(int indice){
        return claves.get(indice).toString();
    }
    public String getNombre(int indice){
        return nombres.get(indice).toString();
    }
    public String getNivel(int indice){
        return niveles.get(indice).toString();
    }

    public int getCantidadCorreos(){
        return correos.size();
    }

    public int getIndiceCorreo(String correo){
        return correos.indexOf(correo);
    }

    public void setCorreo(int index, String correo){
        correos.set(index,correo);
    }

    public void setClave(int index, String clave){
        claves.set(index,clave);
    }
    public void setNombre(int index, String nombre){
        nombres.set(index,nombre);
    }
    public void setNivel(int index, String nivel){
        niveles.set(index,nivel);
    }

    public void removeCorreo(int index){
        correos.remove(index);
    }

    public void removeNombres(int index){
        nombres.remove(index);
    }

    public void removeClave(int index){
        claves.remove(index);
    }

    public void removeNivel(int index){
        niveles.remove(index);
    }

    public Object[] getAllCorreos(){
        return correos.toArray();
    }
}
