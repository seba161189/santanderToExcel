package santandertoexcel;

import java.io.IOException;
import javax.swing.JOptionPane;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class SantanderToExcel {
 static Screen s=new Screen();
final static String IMAGES_PATH="C:\\Users\\seba1\\Desktop.sikuli\\";
static String newTab=IMAGES_PATH+ "newTab.PNG";
static String santander=IMAGES_PATH+"santander.PNG";
static String tabla=IMAGES_PATH+"tabla.PNG";
static String finTabla=IMAGES_PATH+"finTabla.PNG";
static String excel=IMAGES_PATH+"excel.PNG";
static String nuevaHoja=IMAGES_PATH+"nuevaHoja.PNG";
        static String fila1colA=IMAGES_PATH+"fila1colA.PNG";
        static String paginaExcel=IMAGES_PATH+"paginaExcel.PNG";
    public static void main(String[] args) throws IOException, InterruptedException, FindFailed {
      
      abrirNavegador();
      
    }
       
    private static void abrirNavegador() throws IOException, InterruptedException, FindFailed {
            Runtime.getRuntime().exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
            esperarQueAbraElNavegador();
    }
    
    private static void esperarQueAbraElNavegador() throws InterruptedException, IOException, FindFailed {
      //esperando que aparezca el navegador abierto
        if(s.exists(new Pattern(newTab).similar(0.8f),30)!=null){
            irASantanderYMaximizar();
        }else{
            abrirNavegador();
        }

    }

    private static void irASantanderYMaximizar() throws InterruptedException, IOException, FindFailed {
        if(s.exists(new Pattern(newTab).similar(0.8f))!=null){
            Thread.sleep(200);
            s.type("d",KeyModifier.ALT);
            Thread.sleep(200);
            s.paste("http://www.santanderrio.com.ar/banco/online/personas/inversiones/super-fondos/rendimientos");// ir a url
            s.type(Key.ENTER);
            Thread.sleep(500);
            s.type(Key.WIN,Key.UP);
            esperarQueEntreSantander();
        }else{
            abrirNavegador();
        }
    }

    private static void esperarQueEntreSantander() throws InterruptedException, IOException, FindFailed {
        if(s.exists(new Pattern(santander).similar(0.8f))!=null){
            scrolearNavegadorHastaEncontrarLaTabla();
        }else{
            irASantanderYMaximizar();
        }
    }

    private static void scrolearNavegadorHastaEncontrarLaTabla() throws InterruptedException, IOException, FindFailed {
     s.wheel(1,2);
      if(s.exists(new Pattern(tabla).similar(0.9f))!=null){
        seleccionarTabla();
     }else{
         esperarQueEntreSantander();
     }
    }

    private static void seleccionarTabla() throws FindFailed, InterruptedException, IOException {
                 
        s.drag(tabla);
         s.dropAt(new Pattern(finTabla).similar(0.9f));
         copiarTabla();
    }

    private static void copiarTabla() throws InterruptedException, IOException, FindFailed {
   s.type("c",KeyModifier.CTRL); 
     irAlExcel();
    }

    private static void irAlExcel() throws InterruptedException, IOException, FindFailed {
             if(s.exists(new Pattern(newTab).similar(0.8f))!=null){
            Thread.sleep(200);
            s.type("d",KeyModifier.ALT);
            Thread.sleep(200);
            s.type("sprea");
            clickExcelPagina();

        }else{
            abrirNavegador();
        } 
    
    }
    
  private static void clickExcelPagina() throws FindFailed, InterruptedException, IOException {
          if(s.exists(new Pattern(paginaExcel).similar(0.8f))!=null){
                  s.click(paginaExcel);
            s.type(Key.ENTER);
            Thread.sleep(500);
            s.type(Key.WIN,Key.UP);
            esperarQueEntreExcel();    
          }
  }

    private static void esperarQueEntreExcel() throws InterruptedException, IOException, FindFailed {
          if(s.exists(new Pattern(excel).similar(0.8f))!=null){
            clickearNuevoSheet();
 
        }else{
            irASantanderYMaximizar();
        }
    }

    private static void clickearNuevoSheet() throws FindFailed, InterruptedException {
          s.click(nuevaHoja);
    pegarTablaEnElExcel();
    }

    private static void pegarTablaEnElExcel() throws FindFailed, InterruptedException {
     if(s.exists(new Pattern(fila1colA).similar(0.8f))!=null){

            Thread.sleep(5000);
            s.type("v",KeyModifier.CTRL);
    }
    }

  
    
}
