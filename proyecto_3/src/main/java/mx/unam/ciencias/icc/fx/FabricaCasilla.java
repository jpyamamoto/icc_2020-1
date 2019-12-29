package mx.unam.ciencias.icc.fx;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;

/**
 * Clase para fabricar casillas en una tabla.
 */
public class FabricaCasilla<S, T>
    implements Callback<TableColumn<S, T>, TableCell<S, T>> {

    /**
     * Sobrecarga el método para permitir agregar una celda que funciona como
     * casilla CheckBox.
     * @param columna la columa de la casilla.
     */
    @Override public TableCell<S, T> call(TableColumn<S, T> columna) {
        return new CheckBoxTableCell<S,T>();
    }
}
