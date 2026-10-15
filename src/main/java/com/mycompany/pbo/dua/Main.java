
package com.mycompany.pbo.dua;

import java.sql.SQLException;
import view.PBView;

public class Main {

    public static void main(String[] args) throws SQLException {
        PBView view = new PBView();
        view.tampilkanMenu();
    }
}
