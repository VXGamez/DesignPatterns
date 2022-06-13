package View;

/*
CLASSE Screen

Aquesta classe vaig dubtar si fer-la però finalment la he realitzada per un simple motiu: perque el controlador parli amb la vista per printar per pantalla.

Si realitzés un System.out des del controaldor estaria carregant-me el model MVC i per tant no seria correcte, el que fagi és accedir a lfes funcions de print i println d'aquesta classe i printar per la pantalla des-de aqui.

Això dona la capacitat de que si demà es vol fer servir un sistema de finestres com Swing, només caldria canviar les funcions de la vista i poques coses més del programa perque funcioni perfectament.

*/
public class Screen {

    public static void print(String str){
        System.out.print(str);
    }

    public static void println(String str){
        System.out.println(str);
    }
}
