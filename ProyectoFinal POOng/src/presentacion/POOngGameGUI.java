package presentacion;




public class POOngGameGUI {

    private Start start;

    public POOngGameGUI(){
        start = new Start();
    }


    public static void main(String[] args){
        POOngGameGUI poOngGameGUI = new POOngGameGUI();
        poOngGameGUI.start.setVisible();

    }

}