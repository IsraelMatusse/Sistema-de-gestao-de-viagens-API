package com.sgvcore.enums;

public enum ContactoNacional {

    VODACOM(new String[]{"84", "85"}),
    MOVITEL(new String[]{"86", "87"}),
    TMCEL(new String[]{"82", "83"}),

    ;
    private final String[] prefixos;

    ContactoNacional(String[] prefixos) {
        this.prefixos = prefixos;
    }

    public String[] getPrefixos() {
        return prefixos;
    }
}