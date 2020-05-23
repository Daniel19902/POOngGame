package aplicacion;

public class Energy extends Power {
    @Override
    public void spell(Table table, int index) {
        if(index == 0)index = 1;
        else index = 0;
        int giveFortaleza = table.getRackets().get(index).getScore().getLostFortaleza();
        table.getRackets().get(index).getScore().setFortaleza(giveFortaleza*50/100);
    }

    @Override
    public void deleteSpell() {

    }
}
