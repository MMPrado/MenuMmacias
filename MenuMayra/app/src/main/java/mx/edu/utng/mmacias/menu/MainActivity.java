package mx.edu.utng.mmacias.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner cmbMenus;
    Spinner cmbMenuSeleccionado;
    Button btnAgregarPlatillo;
    Button btnmostrarOrden;
    String[] desayunos={"huevos a la Mexicana","chilaquiles","Jugo de naranja","Coctel de Frutas","Hotcakes","Crepas"};
    String [] comidas={"Chiles rellenos","Pollo empanizado","Enchiladas Verdes","Enchiladas Rojas","Mole de Ollas"};
    String [] cena={"Tacos de Pastor","Quesadillas","Pozole","Chocolate"};
    ArrayAdapter<String> adapter;
    MenuComponent desayunosMenu=new Menu("Menu de desayuno","Desayunos");
    MenuComponent comidasMenu = new Menu("Menu de Comidas","Comidas");
    MenuComponent cenasMenu  = new Menu("Menu de Cenas","Cenas");
    MenuComponent allMenus  = new Menu("Todos los menus ","Menu Combinado");
    TextView txtOrden;//<---

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cmbMenus = (Spinner) findViewById(R.id.cmb_menus);
        cmbMenuSeleccionado = (Spinner) findViewById(R.id.cmb_menu_disponible);
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, desayunos);
        btnAgregarPlatillo= (Button)  findViewById(R.id.btn_agregar);
        btnmostrarOrden= (Button)  findViewById(R.id.btn_mostrar_orden);
        txtOrden=(TextView)  findViewById(R.id.txt_orden);
        cmbMenuSeleccionado.setAdapter(adapter);
        cmbMenus.setOnItemSelectedListener(this);
        cmbMenuSeleccionado.setOnItemSelectedListener(this);

        btnAgregarPlatillo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int m = (int) cmbMenus.getSelectedItemId();
                int p = (int) cmbMenuSeleccionado.getSelectedItemId();
                Toast.makeText(getApplicationContext(), "platillo="+p, Toast.LENGTH_LONG).show();
                MenuItem menuItem = null;
                switch (m) {//tipo de menu
                    case 0://menu desayuno
                        switch (p) {//tipo de platillo
                            case 0://huevos a la mexicana
                                menuItem = new MenuItem("Huevos a la mexicana", "huevos estrellados con salsa verde", false, 25.0);
                                break;
                            case 1://chilaquiles
                                menuItem = new MenuItem("Chilaquiles", "chilaquiles en salasa roja", true, 20.0);
                                break;
                        }// fin de switch desayuno
                        desayunosMenu.add(menuItem);
                        Toast.makeText(getApplicationContext(), "Se agrego paltillo", Toast.LENGTH_LONG).show();

                        break;
                    case 1://comidas
                        switch (p) {//tipo de platillo
                            case 0://chiles rellenos
                                menuItem = new MenuItem("chiles rellenos", "chiles rellenos de queso panela", true, 25.0);
                                break;
                            case 1://pollo empanizado
                                menuItem = new MenuItem("Pollo empanizado", "milanesa de pollo con papas", false, 20.0);
                                break;
                        }
                        comidasMenu.add(menuItem);
                        Toast.makeText(getApplicationContext(), "Se agrego paltillo", Toast.LENGTH_LONG).show();
                        break;
                    case 2: //cenas
                        switch (p) {
                            case 0://tacos al pastor
                                menuItem = new MenuItem("Tacods al pastor", "orden de 4 tacos", false, 20.0);
                                break;
                            case 1: //quesadillas
                                menuItem = new MenuItem("Quesadillas", "orden de 2 quesadillas de flor de calabaza", true, 20.0);
                                break;
                        }
                        cenasMenu.add(menuItem);
                        Toast.makeText(getApplicationContext(), "Se agrego paltillo", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
        btnmostrarOrden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allMenus.add(desayunosMenu);
                allMenus.add(comidasMenu);
                allMenus.add(cenasMenu);
               Toast.makeText(getApplicationContext(), allMenus.print(), Toast.LENGTH_LONG).show();
                //txtOrden.setText(allMenus.print());
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        int m=0;//menu
        if (adapterView.getId() == R.id.cmb_menus) {//primer menu
            m=(int)cmbMenus.getSelectedItemId();
            Toast.makeText(getApplicationContext(), "m="+m, Toast.LENGTH_LONG).show();
            switch ((int)cmbMenus.getSelectedItemId()) {
                case 0:

                    adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, desayunos);
                    cmbMenuSeleccionado.setAdapter(adapter);

                    break;
                case 1:
                    adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, comidas);
                    cmbMenuSeleccionado.setAdapter(adapter);
                    break;
                case 2:
                  adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, cena);
                    cmbMenuSeleccionado.setAdapter(adapter);
                    break;

            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
