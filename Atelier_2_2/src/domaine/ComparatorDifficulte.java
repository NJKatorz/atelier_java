package domaine;
import java.util.Comparator;

public class ComparatorDifficulte implements Comparator<Plat> {

    @Override
    public int compare(Plat o1, Plat o2) {
        return o1.getNiveauDeDifficulte().compareTo(o2.getNiveauDeDifficulte());
    }
}
