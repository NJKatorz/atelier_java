package domaine;

public enum Unite {
    GRAMME("gr"), KILOGRAMME("kg"), LITRE("l"), MILLILITRE("ml"), CENTILITRE("cl"), DECILITRE("dl"),
    CUILLER_A_CAFE("cc"), CUILLER_A_THE("ct"), CUILLER_A_DESSERT("cd"), CUILLER_A_SOUPE("cs"), PINCEE("pincée"), UN_PEU("peu"), NEANT("");

    private String abrevation;

    Unite(String abrevation) {
        this.abrevation = abrevation;
    }

    @Override
    public String toString() {
        return abrevation;
    }
}
