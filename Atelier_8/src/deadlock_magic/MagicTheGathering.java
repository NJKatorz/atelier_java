package deadlock_magic;

public class MagicTheGathering {
    public static void main(String[] args) throws InterruptedException{

        Grimoire codex = new Grimoire();

        Thread ceremonie1 =new Thread(){
            public void run(){
                Grimoire.Ceremonie.ASSEMBLEE_DES_ONDES_FRISSONNATES.debuter();
            }
        } ;

        Thread ceremonie2 =new Thread(){
            public void run(){
                Grimoire.Ceremonie.RITUEL_DU_CREPUSCAL_ABYSSAL.debuter();
            }
        } ;

        Thread ceremonie3 =new Thread(){
            public void run(){
                Grimoire.Ceremonie.SYMPOSIUM_DES_ILLUSIONS_PRISMATIQUES.debuter();
            }
        } ;

        ceremonie1.start();
        ceremonie2.start();
        ceremonie3.start();

        ceremonie1.join();
        ceremonie2.join();
        ceremonie3.join();


        System.out.println("Toutes les cérémonie sont finies !");
    }
}
