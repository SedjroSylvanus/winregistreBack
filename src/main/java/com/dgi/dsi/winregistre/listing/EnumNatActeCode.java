package com.dgi.dsi.winregistre.listing;

public enum EnumNatActeCode {


    ACTE_CODE_404_409("404_409", "Code Général des Impôts 404 à 409"),
    ACTE_CODE_409BIS_1("409_1", "Code Général des Impôts 409 Bis 1"),
    ACTE_CODE_409BIS_2("409_2", "Code Général des Impôts 409 Bis 2"),
    ACTE_CODE_FIXE("FIXE", "Code Général des Impôts FIXE"),
    ACTE_CODE_TAUXFIXE("TAUXFIXE", "Code Général des Impôts Taux Fixe"),
    ACTE_CODE_SUC_MUT("SUC_MUT", "Code Général des Impôts Succession");
    private String code;
    private String message;

    EnumNatActeCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
