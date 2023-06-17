package com.sevilla.usermanager.enums;

public enum StatusResponse {
    /**
     * Imprime success en respuesta
     */
    SUCCESS {
        @Override
        public String toString() {
            return "success";
        }
    },
    /**
     * Imprime error en respuesta
     */
    ERROR {
        @Override
        public String toString() {
            return "error";
        }
    },
    NOT_FOUND {
        @Override
        public String toString() {
            return "not found";
        }
    },
    UNAUTHORIZED {
        @Override
        public String toString() {
            return "un authorized";
        }
    }
}
