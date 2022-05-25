/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author Lucas Samoyedem
 */
public class Filtros {

    private String field;
    private Object value;
    private boolean containing;

    public Filtros(String field, Object value, boolean containing) {
        this.field = field;
        this.value = value;
        this.containing = containing;
    }

    public Filtros(String field, Object value) {
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isContaining() {
        return containing;
    }

    public void setContaining(boolean containing) {
        this.containing = containing;
    }

    public String getValueAsString() {
        if (value != null) {
            return "'" + String.valueOf(value) + "'";
        }
        return null;
    }

    public String getOperador() {
        if (containing) {
            return " CONTAINING ";
        } else {
            return " = ";
        }
    }

    public boolean possuiFiltro() {
        return value != null && !value.toString().isEmpty() && field != null && !field.isEmpty();
    }

    public String getClausula() {
        if (possuiFiltro()) {
            return field + getOperador() + getValueAsString();
        }
        return "";
    }
}
