package deadlock_magic;

public class Grimoire {



    public enum Incantation {
        FLAMBOIEMENT_ARDENT,
        DANSE_DES_COMETES,
        ECLAT_DU_PHENIX,
        RESONNANCE_DES_SAGES,
        MURMURES_GIVRES,
        VORTEX_ANCESTRAL
    }

    public enum Ceremonie {
        /**
        // before
        SYMPOSIUM_DES_ILLUSIONS_PRISMATIQUES(Incantation.FLAMBOIEMENT_ARDENT, Incantation.ECLAT_DU_PHENIX, Incantation.MURMURES_GIVRES),
        ASSEMBLEE_DES_ONDES_FRISSONNATES(Incantation.MURMURES_GIVRES, Incantation.VORTEX_ANCESTRAL, Incantation.FLAMBOIEMENT_ARDENT),
        RITUEL_DU_CREPUSCAL_ABYSSAL(Incantation.RESONNANCE_DES_SAGES, Incantation.MURMURES_GIVRES, Incantation.FLAMBOIEMENT_ARDENT);
        */

        // after
        SYMPOSIUM_DES_ILLUSIONS_PRISMATIQUES(Incantation.FLAMBOIEMENT_ARDENT, Incantation.MURMURES_GIVRES, Incantation.ECLAT_DU_PHENIX),
        ASSEMBLEE_DES_ONDES_FRISSONNATES(Incantation.FLAMBOIEMENT_ARDENT, Incantation.MURMURES_GIVRES, Incantation.VORTEX_ANCESTRAL),
        RITUEL_DU_CREPUSCAL_ABYSSAL(Incantation.FLAMBOIEMENT_ARDENT, Incantation.MURMURES_GIVRES, Incantation.RESONNANCE_DES_SAGES);
        final Incantation incantation1;
        final Incantation incantation2;
        final Incantation incantation3;

        Ceremonie(Incantation incantation1, Incantation incantation2, Incantation incantation3) {
            this.incantation1 = incantation1;
            this.incantation2 = incantation2;
            this.incantation3 = incantation3;
        }

        public void debuter(){
            System.out.println(name() + " débute");
            synchronized (incantation1){
                System.out.println(incantation1 + " pris pour " + name());
                mediter();
                synchronized (incantation2){
                    System.out.println(incantation2 + " pris pour " + name());
                    mediter();
                    synchronized (incantation3){
                        System.out.println(incantation3 + " pris pour " + name());
                        mediter();
                        System.out.println(name() + " lancé !");
                    }
                }
            }
        }

        public void mediter(){
            System.out.println("Méditation");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }


}
