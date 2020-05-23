package persistencia;

import aplicacion.POOngGame;

import java.io.*;

public class Salve {

    public Salve(){

    }

    public void optionSalve(POOngGame poOngGame, File file) throws IOException {
        FileOutputStream salve = new FileOutputStream(file);
        ObjectOutputStream write = new ObjectOutputStream(salve);
        write.writeObject(poOngGame);
        write.close();
    }

    public POOngGame optionOpen(File file) throws IOException, ClassNotFoundException {
        FileInputStream open = new FileInputStream(file);
        ObjectInputStream read = new ObjectInputStream(open);
        POOngGame aux = (POOngGame) read.readObject();
        read.close();
        return aux;
    }

}
