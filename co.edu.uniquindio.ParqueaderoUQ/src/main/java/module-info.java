module co.edu.uniquindio.parqueaderouq.parquearoapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens co.edu.uniquindio.parqueaderouq.parquearoapp to javafx.fxml;
    exports co.edu.uniquindio.parqueaderouq.parquearoapp;

    opens co.edu.uniquindio.parqueaderouq.parquearoapp.controller;
    exports co.edu.uniquindio.parqueaderouq.parquearoapp.controller;

    opens co.edu.uniquindio.parqueaderouq.parquearoapp.model;
    exports co.edu.uniquindio.parqueaderouq.parquearoapp.model;

    opens co.edu.uniquindio.parqueaderouq.parquearoapp.utils;
    exports co.edu.uniquindio.parqueaderouq.parquearoapp.utils;
}