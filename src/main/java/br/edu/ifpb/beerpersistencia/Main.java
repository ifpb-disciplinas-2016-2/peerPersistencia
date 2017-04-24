/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.beerpersistencia;

import javax.persistence.Persistence;

/**
 *
 * @author laerton
 */
public class Main {
    public static void main(String[] args) {
        GenericPersiste<Cliente> p = new GenericPersiste<>();
        Cliente cliente = new Cliente("Laerton", "Marques", "laerton240311@gmail.com");
        p.create(cliente);
        
    }
    
    
}
