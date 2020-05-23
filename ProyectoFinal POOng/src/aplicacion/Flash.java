package aplicacion;



public class Flash extends Power {



    @Override
    public void spell(Table table, int index) {
        power = true;
        table.getBall().setDy(table.getBall().getDy()*2);
        while (true){
            if(table.intersect()){
                table.getBall().setDy(table.getBall().getDy()/2);
                break;
            }else if(table.getBall().getY()<0 || table.getBall().getX()>790){
                break;
            }
        }
    }

    @Override
    public void deleteSpell() {
    }
}
