package model;

/**
 * Created by monicacid on 5/24/15.
 */
public class Test {

    private int id_test;
    private String name;
    private int id_guide;
    private int grade;
    private String content;

    public int getId_test() {
        return id_test;
    }

    public void setId_test(int id_test) {
        this.id_test = id_test;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    public int getId_guide() {
        return id_guide;
    }

    public void setId_guide(int id_guide) {
        this.id_guide = id_guide;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getContent() {return content;}

    public void setContent(String content) {this.content = content;}
}
