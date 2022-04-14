import java.lang.invoke.LambdaConversionException;

class DatePrinter implements Runnable{

    @Override
    public void run() {
        for(int i = 0; i < 20; i++){
            System.out.println(new java.util.Date());

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class CountPrinter implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println(i+1);

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

class CountPrinter2 extends Thread{

    @Override
    public void run() {
        super.run();
        for (int i = 0; i <20 ; i++) {
            System.out.println("CP2: " + (i+1));
        }
    }
}

public class FirstThread {

    public static void main(String[] args) {

        //Variante 1:
        //-Schreiben einer Klasse, welche Runnable implementiert
        //erzeugen eines Threads, dem das Runnable Objekt übergeben wird
        //Thread starten mit .start() (NICHT .run() !!!!)
        // wsl die meisteverwendete Variante
        Thread t1 = new Thread(new DatePrinter());
        t1.start();

        //Variante 2:
        //wie Var. 1, Kurzschreibweise, Thread Variable
        new Thread(new CountPrinter()).start();

        //Variante 3:
        //Klasse Thread implementiert selbst das Interface Runnable
        //leite eigene Klasse von Thread ab
        //implementiere die .run() Methode
        //starte Thread mit .start()
        //ACHTUNG: Vererbung ist mit dieser Variante "erledigt"
        new CountPrinter2().start();

        //Variante 4:
        //annonyme innere Klasse
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20 ; i++) {
                    System.out.println("Lambda: "+(i+1));
                }
            }
        }).start();

        //Variante 5:
        //Kurzsschreibweise von Var.4 mit Lambda Expression
        //Thread Konstruktur will Runnable Objekt, Runnable hat nur eine Methode(run)
        new Thread(() -> {
            for (int i = 0; i < 20 ; i++) {
                System.out.println("Lambda: "+(i+1));
            }
        }).start();

    }
}