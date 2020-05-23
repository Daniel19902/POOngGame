package aplicacion;

public class ColdRacket extends Power {
    @Override
    public void spell(Table table, int index) {
        table.getBall().setColder(true);
    }

    @Override
    public void deleteSpell() {

    }
}
