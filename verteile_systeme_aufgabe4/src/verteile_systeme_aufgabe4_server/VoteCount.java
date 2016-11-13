/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verteile_systeme_aufgabe4_server;

import java.io.Serializable;

/**
 *
 * @author Mike
 */
public class VoteCount implements Serializable {

    public int yes;
    public int no;
    public int other;
    
    public VoteCount(int yes, int no, int other) {
        this.no = no;
        this.other = other;
        this.yes = yes;
    }
    
}
