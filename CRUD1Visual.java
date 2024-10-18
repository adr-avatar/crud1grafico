/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.crud1visual;

import javax.swing.SwingUtilities;

/**
 *
 * @author adr
 */
public class CRUD1Visual {

  public static void main(String[] args) {
     SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MiAplicacion app = new MiAplicacion();
                app.setVisible(true);
            }
        });
    }
}
