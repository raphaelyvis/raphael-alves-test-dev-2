package com.nitryx.backend.exceptions

class SaldoNegativoException extends RuntimeException{

    SaldoNegativoException(String message) {
        super(message)
    }
}
