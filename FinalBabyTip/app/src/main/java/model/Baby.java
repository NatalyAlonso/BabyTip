package model;

/**
 * Created by monicacid on 5/5/15.
 */
public class Baby {

    private String picture;
    private String name;
    private int age;
    private int id_baby;
    private String fecha_nacimiento;
    private int peso;
    private  int estatura;
    public Baby(){}

    public Baby(String name, int age){
        this.name = name;
        this.age = age;
    }
    public String getPicture() {
        return picture;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getId_baby() {
        return id_baby;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId_baby(int id_baby) {
        this.id_baby = id_baby;
    }

    public String getFecha_nacimiento() {return fecha_nacimiento;}

    public void setFecha_nacimiento(String fecha_nacimiento) {this.fecha_nacimiento = fecha_nacimiento;}

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getEstatura() {
        return estatura;
    }

    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }
}
