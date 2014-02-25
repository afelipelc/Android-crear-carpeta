package com.principal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

/**
 * Ejemplo de aplicación para crear una carpeta en el almacenamiento interno del dispositivo
 * crea también un archivo de texto
 * 
 * @author afelipe
 *
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //ingresar a Android Developers en el tema de almacenamiento
        // https://developer.android.com/guide/topics/data/data-storage.html
        
        //Almacenamiento privado
        //Android puede crear una carpeta privada para la aplicacion en /data/mi.paquete.app
        //el concenido no es accesible por otras aplicaciones
        //para obtener la ruta privada
        //getFilesDir();
        

        // La clase estatica Environment proporciona accesibilidad al almacenamiento
        // interno y externo, directori de imagenes, descargas, carpeta publica o compartida, etc.
        //Environment.getExternalStorageDirectory() devuelve la raíz del almacenamiento externo de SO
        // que puede ser el primer almacenamiento interno con el que cuenta el dispositivo, no la sdcard externa


        //se requiere que la aplicación obtenga permiso para escribir en la memoria interna o externa
        //en el archivo Manifest.xml, agregar los permisos
        // <uses-permission android:name="android.permission.WRITE_INTERNAL_STORAGE" />
        // <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
        // antes de la etiqueta <application
        
        //crear carpeta en la raíz del almacenamiento interno
        File carpeta = new File(Environment.getExternalStorageDirectory()+"/miAppFelipe");

        //comprobar si la carpeta no existe, entonces crearla
        if(!carpeta.exists()) {
        	//carpeta.mkdir() creará la carpeta en la ruta indicada al inicializar el objeto File
             if(carpeta.mkdir())
            	 Toast.makeText(getApplicationContext(), "Carpeta creada : " + carpeta.getAbsolutePath(), Toast.LENGTH_SHORT).show();
             //se ha creado la carpeta;
        }else
        {
        	//la carpeta ya existe
        	Toast.makeText(getApplicationContext(), "Carpeta existente : " + carpeta.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        }
        
        Log.d("carpeta creada", carpeta.getAbsolutePath());
        
        //crear un archivo de texto en la carpeta creada
        try
        {
            File archivotxt = new File(carpeta, "miTexto.txt");
            FileWriter writer = new FileWriter(archivotxt);
            writer.append("Este es el contenido de mi archivo de texto.");
            writer.flush();
            writer.close();
            Toast.makeText(this, "Archivo guardado", Toast.LENGTH_SHORT).show();
        }
        catch(IOException e)
        {
        	//mostrar en el Logcat el error
             Log.d("Error al crear archivo",e.getStackTrace().toString());
        }
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
