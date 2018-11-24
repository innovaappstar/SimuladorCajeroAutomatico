/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cajeroautomatico.adapter;

import cajeroautomatico.entities.Cliente;
import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author Kenny
 */
public class ClienteAdapter extends AbstractListModel{
 
    private ArrayList<Cliente> lista = new ArrayList<>();
 
    @Override
    public int getSize() {
        return lista.size();
    }
 
    public void addList(ArrayList<Cliente> alCliente){
        this.lista = alCliente;
        this.fireIntervalAdded(this, getSize(), getSize()+1); 
    }
    
    @Override
    public Object getElementAt(int index) {
        Cliente cliente = lista.get(index);
        return cliente.getNombre();
    }
    public void add(Cliente cliente){
        lista.add(cliente);
        this.fireIntervalAdded(this, getSize(), getSize()+1);
    }
    public void eliminar(int index0){
        lista.remove(index0);
        this.fireIntervalRemoved(index0, getSize(), getSize()+1);
    }
    public Cliente get(int index){
        return lista.get(index);
    }
}
