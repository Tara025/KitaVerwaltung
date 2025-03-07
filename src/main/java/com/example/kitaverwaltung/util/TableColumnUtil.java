package com.example.kitaverwaltung.util;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TableColumnUtil {

    public static <T> void setDateCellFactory(TableColumn<T, Date> column, String pattern) {
        column.setCellFactory(new Callback<>() {
            @Override
            public TableCell<T, Date> call(TableColumn<T, Date> param) {
                return new TableCell<>() {
                    private final SimpleDateFormat format = new SimpleDateFormat(pattern);

                    @Override
                    protected void updateItem(Date item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(format.format(item));
                        }
                    }
                };
            }
        });
    }
}