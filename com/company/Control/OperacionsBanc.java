package com.company.Control;


import com.company.Exceptions.AccountNotFoundException;
import com.company.Exceptions.ClientNotFound;
import com.company.Exceptions.ExceptionMessage;
import com.company.Menu;
import com.company.Model.Client;
import com.company.Model.CompteEstalvi;

import java.util.List;

public class OperacionsBanc {
    private static final String dniChars="TRWAGMYFPDXBNJZSQVHLCKE";

    public static boolean verificarDNI(String dni) {
        String DNI = dni.trim().replaceAll(" ", "").substring(0, 8);
        char letraDni = dni.charAt(8);
        int numDNI = Integer.parseInt(DNI) % 23;
        if ( dniChars.charAt(numDNI) == letraDni) {
            return true;
        } else {
            return false;
        }
    }

    public static CompteEstalvi verificarCuenta(String account) throws AccountNotFoundException {
        CompteEstalvi compteDevolver = null;
        for (CompteEstalvi compteEstalvi : Menu.listaCompteEstalvi) {
            if (compteEstalvi.getNumCompte().equals(account)) {
                compteDevolver = compteEstalvi;
            }
        }
        if (compteDevolver != null) {
            return compteDevolver;
        } else {
            throw new AccountNotFoundException(ExceptionMessage.ACCOUNT_NOT_FOUND);
        }
    }
    public static Client buscarCliente(String dni, List<Client> clientList) throws ClientNotFound {
        Client cliente = null;
        for (Client client : clientList) {
            if (dni.equals(client.getDNI())) {
                cliente = client;
            }
        }
        if (cliente != null) {
            return cliente;
        } else {
            throw new ClientNotFound(ExceptionMessage.CLIENT_NOT_FOUND);
        }

    }

}
