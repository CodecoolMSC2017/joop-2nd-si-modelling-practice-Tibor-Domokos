public class Other extends Edible {

    boolean isToxic;

    public Other(String name, int calories, boolean isExpired) {
        super(name, calories, isExpired);
        this.isToxic = isToxic;
    }

}