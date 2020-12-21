package com.company;

import com.company.Control.OperacionsBanc;
import com.company.Exceptions.BankAccountException;
import com.company.Exceptions.ClientAccountException;
import com.company.Exceptions.TransferException;
import com.company.Model.Client;
import com.company.Model.CompteEstalvi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        new Menu().menuPrincipal();
    }
}
