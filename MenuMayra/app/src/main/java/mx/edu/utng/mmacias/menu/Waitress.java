package mx.edu.utng.mmacias.menu;

/**
 * Created by UTNG on 20/09/2017.
 */

public class Waitress { //camarero
    MenuComponent allMenus;

    public Waitress(MenuComponent allMenus){
        this.allMenus=allMenus;

    }

    public String printMenu(){
        return allMenus.print();

    }
}
