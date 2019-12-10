
public class Clovek implements Rasa {
    private final String rasa="Clovek";

    @Override
    public double silaBonus(int sila) {
        // TODO Auto-generated method stub
        return (sila / 1000) * 3;
    }

    @Override
    public double inteligenceBonus(int sila) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double obratnostBonus(int sila) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double odolnostBonus(int sila) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getRasa() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
