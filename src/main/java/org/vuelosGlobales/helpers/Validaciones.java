package org.vuelosGlobales.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Supplier;


public class Validaciones {

    Scanner scanner = new Scanner(System.in);

    public int validarInt(String mensaje){
        int resultado;
        while(true){
            System.out.println(mensaje);
            try{
                resultado = Integer.parseInt(scanner.nextLine());
                break;
            }catch(NumberFormatException e){
                System.out.println("Debe ingresar un número entero");
            }
        }
        return resultado;
    }

    public String validarString(String mensaje){
        String resultado;

        while(true){
            System.out.printf(mensaje);
                resultado = scanner.nextLine();
                if(resultado.isBlank()){
                    System.out.println("Debe ingresar un valor");
                }else{
                    break;
                }
        }
        return resultado;
    }
    public <T> T validarExistId(Supplier<Optional<T>> supplier){
        T objetoSeleccionado = null;
        while (true) {
            Optional<T> objetoOpcional = supplier.get();
            if (objetoOpcional.isPresent()) {
                return objetoSeleccionado = objetoOpcional.get();
            } else {
                System.out.println("El id no existe");
            }
        }
    }

    public String yesOrNo(String message){
        String option;
        while (true){
            option = this.validarString(message);
            if (!option.equals("y") && !option.equals("n")){
                System.out.print("Ingresa una opción valida, ");
            }else {
                break;
            }
        }
        return option;
    }

    public <T> String validarExistPlate(String mensaje, Validator<T> validator){
        Validaciones validaciones = new Validaciones();
        while (true) {
            String input = validaciones.validarString(mensaje);
            Optional<T> validatonResult = validator.validate(input.toUpperCase());
            if (validatonResult.isPresent()) {
                System.out.println("El dato ingresado ya existe");
            } else {
                return input;
            }
        }
    }

    public Date validarFecha(String mensaje) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        Date fecha = null;

        while (fecha == null) {
            System.out.print(mensaje);
            String input = scanner.nextLine();
            try {
                java.util.Date utilDate = dateFormat.parse(input);
                fecha = new Date(utilDate.getTime());
            } catch (ParseException e) {
                System.out.println("Fecha inválida. Formato correcto: YYYY-MM-DD.");
            }
        }
        return fecha;
    }

}
