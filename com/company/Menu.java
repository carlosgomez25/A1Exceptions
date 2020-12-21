package com.company;

import com.company.Control.OperacionsBanc;
import com.company.Exceptions.*;
import com.company.Model.Client;
import com.company.Model.CompteEstalvi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static ArrayList<CompteEstalvi> listaCompteEstalvi = new ArrayList<>();
    private List<Client> listaClientes = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void menuPrincipal() {
        while (true) {
            System.out.println("----------");
            System.out.println("  MENU  ");
            System.out.println("----------");
            System.out.println("1. Clientes");
            System.out.println("2. Cuentas");
            System.out.println("3. Salir");

            System.out.print("Elige una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                menuClientes();
            } else if (opcion == 2) {
                menuCuentas();
            } else if (opcion == 3) {
                break;
            }
        }
        System.out.println("Gracias por usar nuestro programa, hasta pronto");
    }

    private void menuClientes() {
        Client client;
        while (true) {
            System.out.println("Clientes");
            System.out.println("-----------");
            System.out.println("1. Crear cliente");
            System.out.println("2. Enseñar clientes");
            System.out.println("3. Volver hacia atras");
            System.out.print("Elige una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                System.out.println("Nuevo cliente");
                System.out.print("Escriba su nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Escriba sus apellidos: ");
                String apellidos = scanner.nextLine();
                System.out.print("Escriba su DNI: ");
                String dni = scanner.nextLine();

                try {
                    client = new Client(nombre, apellidos, dni);
                    listaClientes.add(client);
                    System.out.println("Cliente creado con exito");
                } catch (ClientAccountException e) {
                    System.out.println(e.getMessage());
                }
                break;
            } else if (opcion == 2) {
                enseñarClientes();
            } else if (opcion == 3) {
                break;
            }
        }

    }

    private void menuCuentas() {
        while (true) {
            System.out.println("Cuentas");
            System.out.println("-------");
            System.out.println("1. Crear cuenta");
            System.out.println("2. Operar en la cuenta");
            System.out.println("3. Enseñar cuentas");
            System.out.println("4. Volver hacia atras");
            System.out.print("Elige una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                System.out.println("Crear nueva cuenta");
                System.out.print("Escribe tu numero de cuenta: ");
                String numeroCuenta = scanner.nextLine();
                CompteEstalvi compteEstalvi = new CompteEstalvi(numeroCuenta);
                listaCompteEstalvi.add(compteEstalvi);
                System.out.println("Cuenta creada correctamente");
            } else if (opcion == 2) {
                System.out.print("Escribe el numero de cuenta en el que quiere operar: ");
                String numeroCuenta = scanner.nextLine();
                try {
                    CompteEstalvi compteEstalvi = OperacionsBanc.verificarCuenta(numeroCuenta);
                    menuOperarCuenta(compteEstalvi);
                } catch (AccountNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } else if (opcion == 3) {
                enseñarCuentas();
            } else if (opcion == 4) {
                break;
            }

        }
    }

    private void menuOperarCuenta(CompteEstalvi compteEstalvi) {
        while (true) {
            System.out.println("Operar en cuenta");
            System.out.println("----------------");
            System.out.println("1. Ingresar dinero en la cuenta");
            System.out.println("2. Retirar dinero de la cuenta");
            System.out.println("3. Realizar transferencia");
            System.out.println("4. Añadir cliente a la cuenta");
            System.out.println("5. Eliminar cliente de la cuenta");
            System.out.println("6. Volver hacia atras");
            System.out.print("Elige una opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                System.out.print("Cantidad de dinero que quiere ingresar: ");
                double dinero = scanner.nextDouble();
                scanner.nextLine();
                compteEstalvi.ingressar(dinero);
                System.out.println("Dinero ingresado con exito");
            } else if (opcion == 2) {
                System.out.print("Cantidad de dinero que quiere retirar: ");
                double dinero = scanner.nextDouble();
                scanner.nextLine();
                try {
                    compteEstalvi.treure(dinero);
                    System.out.println("Dinero retirado con exito");
                } catch (BankAccountException e) {
                    System.out.println(e.getMessage());
                }
            } else if (opcion == 3) {
                System.out.print("Escribe el numero de cuenta a la que desea hacer la transferencia: ");
                String cuentaDestinatario = scanner.nextLine();
                try {
                    CompteEstalvi compteDestinatario = OperacionsBanc.verificarCuenta(cuentaDestinatario);
                    System.out.print("Dinero que quiere trasnferir: ");
                    double dinero = scanner.nextDouble();
                    scanner.nextLine();
                    compteEstalvi.transferencia(cuentaDestinatario, dinero);
                    System.out.println("Transferencia realizada con exito");
                } catch (AccountNotFoundException e) {
                    System.out.println(e.getMessage());
                } catch (TransferException e) {
                    System.out.println(e.getMessage());
                }
            } else if (opcion == 4) {
                try {
                    System.out.print("Escribe el DNI del cliente que quieres añadir a la cuenta: ");
                    String dni = scanner.nextLine();
                    Client client = OperacionsBanc.buscarCliente(dni, listaClientes);
                    compteEstalvi.addUser(client);
                    System.out.println("Cliente añadido "+compteEstalvi.getNumCompte()+" con exito");
                } catch (ClientNotFound clientNotFound) {
                    System.out.println(clientNotFound.getMessage());
                }
            }
            else if (opcion == 5) {
                try {
                    System.out.print("Escribe el DNI del cliente que quieres eliminar: ");
                    String dni = scanner.nextLine();
                    OperacionsBanc.buscarCliente(dni, listaClientes);
                    compteEstalvi.removeUser(dni);
                    System.out.println("Usuario retirado " + compteEstalvi.getNumCompte() + " con exito");
                } catch (BankAccountException e) {
                    System.out.println(e.getMessage());
                } catch (ClientNotFound clientNotFound) {
                    System.out.println(clientNotFound.getMessage());
                }
            }
            else if (opcion == 6) {
                break;
            }
        }
    }

    private void enseñarClientes() {
        for (Client client : listaClientes) {
            System.out.println("Nombre: " + client.getNom() + " - Apellidos: " + client.getCognoms() + " - DNI: " + client.getDNI());
        }
    }

    private void enseñarCuentas() {
        for (CompteEstalvi compteEstalvi : listaCompteEstalvi) {
            System.out.println("------------------");
            System.out.println("Numero de la cuenta: " + compteEstalvi.getNumCompte());
            System.out.println("Cliente:");
            for (Client client : compteEstalvi.getLlista_usuaris()) {
                System.out.println("\n- Nombre: " + client.getNom() + " - DNI: " + client.getDNI());
            }
            System.out.println("\nSaldo: " + compteEstalvi.getSaldo());
            System.out.println("------------------");
        }
    }


}
