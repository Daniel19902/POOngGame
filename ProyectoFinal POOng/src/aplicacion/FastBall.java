package aplicacion;

public class FastBall extends Power  {

    @Override
    public void spell(Table table, int index) {
        table.getBall().fastBall();
    }

    @Override
    public void deleteSpell() {

    }
}
