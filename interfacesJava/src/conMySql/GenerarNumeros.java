/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conMySql;

/**
 *
 * @author CPU_SYS
 */
public class GenerarNumeros {
    private int dato;
    private int cont = 1;
    private String num = "";
    
    public void generarEntradas(int dato){
        this.dato = dato;

        if((this.dato >= 99999999)&&(this.dato<999999999)){
            int can = cont+this.dato;
            num = "NE"+can;
        }
        if((this.dato >= 9999999)&&(this.dato<99999999)){
            int can = cont+this.dato;
            num = "NE0"+can;
        }
        if((this.dato >= 999999)&&(this.dato<9999999)){
            int can = cont+this.dato;
            num = "NE00"+can;
        }
        if((this.dato >= 99999)&&(this.dato<999999)){
            int can = cont+this.dato;
            num = "NE000"+can;
        }
        if((this.dato >= 9999)&&(this.dato < 99999)){
            int can = cont+this.dato;
            num = "NE0000"+can;
        }
        if((this.dato >= 999)&&(this.dato < 9999)){
            int can = cont+this.dato;
            num = "NE00000"+can;
        }
        if((this.dato >= 99)&&(this.dato < 999)){
            int can = cont+this.dato;
            num = "NE000000"+can;
        }
        if((this.dato >= 9)&&(this.dato < 99)){
            int can = cont+this.dato;
            num = "NE0000000"+can;
        }
        if((this.dato >= 1)&&(this.dato < 9)){
            int can = cont+this.dato;
            num = "NE00000000"+can;
        }
        if(this.dato == 0){
            int can = cont+this.dato;
            num = "NE00000000"+can;
        }
    }
    
    public void generarSalidas(int dato){
        this.dato = dato;

        if((this.dato >= 99999999)&&(this.dato<999999999)){
            int can = cont+this.dato;
            num = "NS"+can;
        }
        if((this.dato >= 9999999)&&(this.dato<99999999)){
            int can = cont+this.dato;
            num = "NS0"+can;
        }
        if((this.dato >= 999999)&&(this.dato<9999999)){
            int can = cont+this.dato;
            num = "NS00"+can;
        }
        if((this.dato >= 99999)&&(this.dato<999999)){
            int can = cont+this.dato;
            num = "NS000"+can;
        }
        if((this.dato >= 9999)&&(this.dato < 99999)){
            int can = cont+this.dato;
            num = "NS0000"+can;
        }
        if((this.dato >= 999)&&(this.dato < 9999)){
            int can = cont+this.dato;
            num = "NS00000"+can;
        }
        if((this.dato >= 99)&&(this.dato < 999)){
            int can = cont+this.dato;
            num = "NS000000"+can;
        }
        if((this.dato >= 9)&&(this.dato < 99)){
            int can = cont+this.dato;
            num = "NS0000000"+can;
        }
        if((this.dato >= 1)&&(this.dato < 9)){
            int can = cont+this.dato;
            num = "NS00000000"+can;
        }
        if(this.dato == 0){
            int can = cont+this.dato;
            num = "NS00000000"+can;
        }
    }
    
    public void generarEnvios(int dato){
        this.dato = dato;

        if((this.dato >= 99999999)&&(this.dato<999999999)){
            int can = cont+this.dato;
            num = "EN"+can;
        }
        if((this.dato >= 9999999)&&(this.dato<99999999)){
            int can = cont+this.dato;
            num = "EN0"+can;
        }
        if((this.dato >= 999999)&&(this.dato<9999999)){
            int can = cont+this.dato;
            num = "EN00"+can;
        }
        if((this.dato >= 99999)&&(this.dato<999999)){
            int can = cont+this.dato;
            num = "EN000"+can;
        }
        if((this.dato >= 9999)&&(this.dato < 99999)){
            int can = cont+this.dato;
            num = "EN0000"+can;
        }
        if((this.dato >= 999)&&(this.dato < 9999)){
            int can = cont+this.dato;
            num = "EN00000"+can;
        }
        if((this.dato >= 99)&&(this.dato < 999)){
            int can = cont+this.dato;
            num = "EN000000"+can;
        }
        if((this.dato >= 9)&&(this.dato < 99)){
            int can = cont+this.dato;
            num = "EN0000000"+can;
        }
        if((this.dato >= 1)&&(this.dato < 9)){
            int can = cont+this.dato;
            num = "EN00000000"+can;
        }
        if(this.dato == 0){
            int can = cont+this.dato;
            num = "EN00000000"+can;
        }
    }
    public String serie(){
        return this.num;
    }
}
